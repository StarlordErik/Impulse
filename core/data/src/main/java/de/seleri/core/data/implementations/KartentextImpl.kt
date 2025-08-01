package de.seleri.core.data.implementations

import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.data.daos.singles.spielelemente.KartentextDAO
import de.seleri.core.data.toRoom
import de.seleri.core.domain.mapper.spielelemente.toDomain
import de.seleri.core.domain.model.idEntity.spielelemente.Kartentext
import de.seleri.core.domain.repositories.idEntity.LokalisierungRepo
import de.seleri.core.domain.repositories.idEntity.spielelemente.KartentextRepo

class KartentextImpl(
	private val dao: KartentextDAO, private val lokalisierungRepo: LokalisierungRepo
): KartentextRepo {

	override suspend fun new(modell: Kartentext): KartentextID {
		val entity = modell.toRoom()

		val id = dao.insert(entity)

		return KartentextID(id.toInt())
	}

	override suspend fun delete(model: Kartentext): Int {
		val entity = model.toRoom()

		return dao.delete(entity)
	}

	override suspend fun get(id: KartentextID): Kartentext {
		val entity = dao.get(id)

		val lokalisierung = lokalisierungRepo.get(entity.lokalisierungID)

		return entity.toDomain(lokalisierung)
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
