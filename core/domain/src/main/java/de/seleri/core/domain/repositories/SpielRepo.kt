package de.seleri.core.domain.repositories

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaObjekt
import de.seleri.core.domain.repositories.basis.SammlungRepo
import de.seleri.core.domain.repositories.basis.SpielelementRepo

interface SpielRepo: SpielelementRepo<Spiel, SpielelementID.SpielID>, SammlungRepo<Kategorie, Spiel> {

	suspend fun getAllMetas(): Collection<SpielMetaObjekt>
}
