package de.seleri.core.data.daos.joins

import de.seleri.core.data.daos.EntityDao
import de.seleri.core.data.entities.joins.JoinRoom

interface JoinDao<J: JoinRoom<*, *>>: EntityDao<J>
