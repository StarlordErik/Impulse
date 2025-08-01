package de.seleri.core.data.implementations

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.daos.singles.spielelemente.KartentextDAO
import de.seleri.core.domain.deprecatedRepositories.KartentextRepo
import de.seleri.core.domain.deprecatedRepositories.LokalisierungRepo
import de.seleri.core.domain.mapper.spielelemente.spiel.toDomain
import de.seleri.core.domain.mapper.spielelemente.spiel.toEntity
import de.seleri.core.domain.model.idEntity.spielelemente.Kartentext

class KartentextImpl(
	private val dao: KartentextDAO, private val lokalisierungRepo: LokalisierungRepo
): KartentextRepo {

	override suspend fun upsert(spielelement: Kartentext) {
		val keyID = dao
			.upsert(spielelement.toEntity())
			.toInt()

		val kartentextID = SpielelementID.KartentextID(keyID)

		spielelement.lokalisierung.map { lokalisierung ->
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
