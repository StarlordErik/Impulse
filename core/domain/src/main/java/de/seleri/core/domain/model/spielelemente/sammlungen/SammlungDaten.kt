package de.seleri.core.domain.model.spielelemente.sammlungen

data class SammlungDaten<B: Bestandteil>(
	override val bestandteile: Collection<B>,
): Sammlung<B>
