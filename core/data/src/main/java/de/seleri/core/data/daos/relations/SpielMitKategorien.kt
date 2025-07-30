package de.seleri.core.data.daos.relations

import androidx.room.Query
import de.seleri.core.common.id.spielelementID.SpielID
import de.seleri.core.data.entities.singles.lokStern.spielelemente.KategorieRoom

interface SpielMitKategorien: RelationDao<SpielID, KategorieRoom> {

	@Query(
		"""
		SELECT k.* FROM kategorien k
		INNER JOIN spiel_x_kategorie sxk ON k.id = sxk.kategorieID
		WHERE sxk.spielID = :sammlungID
	"""
	)
	override suspend fun getAll(sammlungID: SpielID): List<KategorieRoom>
}
