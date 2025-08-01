package de.seleri.core.domain.modell.idEntity

import de.seleri.core.common.entities.singles.SingleEntity
import de.seleri.core.common.ids.EntityID
import de.seleri.core.domain.modell.ModellEntity

interface IDentity<EID: EntityID>: ModellEntity, SingleEntity<EID>
