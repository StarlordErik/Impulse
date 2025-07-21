package de.seleri.core.repository.implementations

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.daos.LokalisierungDao
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.repositories.LokalisierungRepo
import de.seleri.core.repository.mapper.toDomain
import de.seleri.core.repository.mapper.toEntityForKartentext
import de.seleri.core.repository.mapper.toEntityForKategorie
import de.seleri.core.repository.mapper.toEntityForSpiel
import de.seleri.core.repository.mapper.toNullEntity
import javax.inject.Inject

class LokalisierungImpl @Inject constructor(
	private val dao: LokalisierungDao,
): LokalisierungRepo {

	override suspend fun upsertForSpiel(spielID: SpielelementID.SpielID, lokalisierung: Lokalisierung) =
		dao.upsert(lokalisierung.toEntityForSpiel(spielID))


	override suspend fun upsertForKategorie(kategorieID: SpielelementID.KategorieID, lokalisierung: Lokalisierung) =
		dao.upsert(lokalisierung.toEntityForKategorie(kategorieID))


	override suspend fun upsertForKartentext(kartentextID: SpielelementID.KartentextID, lokalisierung: Lokalisierung) =
		dao.upsert(lokalisierung.toEntityForKartentext(kartentextID))


	override suspend fun delete(lokalisierung: Lokalisierung) =
		dao.delete(lokalisierung.toNullEntity())


	override suspend fun getForSpiel(spielID: SpielelementID.SpielID): Collection<Lokalisierung> =
		dao
			.getForSpiel(spielID.toInt())
			.map { it.toDomain() }

	override suspend fun getForKategorie(kategorieID: SpielelementID.KategorieID): Collection<Lokalisierung> =
		dao
			.getForKategorie(kategorieID.toInt())
			.map { it.toDomain() }

	override suspend fun getForKartentext(kartentextID: SpielelementID.KartentextID): Collection<Lokalisierung> =
		dao
			.getForKartentext(kartentextID.toInt())
			.map { it.toDomain() }
}
