package de.seleri.core.repository.implementations

import de.seleri.core.data.daos.KartentextDao
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.repositories.KartentextRepo
import de.seleri.core.repository.mapper.toEntity
import javax.inject.Inject

class KartentextImpl @Inject constructor(
	private val dao: KartentextDao,
): KartentextRepo {

	override suspend fun upsert(spielelement: Kartentext) {
		TODO("Not yet implemented")
	}

	override suspend fun delete(spielelement: Kartentext) =
		dao.delete(spielelement.toEntity())


	override suspend fun upsert(kartentexte: Collection<Kartentext>) {
		TODO("Not yet implemented")
	}
}
