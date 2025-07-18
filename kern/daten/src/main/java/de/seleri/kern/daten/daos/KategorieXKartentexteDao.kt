package de.seleri.kern.daten.daos

import androidx.room.Delete
import androidx.room.Upsert
import de.seleri.kern.daten.entities.joins.KategorieXKartentexte

interface KategorieXKartentexteDao {

  @Upsert
  suspend fun upsert(kategorieXKartentexte: KategorieXKartentexte)

  @Delete
  suspend fun delete(kategorieXKartentexte: KategorieXKartentexte)
}
