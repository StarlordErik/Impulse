package de.seleri.core.data.implementations

import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.data.daos.singles.spielelemente.KategorieDAO
import de.seleri.core.data.toRoom
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie
import de.seleri.core.domain.repositories.idEntity.LokalisierungRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KartentextRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KategorieRepo

class KategorieImpl(
	private val dao: KategorieDAO,
	private val lokalisierungRepo: LokalisierungRepo,
	private val kartentextRepo: KartentextRepo,
): KategorieRepo {

	override suspend fun new(modell: Kategorie): KategorieID {
		val entity = modell.toRoom()
// TODO KategorieXKartentext erstellen
		val id = dao.insert(entity)

		return KategorieID(id.toInt())
	}

	override suspend fun delete(model: Kategorie): Int {
		val entity = model.toRoom()
// TODO schau in KartentextImpl
		return dao.delete(entity)
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
		TODO("Not yet implemented")
	}
}
