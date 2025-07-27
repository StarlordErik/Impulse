package de.seleri.core.domain.modell

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.EntityIDint

@Suppress("MayBeConstant")
object Konstanten {

	val ID = EntityIDint(0)

	val SPRACHE = Sprache.OG
	val BEARBEITET = false

	val OG_SPRACHE = Sprache.DE
	val SELBST_ERSTELLT = false
	val INAKTIV = false
	val FAVORISIERT = false

	val GESEHEN = false
	val BESPROCHEN = false

	val BILD_DATEINAME = null
	val ANLEITUNG = null
	val TEXTE_PRO_KARTE = 1
}
