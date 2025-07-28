package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.KategorieIDint
import de.seleri.core.domain.modell.spielelemente.Kategorie

data class KategorieEingabe(
	val kategorieIDint: KategorieIDint, val lokalisierungEingabe: LokalisierungEingabe,
	val kartentexte: Collection<KartentextEingabe>
)

fun generiereKategorie(ogSprache: Sprache, kategorieEingabe: KategorieEingabe): Kategorie =
	Kategorie(
		id = kategorieEingabe.kategorieIDint,
		spielelementDaten = generiereSpielelementDaten(ogSprache, kategorieEingabe.lokalisierungEingabe),
		bestandteile = kategorieEingabe.kartentexte.map { generiereKartentext(ogSprache, it) })
