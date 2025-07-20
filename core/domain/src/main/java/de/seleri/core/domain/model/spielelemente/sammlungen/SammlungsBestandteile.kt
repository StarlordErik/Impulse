package de.seleri.core.domain.model.spielelemente.sammlungen

abstract class SammlungsBestandteile<FK: Fremdschluessel>(
	val originaleBestandteile: Collection<FK>,
	val inaktiveBestandteile: Collection<FK>,
	val selbstErstellteBestandteile: Collection<FK>,
): Sammlung
