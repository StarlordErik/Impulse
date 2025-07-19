package de.seleri.core.data.daos

import androidx.room.Delete
import androidx.room.Upsert
import de.seleri.core.data.entities.joins.SpielXKategorie

interface SpielXKategorieDao {

  @Upsert
  suspend fun upsert(spielXKategorie: SpielXKategorie)

  @Delete
  suspend fun delete(spielXKategorie: SpielXKategorie)
}
