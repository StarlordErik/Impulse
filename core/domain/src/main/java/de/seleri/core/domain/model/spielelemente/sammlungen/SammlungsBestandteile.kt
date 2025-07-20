package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.common.BestandteilFK

abstract class SammlungsBestandteile<B: BestandteilFK>(
	val originaleBestandteile: Collection<B>,
	val inaktiveBestandteile: Collection<B>,
	val selbstErstellteBestandteile: Collection<B>,
): Sammlung
