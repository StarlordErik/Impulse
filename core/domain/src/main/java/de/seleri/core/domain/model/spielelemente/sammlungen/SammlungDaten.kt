package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.Bestandteil

data class SammlungDaten<B: Bestandteil>(
	override val bestandteile: Collection<B>,
): Sammlung<B>
