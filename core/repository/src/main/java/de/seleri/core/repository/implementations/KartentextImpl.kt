package de.seleri.core.repository.implementations

import de.seleri.core.data.daos.spielelemente.KartentextDao
import de.seleri.core.domain.model.idTypes.SpielelementID
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.repositories.KartentextRepo
import de.seleri.core.domain.repositories.LokalisierungRepo
import de.seleri.core.repository.mapper.toDomain
import de.seleri.core.repository.mapper.toEntity

class KartentextImpl(
	private val dao: KartentextDao, private val lokalisierungRepo: LokalisierungRepo
): KartentextRepo {

	override suspend fun upsert(spielelement: Kartentext) {
		val keyID = dao
			.upsert(spielelement.toEntity())
			.toInt()

		val kartentextID = SpielelementID.KartentextID(keyID)

		spielelement.lokalisierungen.map { lokalisierung ->
			lokalisierungRepo.upsertForKartentext(kartentextID, lokalisierung)
		}
	}

	override suspend fun delete(spielelement: Kartentext) =
		dao.delete(spielelement.toEntity())

	override suspend fun get(spielelementID: SpielelementID.KartentextID): Kartentext {
		val kartentextEntity = dao.get(spielelementID.toInt())

		val kartentextID = SpielelementID.KartentextID(kartentextEntity.id)
		val lokalisierungen = lokalisierungRepo.getForKartentext(kartentextID)

		return kartentextEntity.toDomain(lokalisierungen)
	}


	override suspend fun update(kartentexte: Collection<Kartentext>) {
		dao.update(kartentexte.map { it.toEntity() })
	}
}
