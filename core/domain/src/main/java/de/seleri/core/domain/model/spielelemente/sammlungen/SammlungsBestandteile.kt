package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.common.DatenbankObjektID

abstract class SammlungsBestandteile<ID: DatenbankObjektID>(
	val originaleBestandteile: Collection<ID>,
	val inaktiveBestandteile: Collection<ID>,
	val selbstErstellteBestandteile: Collection<ID>,
): Sammlung
