package de.seleri.core.data.daos.compositePk.joins

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.data.entities.joins.KategorieXKartentextRoom

@Dao
interface KategorieXKartentextDAO: JoinDAO<KategorieXKartentextRoom, KategorieID> {

	@Query("SELECT * FROM kategorie_x_kartentext WHERE kategorieID = :sammlungID")
	override suspend fun getAll(sammlungID: KategorieID): List<KategorieXKartentextRoom>

	@Delete
	override suspend fun delete(entity: KategorieXKartentextRoom): Int

	@Insert
	override suspend fun insert(entity: KategorieXKartentextRoom)

	@Insert
	override suspend fun insertAll(entities: Collection<KategorieXKartentextRoom>)
}
