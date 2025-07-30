package de.seleri.core.data.daos

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.seleri.core.data.entities.singles.TranslationRoom

interface TranslationDao: EntityDao<TranslationRoom> {

	@Insert
	override suspend fun insert(entity: TranslationRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<TranslationRoom>): List<Long>

	@Update
	override suspend fun update(entity: TranslationRoom): Int

	@Delete
	override suspend fun delete(entity: TranslationRoom): Int

	@Query("SELECT * FROM translationen WHERE lokalisierungID = :lokalisierungID")
	suspend fun getForLokalisierung(lokalisierungID: Int): List<TranslationRoom>
}
