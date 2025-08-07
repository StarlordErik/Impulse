package de.seleri.core.data.daos.relations

import androidx.room.Dao
import androidx.room.Query
import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom

@Dao
interface SpielMitKategorienDAO: RelationDAO<SpielID, KategorieRoom> {

	@Query(
		"""
		SELECT k.* FROM kategorien k
		INNER JOIN spiel_x_kategorie sxk ON k.id = sxk.kategorieID
		WHERE sxk.spielID = :sammlungID
	"""
	)
	override suspend fun getAllBestandteile(sammlungID: SpielID): List<KategorieRoom>
}
