package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.ids.BestandteilID

data class SammlungDaten<ID: BestandteilID>(
	override val bestandteile: Collection<ID>,
): Sammlung<ID> {

	override val sammlungDaten: SammlungDaten<ID> get() = this
}
