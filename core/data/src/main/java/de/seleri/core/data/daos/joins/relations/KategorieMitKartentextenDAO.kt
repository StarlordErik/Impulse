package de.seleri.core.data.daos.joins.relations

import androidx.room.Query
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.data.entities.singles.lokStern.spielelemente.KartentextRoom

interface KategorieMitKartentextenDAO: RelationDAO<KategorieID, KartentextRoom> {

	@Query(
		"""
		SELECT kt.* FROM kartentexte kt
		INNER JOIN kategorie_x_kartentext kxk ON kt.id = kxk.kartentextID
		WHERE kxk.kategorieID = :sammlungID
	"""
	)
	override suspend fun getAll(sammlungID: KategorieID): List<KartentextRoom>
}
