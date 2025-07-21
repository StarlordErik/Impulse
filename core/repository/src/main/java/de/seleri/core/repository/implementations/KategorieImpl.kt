package de.seleri.core.repository.implementations

import de.seleri.core.data.daos.KategorieDao
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.repositories.KategorieRepo
import de.seleri.core.repository.mapper.toEntity
import javax.inject.Inject

class KategorieImpl @Inject constructor(
	private val dao: KategorieDao,
): KategorieRepo {

	override suspend fun upsert(spielelement: Kategorie) {
		TODO("Not yet implemented")
	}

	override suspend fun delete(spielelement: Kategorie) =
		dao.delete(spielelement.toEntity())

	override suspend fun insertConnection(
		sammlung: Kategorie, bestandteil: Kartentext
	) {
		TODO("Not yet implemented")
	}

	override suspend fun deleteConnection(
		sammlung: Kategorie, bestandteil: Kartentext
	) {
		TODO("Not yet implemented")
	}
}
