package de.seleri.core.domain.mapper.eingabeUtils

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.SpielIDint
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel

data class SpielEingabe(
	val spielIDint: SpielIDint, val lokalisierungEingabe: LokalisierungEingabe,
	val kategorieEingaben: Collection<KategorieEingabe>
)

fun generiereSpiel(ogSprache: Sprache, spielEingabe: SpielEingabe): Spiel =
	Spiel(
		id = spielEingabe.spielIDint,
		spielMetaDaten = generiereSpielMetaDaten(ogSprache, spielEingabe),
		bestandteile = spielEingabe.kategorieEingaben.map { generiereKategorie(ogSprache, it) })
