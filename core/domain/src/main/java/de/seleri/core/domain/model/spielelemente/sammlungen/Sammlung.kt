package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.Bestandteil

interface Sammlung<B: Bestandteil> {

	val bestandteile: Collection<B>
}
