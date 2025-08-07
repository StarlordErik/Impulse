package de.seleri.core.data.daos.relations

import androidx.room.Dao
import androidx.room.Query
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom

@Dao
interface KategorieMitKartentextenDAO: RelationDAO<KategorieID, KartentextRoom> {

	@Query(
		"""
		SELECT kt.* FROM kartentexte kt
		INNER JOIN kategorie_x_kartentext kxk ON kt.id = kxk.kartentextID
		WHERE kxk.kategorieID = :sammlungID
	"""
	)
	override suspend fun getAllBestandteile(sammlungID: KategorieID): List<KartentextRoom>
}
