package de.seleri.core.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import de.seleri.core.data.entities.joins.KategorieXKartentext
import de.seleri.core.data.entities.singles.KartentextEntity
import de.seleri.core.data.entities.singles.KategorieEntity

@Dao
interface KategorieDao {

	@Upsert
	suspend fun upsert(kategorie: KategorieEntity): Long

	@Delete
	suspend fun delete(kategorie: KategorieEntity)

	@Query("SELECT * FROM Kategorien WHERE id = :kategorieId")
	suspend fun get(kategorieId: Int): KategorieEntity

	@Upsert
	suspend fun upsert(kategorieXKartentext: KategorieXKartentext)

	@Delete
	suspend fun delete(kategorieXKartentext: KategorieXKartentext)

	@Query(
		"""
        SELECT t.* FROM Kartentexte t
        INNER JOIN KategorieXKartentext x ON t.id = x.kartentextID
        WHERE x.kategorieID = :kategorieId
    """
	)
	suspend fun getKartentexte(kategorieId: Int): List<KartentextEntity>
}
