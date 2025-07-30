package de.seleri.core.data.implementations

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.daos.singles.LokalisierungDAO
import de.seleri.core.data.mapper.toDomain
import de.seleri.core.data.mapper.toEntityForKartentext
import de.seleri.core.data.mapper.toEntityForKategorie
import de.seleri.core.data.mapper.toEntityForSpiel
import de.seleri.core.data.mapper.toNullEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.repositories.LokalisierungRepo

class LokalisierungImpl(
	private val dao: LokalisierungDAO,
): LokalisierungRepo {

	override suspend fun upsertForSpiel(spielID: SpielelementID.SpielID, lokalisierung: Lokalisierung) {
		dao.upsert(lokalisierung.toEntityForSpiel(spielID))
	}


	override suspend fun upsertForKategorie(kategorieID: SpielelementID.KategorieID, lokalisierung: Lokalisierung) {
		dao.upsert(lokalisierung.toEntityForKategorie(kategorieID))
	}


	override suspend fun upsertForKartentext(kartentextID: SpielelementID.KartentextID, lokalisierung: Lokalisierung) {
		dao.upsert(lokalisierung.toEntityForKartentext(kartentextID))
	}


	override suspend fun delete(lokalisierung: Lokalisierung) =
		dao.delete(lokalisierung.toNullEntity())


	override suspend fun getForSpiel(spielID: SpielelementID.SpielID): List<Lokalisierung> =
		dao
			.getForSpiel(spielID.toInt())
			.map { it.toDomain() }

	override suspend fun getForKategorie(kategorieID: SpielelementID.KategorieID): List<Lokalisierung> =
		dao
			.getForKategorie(kategorieID.toInt())
			.map { it.toDomain() }

	override suspend fun getForKartentext(kartentextID: SpielelementID.KartentextID): List<Lokalisierung> =
		dao
			.getForKartentext(kartentextID.toInt())
			.map { it.toDomain() }
}
