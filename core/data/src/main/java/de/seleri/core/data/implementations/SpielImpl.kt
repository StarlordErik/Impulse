package de.seleri.core.data.implementations

import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.data.daos.compositePk.joins.SpielXKategorieDAO
import de.seleri.core.data.daos.relations.SpielMitKategorienDAO
import de.seleri.core.data.daos.singles.spielelemente.SpielDAO
import de.seleri.core.data.entities.joins.SpielXKategorieRoom
import de.seleri.core.data.toRoom
import de.seleri.core.domain.mapper.spielelemente.spiel.toDomain
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
		val entity = dao.get(id)
		val lokalisierung = lokalisierungRepo.get(entity.lokalisierungID)

		val kategorieEntities = relationDao.getAllBestandteile(id)
		val kategorien = kategorieRepo.complete(kategorieEntities)

		return entity.toDomain(lokalisierung, kategorien)
	}

	override suspend fun find(id: SpielID): Spiel? {
		val entity = dao.find(id)

		if (entity == null) return null
		else {
			val lokalisierung = lokalisierungRepo.get(entity.lokalisierungID)

			val kategorieEntities = relationDao.getAllBestandteile(id)
			val kategorien = kategorieRepo.complete(kategorieEntities)

			return entity.toDomain(lokalisierung, kategorien)
		}
	}

	override suspend fun update(model: Spiel): Int {
		val entity = model.toRoom()

		var updateCounter = dao.update(entity)

		val connections = joinDao.getAll(entity.id)
		val connectionKategorieIDs = connections.map { it.kategorieID }

		model.bestandteile.forEach { kategorie ->
			val kategorieID = kategorie.id
			if (kategorieID !in connectionKategorieIDs) {
				val joinEntity = SpielXKategorieRoom(entity.id, kategorieID)
				joinDao.insert(joinEntity)
				updateCounter++
			}
		}

		val bestandteilKategorieIDs = model.bestandteile.map { it.id }

		connectionKategorieIDs.forEach { connectionKategorieID ->
			if (connectionKategorieID !in bestandteilKategorieIDs) {
				val joinEntity = SpielXKategorieRoom(entity.id, connectionKategorieID)
				joinDao.delete(joinEntity)
				updateCounter++
			}
		}

		return updateCounter
	}

	override suspend fun getAllMetas(): Collection<SpielMetaDO> {
		TODO("Not yet implemented")
	}
}
