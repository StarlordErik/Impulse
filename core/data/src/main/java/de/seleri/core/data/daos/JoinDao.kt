package de.seleri.core.data.daos

import de.seleri.core.data.entities.joins.JoinEntity

interface JoinDao<J: JoinEntity> {

	suspend fun insert(joinEntity: J)
	suspend fun delete(joinEntity: J)

	suspend fun getAllConnections(sammlungsId: Int): List<J>
}
