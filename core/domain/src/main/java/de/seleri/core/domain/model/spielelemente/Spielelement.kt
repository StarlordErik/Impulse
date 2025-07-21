package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.DatenbankObjekt
import de.seleri.core.domain.model.ids.BestandteilID

interface Spielelement: DatenbankObjekt {

	val lokalisierungen: Collection<BestandteilID.LokalisierungID>

	val ogSprache: Sprache
	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean
}
