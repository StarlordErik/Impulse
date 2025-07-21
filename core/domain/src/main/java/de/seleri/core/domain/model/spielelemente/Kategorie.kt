package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.Bestandteil
import de.seleri.core.domain.model.DEFAULT_ID
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

class Kategorie(
	id: Int = DEFAULT_ID,
	private val spielelementDaten: SpielelementDaten,

	private val sammlungDaten: SammlungDaten<BestandteilID.KartentextID>,
): Spielelement by spielelementDaten, Sammlung<BestandteilID.KartentextID> by sammlungDaten, Bestandteil
