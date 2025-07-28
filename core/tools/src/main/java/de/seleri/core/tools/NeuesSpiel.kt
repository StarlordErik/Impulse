package de.seleri.core.tools

import de.seleri.core.common.Sprache
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.Translation
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.modell.spielelemente.spiel.Spiel
import de.seleri.core.tools.fromSkriptUtils.entferneNullerKategorien
import de.seleri.core.tools.fromSkriptUtils.fromSkript
import de.seleri.core.tools.fromSkriptUtils.fromSkriptForAll
import de.seleri.core.tools.fromSkriptUtils.fromSkriptForKartentexte

@Suppress("MaxLineLength", "LongMethod")
fun main() {

	println("Zeit für ein neues Spiel!")

	val ogSprache = Sprache.DE

	var maxTranslationID = 0
	var maxLokalisierungID = 0
	var maxKartentextID = 0
	var maxKategorieID = 0
	var maxSpielID = 0

	// Spiel:

	val spielNameOG: String = "Erzählt euch mehr - Klassik"
	val spielNameERIK: String = ""
	val spielNameDE: String = ""
	val spielNameEN: String = ""

	// Kategorien:

	val kategorie1NameOG: String = "Gedankenspiel"
	val kategorie1NameERIK: String = ""
	val kategorie1NameDE: String = ""
	val kategorie1NameEN: String = ""

	val kategorie2NameOG: String = "Kreuzverhör"
	val kategorie2NameERIK: String = ""
	val kategorie2NameDE: String = ""
	val kategorie2NameEN: String = ""

	val kategorie3NameOG: String = "Selbstreflexion"
	val kategorie3NameERIK: String = ""
	val kategorie3NameDE: String = ""
	val kategorie3NameEN: String = ""

	val kategorie4NameOG: String = ""
	val kategorie4NameERIK: String = ""
	val kategorie4NameDE: String = ""
	val kategorie4NameEN: String = ""

	// Kartentexte:

	val kartentext1TexteOG: List<String> = listOf("Sex", "mehr Sex")
	val kartentext1TexteERIK: List<String> = listOf()
	val kartentext1TexteDE: List<String> = listOf()
	val kartentext1TexteEN: List<String> = listOf()

	val kartentext2TexteOG: List<String> = listOf("Sex", "mehr Sex")
	val kartentext2TexteERIK: List<String> = listOf()
	val kartentext2TexteDE: List<String> = listOf()
	val kartentext2TexteEN: List<String> = listOf()

	val kartentext3TexteOG: List<String> = listOf("Sex", "mehr Sex")
	val kartentext3TexteERIK: List<String> = listOf()
	val kartentext3TexteDE: List<String> = listOf()
	val kartentext3TexteEN: List<String> = listOf()

	val kartentext4TexteOG: List<String> = listOf()
	val kartentext4TexteERIK: List<String> = listOf()
	val kartentext4TexteDE: List<String> = listOf()
	val kartentext4TexteEN: List<String> = listOf()

	// ------------------------------------ EINGABE BIS HIERHIN UND NICHT WEITER ------------------------------------

	// Eingabe to Translationen:

	val spielOG = Translation.fromSkript(++maxTranslationID, Sprache.OG, spielNameOG)
	val spielERIK = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, spielNameERIK)
	val spielDE = Translation.fromSkript(++maxTranslationID, Sprache.DE, spielNameDE)
	val spielEN = Translation.fromSkript(++maxTranslationID, Sprache.EN, spielNameEN)

	val kategorie1OG = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie1NameOG)
	val kategorie1ERIK = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie1NameERIK)
	val kategorie1DE = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie1NameDE)
	val kategorie1EN = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie1NameEN)

	val kategorie2OG = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie2NameOG)
	val kategorie2ERIK = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie2NameERIK)
	val kategorie2DE = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie2NameDE)
	val kategorie2EN = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie2NameEN)

	val kategorie3OG = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie3NameOG)
	val kategorie3ERIK = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie3NameERIK)
	val kategorie3DE = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie3NameDE)
	val kategorie3EN = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie3NameEN)

	val kategorie4OG = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie4NameOG)
	val kategorie4ERIK = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie4NameERIK)
	val kategorie4DE = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie4NameDE)
	val kategorie4EN = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie4NameEN)

	val kartentexte1OG = kartentext1TexteOG.map { Translation.fromSkript(++maxTranslationID, Sprache.OG, it) }
	val kartentexte1ERIK = kartentext1TexteERIK.map { Translation.fromSkript(++maxTranslationID, Sprache.ERIK, it) }
	val kartentexte1DE = kartentext1TexteDE.map { Translation.fromSkript(++maxTranslationID, Sprache.DE, it) }
	val kartentexte1EN = kartentext1TexteEN.map { Translation.fromSkript(++maxTranslationID, Sprache.EN, it) }

	val kartentexte2OG = kartentext2TexteOG.map { Translation.fromSkript(++maxTranslationID, Sprache.OG, it) }
	val kartentexte2ERIK = kartentext2TexteERIK.map { Translation.fromSkript(++maxTranslationID, Sprache.ERIK, it) }
	val kartentexte2DE = kartentext2TexteDE.map { Translation.fromSkript(++maxTranslationID, Sprache.DE, it) }
	val kartentexte2EN = kartentext2TexteEN.map { Translation.fromSkript(++maxTranslationID, Sprache.EN, it) }

	val kartentexte3OG = kartentext3TexteOG.map { Translation.fromSkript(++maxTranslationID, Sprache.OG, it) }
	val kartentexte3ERIK = kartentext3TexteERIK.map { Translation.fromSkript(++maxTranslationID, Sprache.ERIK, it) }
	val kartentexte3DE = kartentext3TexteDE.map { Translation.fromSkript(++maxTranslationID, Sprache.DE, it) }
	val kartentexte3EN = kartentext3TexteEN.map { Translation.fromSkript(++maxTranslationID, Sprache.EN, it) }

	val kartentexte4OG = kartentext4TexteOG.map { Translation.fromSkript(++maxTranslationID, Sprache.OG, it) }
	val kartentexte4ERIK = kartentext4TexteERIK.map { Translation.fromSkript(++maxTranslationID, Sprache.ERIK, it) }
	val kartentexte4DE = kartentext4TexteDE.map { Translation.fromSkript(++maxTranslationID, Sprache.DE, it) }
	val kartentexte4EN = kartentext4TexteEN.map { Translation.fromSkript(++maxTranslationID, Sprache.EN, it) }

	// Translationen in Lokalisierung gruppieren:

	val spielLokalisierung =
		Lokalisierung.fromSkript(++maxLokalisierungID, ogSprache, spielOG, spielERIK, spielDE, spielEN)

	val kategorie1Lokalisierung = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie1OG, kategorie1ERIK, kategorie1DE, kategorie1EN
	)
	val kategorie2Lokalisierung = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie2OG, kategorie2ERIK, kategorie2DE, kategorie2EN
	)
	val kategorie3Lokalisierung = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie3OG, kategorie3ERIK, kategorie3DE, kategorie3EN
	)
	val kategorie4Lokalisierung = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie4OG, kategorie4ERIK, kategorie4DE, kategorie4EN
	)

	val kartentexte1LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte1OG, kartentexte1ERIK, kartentexte1DE, kartentexte1EN
	)
	val kartentexte1Lokalisierungen = kartentexte1LokalisierungsInfos.first
	maxLokalisierungID += kartentexte1LokalisierungsInfos.second

	val kartentexte2LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte2OG, kartentexte2ERIK, kartentexte2DE, kartentexte2EN
	)
	val kartentexte2Lokalisierungen = kartentexte2LokalisierungsInfos.first
	maxLokalisierungID += kartentexte2LokalisierungsInfos.second

	val kartentexte3LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte3OG, kartentexte3ERIK, kartentexte3DE, kartentexte3EN
	)
	val kartentexte3Lokalisierungen = kartentexte3LokalisierungsInfos.first
	maxLokalisierungID += kartentexte3LokalisierungsInfos.second

	val kartentexte4LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte4OG, kartentexte4ERIK, kartentexte4DE, kartentexte4EN
	)
	val kartentexte4Lokalisierungen = kartentexte4LokalisierungsInfos.first
	maxLokalisierungID += kartentexte4LokalisierungsInfos.second

	// Lokalisierungen zu Kartentexten:

	val kartentexte1Infos = Kartentext.fromSkriptForAll(++maxKartentextID, kartentexte1Lokalisierungen)
	val kartentexte1 = kartentexte1Infos.first
	maxKartentextID += kartentexte1Infos.second

	val kartentexte2Infos = Kartentext.fromSkriptForAll(++maxKartentextID, kartentexte2Lokalisierungen)
	val kartentexte2 = kartentexte2Infos.first
	maxKartentextID += kartentexte2Infos.second

	val kartentexte3Infos = Kartentext.fromSkriptForAll(++maxKartentextID, kartentexte3Lokalisierungen)
	val kartentexte3 = kartentexte3Infos.first
	maxKartentextID += kartentexte3Infos.second

	val kartentexte4Infos = Kartentext.fromSkriptForAll(++maxKartentextID, kartentexte4Lokalisierungen)
	val kartentexte4 = kartentexte4Infos.first
	maxKartentextID += kartentexte4Infos.second

	// Kartentexte zu Kategorien (mit Lokalisierungen):

	val kategorie1 = Kategorie.fromSkript(++maxKategorieID, kategorie1Lokalisierung, kartentexte1)
	val kategorie2 = Kategorie.fromSkript(++maxKategorieID, kategorie2Lokalisierung, kartentexte2)
	val kategorie3 = Kategorie.fromSkript(++maxKategorieID, kategorie3Lokalisierung, kartentexte3)
	val kategorie4 = Kategorie.fromSkript(++maxKategorieID, kategorie4Lokalisierung, kartentexte4)

	val kategorien = entferneNullerKategorien(listOf(kategorie1, kategorie2, kategorie3, kategorie4))

	// Kategorien zu Spiel (mit Lokalisierungen):

	val spiel = Spiel.fromSkript(++maxSpielID, spielLokalisierung, kategorien)

//	checkDurchAusgabeInDatei(spiel)
}

/*
private fun checkDurchAusgabeInDatei(spiel: Spiel) {
	val outputFile = File("core/tools/build/outputs/neues_Spiel.txt")
	outputFile.parentFile.mkdirs()

	val content = buildString {
		val spielLokalisierungen = spiel.lokalisierung.map { lokalisierung ->
			lokalisierung.sprache to lokalisierung.bezeichnung
		}
		appendLine("Folgendes Spiel wurde erstellt:")
		appendLine("\"${spielLokalisierungen.first().second}\" in den Sprachen: ${spielLokalisierungen.map { it.first }}")
		appendLine()

		val kategorieLokalisierungen = spiel.bestandteile.map { kategorie ->
			kategorie.lokalisierung.first().bezeichnung
		}
		appendLine("mit den Kategorien:")
		appendLine(kategorieLokalisierungen)
		appendLine()

		val kartentextLokalisierungen = spiel.bestandteile.map { kategorie ->
			kategorie.bestandteile.map { kartentext ->
				kartentext.lokalisierung.map { lokalisierung ->
					lokalisierung.bezeichnung.replace("\n", "\\n")
				}
			}
		}

		val kmkLokalisierungen = kategorieLokalisierungen.zip(kartentextLokalisierungen)

		kmkLokalisierungen.forEach { kategorieMitKartentexten ->
			appendLine("${kategorieMitKartentexten.first}:")
			kategorieMitKartentexten.second.forEach { kartentext ->
				appendLine("\"${kartentext.first()}\",")
			}
			appendLine()
		}
	}

	outputFile.writeText(content)
	val uri =
		outputFile
			.toPath()
			.toUri()
			.toString()
	println("Inhalte des Spiels gespeichert in: $uri")
}

 */
