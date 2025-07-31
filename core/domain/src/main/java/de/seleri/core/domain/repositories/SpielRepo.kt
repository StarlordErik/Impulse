package de.seleri.core.domain.repositories

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDO
import de.seleri.core.domain.repositories.base.SpielelementRepo

interface SpielRepo: SpielelementRepo<Spiel, SpielID> {

	suspend fun getAllMetas(): Collection<SpielMetaDO>
}
