package de.seleri.core.data.daos.joins

import de.seleri.core.data.daos.EntityDAO
import de.seleri.core.data.entities.joins.JoinRoom

interface JoinDAO<J: JoinRoom<*, *>>: EntityDAO<J>
