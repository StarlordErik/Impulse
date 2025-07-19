package de.seleri.core.data.daos

import androidx.room.Delete
import androidx.room.Upsert
import de.seleri.core.data.entities.joins.KategorieXKartentext

interface KategorieXKartentextDao {

  @Upsert
  suspend fun upsert(kategorieXKartentext: KategorieXKartentext)

  @Delete
  suspend fun delete(kategorieXKartentext: KategorieXKartentext)
}
