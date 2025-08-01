package de.seleri.core.domain.model.idEntity

import de.seleri.core.common.entities.singles.SingleEntity
import de.seleri.core.common.ids.EntityID
import de.seleri.core.domain.model.ModelEntity

interface IDentity<EID: EntityID>: ModelEntity, SingleEntity<EID>
