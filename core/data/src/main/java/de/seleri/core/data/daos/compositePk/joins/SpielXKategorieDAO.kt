package de.seleri.core.data.daos.compositePk.joins

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.data.entities.joins.SpielXKategorieRoom

@Dao
interface SpielXKategorieDAO: JoinDAO<SpielXKategorieRoom, SpielID> {

	@Query("SELECT * FROM spiel_x_kategorie WHERE spielID = :sammlungID")
	override suspend fun getAll(sammlungID: SpielID): List<SpielXKategorieRoom>

	@Delete
	override suspend fun delete(entity: SpielXKategorieRoom): Int

	@Insert
	override suspend fun insert(entity: SpielXKategorieRoom)

	@Insert
	override suspend fun insertAll(entities: Collection<SpielXKategorieRoom>)
}
