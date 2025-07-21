package de.seleri.core.domain.model.spiele.spielelemente

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.sammlungen.Bestandteil
import de.seleri.core.domain.model.sammlungen.Sammlung
import de.seleri.core.domain.model.sammlungen.SammlungDaten

data class Kategorie(
	private val spielelementDaten: SpielelementDaten,

	private val sammlungDaten: SammlungDaten<BestandteilID.KartentextID>,
): Spielelement by spielelementDaten, Sammlung<BestandteilID.KartentextID> by sammlungDaten, Bestandteil
