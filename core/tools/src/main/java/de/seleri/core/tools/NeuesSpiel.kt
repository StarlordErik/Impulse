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

	println("Zeit für ein neues Spiel!\n.\n.\n.")

	// Schau in die Datenbank; Was ist die höchste, vergebene ID der jeweiligen Tabellen?

	var maxTranslationID = 0
	var maxLokalisierungID = 0
	var maxKartentextID = 0
	var maxKategorieID = 0
	var maxSpielID = 0

	// In welcher Sprache ist das Spiel geschrieben?

	val ogSprache = Sprache.DE

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

	val kartentexte1TexteOG: List<String> = listOf("Gedanken", "spiel")
	val kartentexte1TexteERIK: List<String> = listOf()
	val kartentexte1TexteDE: List<String> = listOf()
	val kartentexte1TexteEN: List<String> = listOf()

	val kartentexte2TexteOG: List<String> = listOf("Kreuz", "verhör")
	val kartentexte2TexteERIK: List<String> = listOf()
	val kartentexte2TexteDE: List<String> = listOf()
	val kartentexte2TexteEN: List<String> = listOf()

	val kartentexte3TexteOG: List<String> = listOf("Selbst", "reflexion")
	val kartentexte3TexteERIK: List<String> = listOf()
	val kartentexte3TexteDE: List<String> = listOf()
	val kartentexte3TexteEN: List<String> = listOf()

	val kartentexte4TexteOG: List<String> = listOf()
	val kartentexte4TexteERIK: List<String> = listOf()
	val kartentexte4TexteDE: List<String> = listOf()
	val kartentexte4TexteEN: List<String> = listOf()

	// ------------------------------------ EINGABE BIS HIERHIN UND NICHT WEITER ------------------------------------

	val startTranslationsID = maxTranslationID
	val startLokalisierungID = maxLokalisierungID
	val startKartentextID = maxKartentextID
	val startKategorieID = maxKategorieID
	val startSpielID = maxSpielID

	// Eingabe to Translationen - fürs Spiel:

	val spielOGinfo = Translation.fromSkript(++maxTranslationID, Sprache.OG, spielNameOG)
	val spielOG = spielOGinfo.first
	maxTranslationID += spielOGinfo.second

	val spielERIKinfo = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, spielNameERIK)
	val spielERIK = spielERIKinfo.first
	maxTranslationID += spielERIKinfo.second

	val spielDEinfo = Translation.fromSkript(++maxTranslationID, Sprache.DE, spielNameDE)
	val spielDE = spielDEinfo.first
	maxTranslationID += spielDEinfo.second

	val spielENinfo = Translation.fromSkript(++maxTranslationID, Sprache.EN, spielNameEN)
	val spielEN = spielENinfo.first
	maxTranslationID += spielENinfo.second

	// Eingabe to Translationen - für die Kategorien:

	val kategorie1OGinfo = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie1NameOG)
	val kategorie1OG = kategorie1OGinfo.first
	maxTranslationID += kategorie1OGinfo.second

	val kategorie1ERIKinfo = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie1NameERIK)
	val kategorie1ERIK = kategorie1ERIKinfo.first
	maxTranslationID += kategorie1ERIKinfo.second

	val kategorie1DEinfo = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie1NameDE)
	val kategorie1DE = kategorie1DEinfo.first
	maxTranslationID += kategorie1DEinfo.second

	val kategorie1ENinfo = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie1NameEN)
	val kategorie1EN = kategorie1ENinfo.first
	maxTranslationID += kategorie1ENinfo.second

	val kategorie2OGinfo = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie2NameOG)
	val kategorie2OG = kategorie2OGinfo.first
	maxTranslationID += kategorie2OGinfo.second

	val kategorie2ERIKinfo = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie2NameERIK)
	val kategorie2ERIK = kategorie2ERIKinfo.first
	maxTranslationID += kategorie2ERIKinfo.second

	val kategorie2DEinfo = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie2NameDE)
	val kategorie2DE = kategorie2DEinfo.first
	maxTranslationID += kategorie2DEinfo.second

	val kategorie2ENinfo = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie2NameEN)
	val kategorie2EN = kategorie2ENinfo.first
	maxTranslationID += kategorie2ENinfo.second

	val kategorie3OGinfo = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie3NameOG)
	val kategorie3OG = kategorie3OGinfo.first
	maxTranslationID += kategorie3OGinfo.second

	val kategorie3ERIKinfo = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie3NameERIK)
	val kategorie3ERIK = kategorie3ERIKinfo.first
	maxTranslationID += kategorie3ERIKinfo.second

	val kategorie3DEinfo = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie3NameDE)
	val kategorie3DE = kategorie3DEinfo.first
	maxTranslationID += kategorie3DEinfo.second

	val kategorie3ENinfo = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie3NameEN)
	val kategorie3EN = kategorie3ENinfo.first
	maxTranslationID += kategorie3ENinfo.second

	val kategorie4OGinfo = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie4NameOG)
	val kategorie4OG = kategorie4OGinfo.first
	maxTranslationID += kategorie4OGinfo.second

	val kategorie4ERIKinfo = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie4NameERIK)
	val kategorie4ERIK = kategorie4ERIKinfo.first
	maxTranslationID += kategorie4ERIKinfo.second

	val kategorie4DEinfo = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie4NameDE)
	val kategorie4DE = kategorie4DEinfo.first
	maxTranslationID += kategorie4DEinfo.second

	val kategorie4ENinfo = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie4NameEN)
	val kategorie4EN = kategorie4ENinfo.first
	maxTranslationID += kategorie4ENinfo.second

	// Eingabe to Translationen - für die Kartentexte:

	val kartentexte1OGinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.OG, kartentexte1TexteOG)
	val kartentexte1OG = kartentexte1OGinfo.first
	maxTranslationID += kartentexte1OGinfo.second

	val kartentexte1ERIKinfo =
		Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.ERIK, kartentexte1TexteERIK)
	val kartentexte1ERIK = kartentexte1ERIKinfo.first
	maxTranslationID += kartentexte1ERIKinfo.second

	val kartentexte1DEinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.DE, kartentexte1TexteDE)
	val kartentexte1DE = kartentexte1DEinfo.first
	maxTranslationID += kartentexte1DEinfo.second

	val kartentexte1ENinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.EN, kartentexte1TexteEN)
	val kartentexte1EN = kartentexte1ENinfo.first
	maxTranslationID += kartentexte1ENinfo.second

	val kartentexte2OGinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.OG, kartentexte2TexteOG)
	val kartentexte2OG = kartentexte2OGinfo.first
	maxTranslationID += kartentexte2OGinfo.second

	val kartentexte2ERIKinfo =
		Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.ERIK, kartentexte2TexteERIK)
	val kartentexte2ERIK = kartentexte2ERIKinfo.first
	maxTranslationID += kartentexte2ERIKinfo.second

	val kartentexte2DEinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.DE, kartentexte2TexteDE)
	val kartentexte2DE = kartentexte2DEinfo.first
	maxTranslationID += kartentexte2DEinfo.second

	val kartentexte2ENinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.EN, kartentexte2TexteEN)
	val kartentexte2EN = kartentexte2ENinfo.first
	maxTranslationID += kartentexte2ENinfo.second

	val kartentexte3OGinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.OG, kartentexte3TexteOG)
	val kartentexte3OG = kartentexte3OGinfo.first
	maxTranslationID += kartentexte3OGinfo.second

	val kartentexte3ERIKinfo =
		Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.ERIK, kartentexte3TexteERIK)
	val kartentexte3ERIK = kartentexte3ERIKinfo.first
	maxTranslationID += kartentexte3ERIKinfo.second

	val kartentexte3DEinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.DE, kartentexte3TexteDE)
	val kartentexte3DE = kartentexte3DEinfo.first
	maxTranslationID += kartentexte3DEinfo.second

	val kartentexte3ENinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.EN, kartentexte3TexteEN)
	val kartentexte3EN = kartentexte3ENinfo.first
	maxTranslationID += kartentexte3ENinfo.second

	val kartentexte4OGinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.OG, kartentexte4TexteOG)
	val kartentexte4OG = kartentexte4OGinfo.first
	maxTranslationID += kartentexte4OGinfo.second

	val kartentexte4ERIKinfo =
		Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.ERIK, kartentexte4TexteERIK)
	val kartentexte4ERIK = kartentexte4ERIKinfo.first
	maxTranslationID += kartentexte4ERIKinfo.second

	val kartentexte4DEinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.DE, kartentexte4TexteDE)
	val kartentexte4DE = kartentexte4DEinfo.first
	maxTranslationID += kartentexte4DEinfo.second

	val kartentexte4ENinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.EN, kartentexte4TexteEN)
	val kartentexte4EN = kartentexte4ENinfo.first
	maxTranslationID += kartentexte4ENinfo.second

	// Translationen in Lokalisierung gruppieren - für das Spiel:

	val spielLokalisierungInfo =
		Lokalisierung.fromSkript(++maxLokalisierungID, ogSprache, spielOG, spielERIK, spielDE, spielEN)
	val spielLokalisierung = spielLokalisierungInfo.first
	maxLokalisierungID += spielLokalisierungInfo.second
	maxTranslationID += spielLokalisierungInfo.third

	// Translationen in Lokalisierung gruppieren - für die Kategorien:

	val kategorie1LokalisierungInfo = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie1OG, kategorie1ERIK, kategorie1DE, kategorie1EN
	)
	val kategorie1Lokalisierung = kategorie1LokalisierungInfo.first
	maxLokalisierungID += kategorie1LokalisierungInfo.second
	maxTranslationID += kategorie1LokalisierungInfo.third

	val kategorie2LokalisierungInfo = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie2OG, kategorie2ERIK, kategorie2DE, kategorie2EN
	)
	val kategorie2Lokalisierung = kategorie2LokalisierungInfo.first
	maxLokalisierungID += kategorie2LokalisierungInfo.second
	maxTranslationID += kategorie2LokalisierungInfo.third

	val kategorie3LokalisierungInfo = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie3OG, kategorie3ERIK, kategorie3DE, kategorie3EN
	)
	val kategorie3Lokalisierung = kategorie3LokalisierungInfo.first
	maxLokalisierungID += kategorie3LokalisierungInfo.second
	maxTranslationID += kategorie3LokalisierungInfo.third

	val kategorie4LokalisierungInfo = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie4OG, kategorie4ERIK, kategorie4DE, kategorie4EN
	)
	val kategorie4Lokalisierung = kategorie4LokalisierungInfo.first
	maxLokalisierungID += kategorie4LokalisierungInfo.second
	maxTranslationID += kategorie4LokalisierungInfo.third

	// Translationen in Lokalisierung gruppieren - für die Kartentexte:

	val kartentexte1LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte1OG, kartentexte1ERIK, kartentexte1DE, kartentexte1EN
	)
	val kartentexte1Lokalisierungen = kartentexte1LokalisierungsInfos.first
	maxLokalisierungID += kartentexte1LokalisierungsInfos.second
	maxTranslationID += kartentexte1LokalisierungsInfos.third

	val kartentexte2LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte2OG, kartentexte2ERIK, kartentexte2DE, kartentexte2EN
	)
	val kartentexte2Lokalisierungen = kartentexte2LokalisierungsInfos.first
	maxLokalisierungID += kartentexte2LokalisierungsInfos.second
	maxTranslationID += kartentexte2LokalisierungsInfos.third

	val kartentexte3LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte3OG, kartentexte3ERIK, kartentexte3DE, kartentexte3EN
	)
	val kartentexte3Lokalisierungen = kartentexte3LokalisierungsInfos.first
	maxLokalisierungID += kartentexte3LokalisierungsInfos.second
	maxTranslationID += kartentexte3LokalisierungsInfos.third

	val kartentexte4LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte4OG, kartentexte4ERIK, kartentexte4DE, kartentexte4EN
	)
	val kartentexte4Lokalisierungen = kartentexte4LokalisierungsInfos.first
	maxLokalisierungID += kartentexte4LokalisierungsInfos.second
	maxTranslationID += kartentexte4LokalisierungsInfos.third

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

	val kategorie1Info = Kategorie.fromSkript(++maxKategorieID, kategorie1Lokalisierung, kartentexte1)
	val kategorie1 = kategorie1Info.first
	maxKategorieID += kategorie1Info.second

	val kategorie2Info = Kategorie.fromSkript(++maxKategorieID, kategorie2Lokalisierung, kartentexte2)
	val kategorie2 = kategorie2Info.first
	maxKategorieID += kategorie2Info.second

	val kategorie3Info = Kategorie.fromSkript(++maxKategorieID, kategorie3Lokalisierung, kartentexte3)
	val kategorie3 = kategorie3Info.first
	maxKategorieID += kategorie3Info.second

	val kategorie4Info = Kategorie.fromSkript(++maxKategorieID, kategorie4Lokalisierung, kartentexte4)
	val kategorie4 = kategorie4Info.first
	maxKategorieID += kategorie4Info.second

	val kategorien = entferneNullerKategorien(listOf(kategorie1, kategorie2, kategorie3, kategorie4))

	// Kategorien zu Spiel (mit Lokalisierungen):

	val spiel = Spiel.fromSkript(++maxSpielID, spielLokalisierung, kategorien)

	println("Fertig!\n")

	// ---------------------------------- SPIEL WURDE ERSTELLT - NUN ZUR AUSWERTUNG ----------------------------------

	println("\"$spielNameOG\" wurde erstellt! \n")

	val anzahlSpiele = maxSpielID - startSpielID
	println("Statistik für $anzahlSpiele Spiel:")

	val anzahlKategorien = maxKategorieID - startKategorieID
	println("\t$anzahlKategorien Kategorien")

	val anzahlKartentexte = maxKartentextID - startKartentextID
	println("\t$anzahlKartentexte Kartentexte")

	val anzahlLokalisierungen = maxLokalisierungID - startLokalisierungID
	println("\t$anzahlLokalisierungen Lokalisierungen")

	val anzahlTranslationen = maxTranslationID - startTranslationsID
	println("\t$anzahlTranslationen Translationen")

	/*
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


 */
}
