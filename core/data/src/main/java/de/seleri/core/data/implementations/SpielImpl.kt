package de.seleri.core.data.implementations

import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.data.daos.compositePk.joins.SpielXKategorieDAO
import de.seleri.core.data.daos.relations.SpielMitKategorienDAO
import de.seleri.core.data.daos.singles.spielelemente.SpielDAO
import de.seleri.core.data.entities.joins.SpielXKategorieRoom
import de.seleri.core.data.toRoom
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.SpielMetaDO
import de.seleri.core.domain.repositories.idEntity.LokalisierungRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KartentextRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KategorieRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.SpielRepo

class SpielImpl(
	private val dao: SpielDAO,
	private val joinDao: SpielXKategorieDAO,
	private val relationDao: SpielMitKategorienDAO,
	private val lokalisierungRepo: LokalisierungRepo,
	private val kartentextRepo: KartentextRepo,
	private val kategorieRepo: KategorieRepo
): SpielRepo {

	override suspend fun new(model: Spiel): SpielID {
		val entity = model.toRoom()

		val id = dao.insert(entity)
		val spielID = SpielID(id.toInt())

		model.bestandteile.forEach { kategorie ->
			val joinEntity = SpielXKategorieRoom(spielID, kategorie.id)
			joinDao.insert(joinEntity)
		}

		return spielID
	}

	override suspend fun delete(model: Spiel): Int {
		val entity = model.toRoom()
		val id = entity.id.value

		var deleteCounter = dao.delete(entity)

		val kartentextID = KartentextID(id)
		val kartentext = kartentextRepo.find(kartentextID)

		val kategorieID = KategorieID(id)
		val kategorie = kategorieRepo.find(kategorieID)

		if (kartentext == null && kategorie == null) {
			deleteCounter += lokalisierungRepo.delete(model.lokalisierung)
		}

		model.bestandteile.forEach { kategorie ->
			val joinEntity = SpielXKategorieRoom(model.id, kategorie.id)
			joinDao.delete(joinEntity)
			deleteCounter++
		}

		return deleteCounter
	}

	override suspend fun get(id: SpielID): Spiel {
		TODO("Not yet implemented")
	}

	override suspend fun find(id: SpielID): Spiel? {
		TODO("Not yet implemented")
	}

	override suspend fun update(model: Spiel): Int {
		TODO("Not yet implemented")
	}

	override suspend fun getAllMetas(): Collection<SpielMetaDO> {
		TODO("Not yet implemented")
	}
}
