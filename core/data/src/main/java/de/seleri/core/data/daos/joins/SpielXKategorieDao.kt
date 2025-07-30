package de.seleri.core.data.daos.joins

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.common.id.spielelementID.SpielID
import de.seleri.core.data.entities.joins.SpielXKategorieRoom

interface SpielXKategorieDao: JoinDao<SpielXKategorieRoom, SpielID, KategorieID> {

	@Insert
	override suspend fun insert(entity: SpielXKategorieRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<SpielXKategorieRoom>): List<Long>

	@Delete
	override suspend fun delete(entity: SpielXKategorieRoom): Int

	@Query("SELECT kategorieID FROM spiel_x_kategorie WHERE spielID = :sammlungID")
	override suspend fun getAllForSammlung(sammlungID: SpielID): List<KategorieID>
}
