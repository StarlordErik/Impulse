package de.seleri.core.data.daos.compositePk.joins

import de.seleri.core.data.daos.compositePk.CompositePkDAO
import de.seleri.core.data.entities.joins.JoinRoom

interface JoinDAO<J: JoinRoom>: CompositePkDAO<J>
