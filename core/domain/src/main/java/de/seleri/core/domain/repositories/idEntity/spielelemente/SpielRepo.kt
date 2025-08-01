package de.seleri.core.domain.repositories.idEntity.spielelemente

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.SpielMetaDO

interface SpielRepo: SpielelementRepo<Spiel, SpielID> {

	suspend fun getAllMetas(): Collection<SpielMetaDO>
}
