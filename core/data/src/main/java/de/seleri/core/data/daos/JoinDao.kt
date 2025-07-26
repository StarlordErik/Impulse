package de.seleri.core.data.daos

import de.seleri.core.data.entities.joins.JoinEntity

interface JoinDao<J: JoinEntity> {

	suspend fun insert(joinEntity: J)
	suspend fun insert(joinEntities: Collection<J>)
	suspend fun delete(joinEntity: J)
}
