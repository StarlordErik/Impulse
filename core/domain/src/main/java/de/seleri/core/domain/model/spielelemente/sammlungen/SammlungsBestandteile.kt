package de.seleri.core.domain.model.spielelemente.sammlungen

abstract class SammlungsBestandteile<B: Bestandteil>(
	val originaleBestandteile: Collection<B>,
	val inaktiveBestandteile: Collection<B>,
	val selbstErstellteBestandteile: Collection<B>,
) : Sammlung<B>
