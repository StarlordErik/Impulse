package de.seleri.core.representation.viewModels

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.idEntity.spielelemente.Spielelement

interface SpielelementAlsTextDarstellen {

	val sprache: Sprache
		get() = Sprache.OG // TODO Sprache muss variabel gesetzt werden

	fun getDarstellungAlsText(spielelement: Spielelement?): String =
		spielelement?.getBezeichnung(sprache)
			?: "loading"
}
