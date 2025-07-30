package de.seleri.core.data.daos.joins

import de.seleri.core.data.entities.joins.JoinRoom

interface JoinDao<J: JoinRoom> {

	suspend fun insert(joinEntity: J)
	suspend fun delete(joinEntity: J)

	suspend fun getAllConnections(sammlungsId: Int): List<J>
}
