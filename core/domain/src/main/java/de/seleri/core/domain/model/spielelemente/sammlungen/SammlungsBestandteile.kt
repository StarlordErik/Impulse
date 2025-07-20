package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.ids.BestandteilID

abstract class SammlungsBestandteile<ID: BestandteilID>(
	val originaleBestandteile: Collection<ID>,
	val inaktiveBestandteile: Collection<ID>,
	val selbstErstellteBestandteile: Collection<ID>,
): Sammlung
