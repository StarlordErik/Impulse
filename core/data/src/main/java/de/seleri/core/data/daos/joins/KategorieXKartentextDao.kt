package de.seleri.core.data.daos.joins

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.seleri.core.common.id.spielelementID.KartentextID
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.data.entities.joins.KategorieXKartentextRoom

interface KategorieXKartentextDao: JoinDao<KategorieXKartentextRoom, KategorieID, KartentextID> {

	@Insert
	override suspend fun insert(entity: KategorieXKartentextRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<KategorieXKartentextRoom>): List<Long>

	@Delete
	override suspend fun delete(entity: KategorieXKartentextRoom): Int

	@Query("SELECT kartentextID FROM kategorie_x_kartentext WHERE kategorieID = :sammlungID")
	override suspend fun getAllForSammlung(sammlungID: KategorieID): List<KartentextID>
}
