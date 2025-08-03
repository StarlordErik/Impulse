package de.seleri.core.data.implementations

import de.seleri.core.common.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.data.daos.compositePk.joins.KategorieXKartentextDAO
import de.seleri.core.data.daos.relations.KategorieMitKartentextenDAO
import de.seleri.core.data.daos.singles.spielelemente.KategorieDAO
import de.seleri.core.data.entities.joins.KategorieXKartentextRoom
import de.seleri.core.data.toRoom
import de.seleri.core.domain.mapper.spielelemente.toDomain
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie
import de.seleri.core.domain.repositories.idEntity.LokalisierungRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KartentextRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KategorieRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.SpielRepo

class KategorieImpl(
	private val dao: KategorieDAO,
	private val joinDao: KategorieXKartentextDAO,
	private val relationDao: KategorieMitKartentextenDAO,
	private val lokalisierungRepo: LokalisierungRepo,
	private val kartentextRepo: KartentextRepo,
	private val spielRepo: SpielRepo
): KategorieRepo {

	override suspend fun new(model: Kategorie): KategorieID {
		val entity = model.toRoom()

		val id = dao.insert(entity)
		val kategorieID = KategorieID(id.toInt())

		model.bestandteile.forEach { kartentext ->
			val joinEntity = KategorieXKartentextRoom(kategorieID, kartentext.id)
			joinDao.insert(joinEntity)
		}

		return kategorieID
	}

	override suspend fun delete(model: Kategorie): Int {
		val entity = model.toRoom()
		val id = entity.id.value

		var deleteCounter = dao.delete(entity)

		val kartentextID = KartentextID(id)
		val kartentext = kartentextRepo.find(kartentextID)

		val spielID = SpielID(id)
		val spiel = spielRepo.find(spielID)

		if (kartentext == null && spiel == null) {
			deleteCounter += lokalisierungRepo.delete(model.lokalisierung)
		}

		model.bestandteile.forEach { kartentext ->
			val joinEntity = KategorieXKartentextRoom(model.id, kartentext.id)
			joinDao.delete(joinEntity)
			deleteCounter++
		}

		return deleteCounter
	}

	override suspend fun get(id: KategorieID): Kategorie {
		val entity = dao.get(id)
		val lokalisierung = lokalisierungRepo.get(entity.lokalisierungID)

		val kartentextEntities = relationDao.getAllBestandteile(id)
		val kartentexte = kartentextRepo.complete(kartentextEntities)

		return entity.toDomain(lokalisierung, kartentexte)
	}

	override suspend fun find(id: KategorieID): Kategorie? {
		val entity = dao.find(id)

		if (entity == null) return null
		else {
			val lokalisierung = lokalisierungRepo.get(entity.lokalisierungID)

			val kartentextEntities = relationDao.getAllBestandteile(id)
			val kartentexte = kartentextRepo.complete(kartentextEntities)

			return entity.toDomain(lokalisierung, kartentexte)
		}
	}

	override suspend fun update(model: Kategorie): Int {
		val entity = model.toRoom()

		var updateCounter = dao.update(entity)

		val connections = joinDao.getAll(entity.id)
		val connectionKartentextIDs = connections.map { it.kartentextID }

		model.bestandteile.forEach { kartentext ->
			val kartentextID = kartentext.id
			if (kartentextID !in connectionKartentextIDs) {
				val joinEntity = KategorieXKartentextRoom(entity.id, kartentextID)
				joinDao.insert(joinEntity)
				updateCounter++
			}
		}

		val bestandteilKartentextIDs = model.bestandteile.map { it.id }

		connectionKartentextIDs.forEach { connectionKartentextID ->
			if (connectionKartentextID !in bestandteilKartentextIDs) {
				val joinEntity = KategorieXKartentextRoom(entity.id, connectionKartentextID)
				joinDao.delete(joinEntity)
				updateCounter++
			}
		}

		return updateCounter
	}

	override suspend fun complete(bestandteilEntities: Collection<KategorieEntity>): Collection<Kategorie> {
		return bestandteilEntities.map {
			val lokalisierung = lokalisierungRepo.get(it.lokalisierungID)

			val kartentextEntities = relationDao.getAllBestandteile(it.id)
			val kartentexte = kartentextRepo.complete(kartentextEntities)

			it.toDomain(lokalisierung, kartentexte)
		}
	}
}
