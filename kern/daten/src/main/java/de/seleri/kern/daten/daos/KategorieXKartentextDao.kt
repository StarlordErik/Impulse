package de.seleri.kern.daten.daos

import androidx.room.Delete
import androidx.room.Upsert
import de.seleri.kern.daten.entities.joins.KategorieXKartentext

interface KategorieXKartentextDao {

  @Upsert
  suspend fun upsert(kategorieXKartentext: KategorieXKartentext)

  @Delete
  suspend fun delete(kategorieXKartentext: KategorieXKartentext)
}
