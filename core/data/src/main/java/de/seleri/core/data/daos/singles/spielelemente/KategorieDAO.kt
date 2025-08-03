package de.seleri.core.data.daos.singles.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.data.entities.singles.spielelemente.KategorieRoom

@Dao
interface KategorieDAO: SpielelementDAO<KategorieRoom, KategorieID> {

	@Delete
	override suspend fun delete(entity: KategorieRoom): Int

	@Insert
	override suspend fun insert(entity: KategorieRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<KategorieRoom>): List<Long>

	@Query("SELECT * FROM kategorien WHERE id = :spielelementID")
	override suspend fun get(spielelementID: KategorieID): KategorieRoom

	@Query("SELECT * FROM kategorien WHERE id = :spielelementID")
	override suspend fun find(spielelementID: KategorieID): KategorieRoom?

	@Update
	override suspend fun update(entity: KategorieRoom): Int
}
