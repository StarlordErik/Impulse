package de.seleri.core.domain.repositories

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaObjekt
import de.seleri.core.domain.repositories.basis.SammlungRepo
import de.seleri.core.domain.repositories.basis.SpielelementRepo

interface SpielRepo: SpielelementRepo<Spiel, SpielelementID.SpielID>, SammlungRepo<Kategorie, Spiel> {

	suspend fun getAllMetaObjekte(): List<SpielMetaObjekt>
}
