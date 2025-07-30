package de.seleri.core.data.daos.singles.lokStern.spielelemente

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.seleri.core.common.id.spielelementID.KategorieID
import de.seleri.core.data.entities.singles.lokStern.spielelemente.KartentextRoom
import de.seleri.core.data.entities.singles.lokStern.spielelemente.KategorieRoom

@Dao
interface KategorieDao: SpielelementDao<KategorieRoom, KategorieID> {

	@Insert
	override suspend fun insert(entity: KategorieRoom): Long

	@Insert
	override suspend fun insertAll(entities: Collection<KategorieRoom>): List<Long>

	@Delete
	override suspend fun delete(entity: KategorieRoom): Int

	@Update
	override suspend fun update(entity: KategorieRoom): Int

	@Query("SELECT * FROM kategorien WHERE id = :spielelementID")
	override suspend fun get(spielelementID: KategorieID): KategorieRoom

	@Query(
		"""
        SELECT t.* FROM Kartentexte t
        INNER JOIN KategorieXKartentext x ON t.id = x.kartentextID
        WHERE x.kategorieID = :kategorieId
    """
	)
	suspend fun getKartentexte(kategorieId: Int): List<KartentextRoom>
}
