package de.seleri.core.repository.implementations

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.daos.KartentextDao
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.repositories.KartentextRepo
import de.seleri.core.domain.repositories.LokalisierungRepo
import de.seleri.core.repository.mapper.toEntity
import javax.inject.Inject

class KartentextImpl @Inject constructor(
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


	override suspend fun update(kartentexte: Collection<Kartentext>) {
		dao.update(kartentexte.map { it.toEntity() })
	}
}
