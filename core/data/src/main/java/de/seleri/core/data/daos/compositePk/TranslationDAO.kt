package de.seleri.core.data.daos.compositePk

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.data.daos.UpdateableDAO
import de.seleri.core.data.entities.TranslationRoom

@Dao
interface TranslationDAO: CompositePkDAO<TranslationRoom>, UpdateableDAO<TranslationRoom> {

	@Delete
	override suspend fun delete(entity: TranslationRoom): Int

	@Insert
	override suspend fun insert(entity: TranslationRoom)

	@Insert
	override suspend fun insertAll(entities: Collection<TranslationRoom>)

	@Update
	override suspend fun update(entity: TranslationRoom): Int

	@Query("SELECT * FROM translationen WHERE bezeichnung = :bezeichnung")
	suspend fun findByBezeichnung(bezeichnung: String): TranslationRoom?

	@Query("SELECT * FROM translationen WHERE lokalisierungID = :lokalisierungID")
	suspend fun getForLokalisierung(lokalisierungID: LokalisierungID): List<TranslationRoom>
}
