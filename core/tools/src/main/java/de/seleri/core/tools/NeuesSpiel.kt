package de.seleri.core.tools

import de.seleri.core.common.Sprache

@Suppress("MaxLineLength", "LongMethod")
fun main() {

	val ogSprache = Sprache.DE

	val spielNameOG: String = "Erzählt euch mehr - Klassik"
	val spielNameERIK: String? = null
	val spielNameDE: String? = null
	val spielNameEN: String? = null

	// Kategorien:

	val kategorie1NameOG: String = "Gedankenspiel"
	val kategorie1NameERIK: String? = null
	val kategorie1NameDE: String? = null
	val kategorie1NameEN: String? = null

	val kategorie2NameOG: String? = "Kreuzverhör"
	val kategorie2NameERIK: String? = null
	val kategorie2NameDE: String? = null
	val kategorie2NameEN: String? = null

	val kategorie3NameOG: String? = "Selbstreflexion"
	val kategorie3NameERIK: String? = null
	val kategorie3NameDE: String? = null
	val kategorie3NameEN: String? = null

	val kategorie4NameOG: String? = null
	val kategorie4NameERIK: String? = null
	val kategorie4NameDE: String? = null
	val kategorie4NameEN: String? = null

	// Kartentexte:

	val kartentext1TexteOG: List<String>? = listOf("Sex", "mehr Sex")
	val kartentext1TexteERIK: List<String>? = null
	val kartentext1TexteDE: List<String>? = null
	val kartentext1TexteEN: List<String>? = null

	val kartentext2TexteOG: List<String>? = listOf("Sex", "mehr Sex")
	val kartentext2TexteERIK: List<String>? = null
	val kartentext2TexteDE: List<String>? = null
	val kartentext2TexteEN: List<String>? = null

	val kartentext3TexteOG: List<String>? = listOf("Sex", "mehr Sex")
	val kartentext3TexteERIK: List<String>? = null
	val kartentext3TexteDE: List<String>? = null
	val kartentext3TexteEN: List<String>? = null

	val kartentext4TexteOG: List<String>? = null
	val kartentext4TexteERIK: List<String>? = null
	val kartentext4TexteDE: List<String>? = null
	val kartentext4TexteEN: List<String>? = null

	var maxTranslationID = 0
	var maxLokalisierungID = 0
	var maxKartentextID = 0
	var maxKategorieID = 0
	var maxSpielID = 0

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
