package de.seleri.core.data.daos

import de.seleri.core.data.entities.singles.EntityRoom

interface DatenbankObjektDao<D: EntityRoom> {

	suspend fun upsert(entity: D): Long
	suspend fun delete(entity: D)
}
