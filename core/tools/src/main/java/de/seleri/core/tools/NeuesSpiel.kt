package de.seleri.core.tools

import de.seleri.core.common.Sprache
import de.seleri.core.domain.mapper.eingabeUtils.fromSkript
import de.seleri.core.domain.modell.Translation

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

	// und jetzt die Verwandlung zum Modell:

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

//	checkDurchAusgabeInDatei()
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
