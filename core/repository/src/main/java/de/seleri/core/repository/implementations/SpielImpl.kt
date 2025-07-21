package de.seleri.core.repository.implementations

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.daos.SpielDao
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaObjekt
import de.seleri.core.domain.repositories.LokalisierungRepo
import de.seleri.core.domain.repositories.SpielRepo
import de.seleri.core.repository.mapper.toEntity
import de.seleri.core.repository.mapper.toMeta
import javax.inject.Inject

class SpielImpl @Inject constructor(
	private val dao: SpielDao, private val lokalisierungRepo: LokalisierungRepo
): SpielRepo {

	override suspend fun upsert(spielelement: Spiel) {
		TODO("Not yet implemented")
	}

	override suspend fun delete(spielelement: Spiel) =
		dao.delete(spielelement.toEntity())


	override suspend fun insertConnection(
		sammlung: Spiel, bestandteil: Kategorie
	) {
		TODO("Not yet implemented")
	}

	override suspend fun deleteConnection(
		sammlung: Spiel, bestandteil: Kategorie
	) {
		TODO("Not yet implemented")
	}

	override suspend fun getAllMetas(): Collection<SpielMetaObjekt> {
		val spielEntities = dao.getAll()

		return spielEntities.map { spielEntity ->
			val spielID = SpielelementID.SpielID(spielEntity.id)
			val lokalisierungen = lokalisierungRepo.getForSpiel(spielID)
			spielEntity.toMeta(lokalisierungen)
		}
	}
}
