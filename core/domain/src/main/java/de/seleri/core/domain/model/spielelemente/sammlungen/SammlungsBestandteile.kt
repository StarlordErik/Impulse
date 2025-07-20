package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.common.BestandteilID

abstract class SammlungsBestandteile<ID: BestandteilID>(
	val originaleBestandteile: Collection<ID>,
	val inaktiveBestandteile: Collection<ID>,
	val selbstErstellteBestandteile: Collection<ID>,
): Sammlung
