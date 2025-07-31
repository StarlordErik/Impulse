package de.seleri.core.domain.modell

import de.seleri.core.common.entities.singles.SingleEntity
import de.seleri.core.common.ids.EntityID

interface IDable<EID: EntityID>: ModellEntity, SingleEntity<EID>
