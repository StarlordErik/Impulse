package de.seleri.kern.daten.daos

import androidx.room.Delete
import androidx.room.Upsert
import de.seleri.kern.daten.entities.joins.SpielXKategorie

interface SpielXKategorieDao {

  @Upsert
  suspend fun upsert(spielXKategorie: SpielXKategorie)

  @Delete
  suspend fun delete(spielXKategorie: SpielXKategorie)
}
