package de.seleri.core.domain.modell

import de.seleri.core.common.idInt.AbstractEntityIDint
import de.seleri.core.common.idInt.KartentextIDint
import de.seleri.core.common.idInt.KategorieIDint
import de.seleri.core.common.idInt.LokalisierungIDint
import de.seleri.core.common.idInt.SpielIDint
import de.seleri.core.common.idInt.TranslationIDint

@Suppress("MayBeConstant")
object Konstanten {

	private val ENTITY_ID = AbstractEntityIDint(0)

	val TRANSLATION_ID = TranslationIDint(ENTITY_ID.id)
	val LOKALISIERUNG_ID = LokalisierungIDint(ENTITY_ID.id)

	val KARTENTEXT_ID = KartentextIDint(ENTITY_ID.id)
	val KATEGORIE_ID = KategorieIDint(ENTITY_ID.id)
	val SPIEL_ID = SpielIDint(ENTITY_ID.id)


	val BEARBEITET = false
	val SELBST_ERSTELLT = false
	val INAKTIV = false
	val FAVORISIERT = false

	val GESEHEN = false
	val BESPROCHEN = false

	val BILD_DATEINAME = null
	val ANLEITUNG = null
	val TEXTE_PRO_KARTE = 1
}
