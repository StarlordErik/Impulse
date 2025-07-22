package de.seleri.core.data.daos

import de.seleri.core.data.entities.singles.DatenbankObjektEntity

interface DatenbankObjektDao<D: DatenbankObjektEntity> {

	suspend fun upsert(entity: D): Long
	suspend fun delete(entity: D)
}
