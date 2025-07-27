package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.TranslationIDint
import de.seleri.core.domain.modell.Translation

const val TRANSLATION_ID_BOOSTER = 1000000

data class TranslationEingabe(
	val id: TranslationIDint, val sprache: Sprache = Sprache.OG, val bezeichnung: String
)

fun generiereTranslation(
	translationEingabe: TranslationEingabe
): Translation =
	Translation(
		id = translationEingabe.id,
		sprache = translationEingabe.sprache,
		bezeichnung = translationEingabe.bezeichnung,
	)
