package de.seleri.core.data.implementations

import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.data.daos.singles.spielelemente.KartentextDAO
import de.seleri.core.data.toRoom
import de.seleri.core.domain.mapper.spielelemente.toDomain
import de.seleri.core.domain.model.idEntity.spielelemente.Kartentext
import de.seleri.core.domain.repositories.idEntity.LokalisierungRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KartentextRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KategorieRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.SpielRepo

class KartentextImpl(
	private val dao: KartentextDAO,
	private val lokalisierungRepo: LokalisierungRepo,
	private val kategorieRepo: KategorieRepo,
	private val spielRepo: SpielRepo
): KartentextRepo {

	override suspend fun new(modell: Kartentext): KartentextID {
		val entity = modell.toRoom()

		val lokalisierungID = lokalisierungRepo.new(modell.lokalisierung)
		val entityMitPassenderLokalisierungID = entity.copy(lokalisierungID = lokalisierungID)

		val id = dao.insert(entityMitPassenderLokalisierungID)
		return KartentextID(id.toInt())
	}

	override suspend fun delete(model: Kartentext): Int {
		val entity = model.toRoom()
		val id = entity.id.value

		var deleteCounter = dao.delete(entity)

		val kategorieID = KategorieID(id)
		val kategorie = kategorieRepo.find(kategorieID)

		val spielID = SpielID(id)
		val spiel = spielRepo.find(spielID)

		if (kategorie == null && spiel == null) {
			deleteCounter += lokalisierungRepo.delete(model.lokalisierung)
		}

		return deleteCounter
	}

	override suspend fun get(id: KartentextID): Kartentext {
		val entity = dao.get(id)

		val lokalisierung = lokalisierungRepo.get(entity.lokalisierungID)

		return entity.toDomain(lokalisierung)
	}

	override suspend fun find(id: KartentextID): Kartentext? {
		val entity = dao.find(id)

		if (entity == null) return null
		else {

			val lokalisierung = lokalisierungRepo.get(entity.lokalisierungID)
			return entity.toDomain(lokalisierung)
		}
	}

	override suspend fun update(model: Kartentext): Int {
		val entity = model.toRoom()

		return dao.update(entity)
	}

	override suspend fun updateAll(kartentexte: Collection<Kartentext>): Int {
		val entities = kartentexte.map { it.toRoom() }

		return dao.updateAll(entities)
	}
}
