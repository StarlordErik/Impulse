package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.ids.BestandteilID

interface Sammlung<ID: BestandteilID> {

	val sammlungDaten: SammlungDaten<ID>

	val bestandteile get() = sammlungDaten.bestandteile
}
