package de.seleri.core.repository.implementations

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.daos.LokalisierungDao
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.repositories.LokalisierungRepo
import javax.inject.Inject

class LokalisierungImpl @Inject constructor(
	private val dao: LokalisierungDao,
): LokalisierungRepo {

	override suspend fun upsertForSpiel(
		spielID: SpielelementID.SpielID, lokalisierung: Lokalisierung
	) {
		TODO("Not yet implemented")
	}

	override suspend fun upsertForKategorie(
		kategorieID: SpielelementID.KategorieID, lokalisierung: Lokalisierung
	) {
		TODO("Not yet implemented")
	}

	override suspend fun upsertForKartentext(
		kartentextID: SpielelementID.KartentextID, lokalisierung: Lokalisierung
	) {
		TODO("Not yet implemented")
	}

	override suspend fun delete(lokalisierung: Lokalisierung) {
		TODO("Not yet implemented")
	}

	override suspend fun getForSpiel(spielID: SpielelementID.SpielID): Collection<Lokalisierung> {
		TODO("Not yet implemented")
	}

	override suspend fun getForKategorie(kategorieID: SpielelementID.KategorieID): Collection<Lokalisierung> {
		TODO("Not yet implemented")
	}

	override suspend fun getForKartentext(kartentextID: SpielelementID.KartentextID): Collection<Lokalisierung> {
		TODO("Not yet implemented")
	}
}
