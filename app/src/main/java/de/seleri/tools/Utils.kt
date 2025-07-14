package de.seleri.tools

import de.seleri.frontend.ImpulseViewModel
import de.seleri.spielelemente.DATENBANK_DATEIFORMAT
import de.seleri.spielelemente.DATENBANK_NAME
import de.seleri.spielelemente.DATENBANK_PFAD
import de.seleri.spielelemente.Datenbanksystem
import java.io.File
import java.nio.file.Paths

/**
 * Erstellt ein neues `Datenbanksystem` auf Basis der Datei im Ressourcenverzeichnis.
 * **Funktioniert nur zur Entwicklungszeit!**
 *
 * @return Instanz des Datenbanksystems mit geladenen Daten
 */
fun Datenbanksystem.Companion.generieren(): Datenbanksystem {
    val dateipfad = Paths.get(DATENBANK_PFAD)
    return Datenbanksystem(dateipfad.toFile())
}

@Suppress("LongMethod")
fun dummyViewModel(): ImpulseViewModel {
    val dummyDatenbank = """
Karten:
  - ID: 1
    Text:
      OG: "Do I look kind? Explain."
      EN: "Do I look kind? Explain."
    gesehen: false
    gelöscht: false
  - ID: 2
    Text:
      OG: "What is my body language telling you right now?"
      EN: "What is my body language telling you right now?"
    gesehen: false
    gelöscht: false
  - ID: 3
    Text:
      OG: "Do I seem like a coffee or tea person? Sweetened or unsweetened?"
      EN: "Do I seem like a coffee or tea person? Sweetened or unsweetened?"
    gesehen: false
    gelöscht: false
  - ID: 4
    Text:
      OG: "Du bist mit einem seltenen Virus infiziert. Ärzte teilen dir mit, dass du nur noch eine Woche zu leben hast. Du bist mobil und frei beweglich.\n\nWie gestaltest du deine letzte Woche?"
      DE: "Du bist mit einem seltenen Virus infiziert. Ärzte teilen dir mit, dass du nur noch eine Woche zu leben hast. Du bist mobil und frei beweglich.\n\nWie gestaltest du deine letzte Woche?"
    gesehen: false
    gelöscht: false
  - ID: 5
    Text:
      OG: "Würdest du lieber ...\n\n ... das Weltall oder den Ozean erkunden?\n ... den ganzen Tag lang Anzug oder Jogginganzug tragen?\n ... alle Sprachen dieser Welt oder alle Instrumente dieser Welt beherrschen?"
      DE: "Würdest du lieber ...\n\n ... das Weltall oder den Ozean erkunden?\n ... den ganzen Tag lang Anzug oder Jogginganzug tragen?\n ... alle Sprachen dieser Welt oder alle Instrumente dieser Welt beherrschen?"
    gesehen: false
    gelöscht: false

Kategorien:
  - ID: 1
    Name:
      OG: "Level 1: Perception"
      EN: "Level 1: Perception"
    originale_Karten:
      IDs: [1,2,3,4,5]
      davon_entfernt: []
    hinzugefügte_Karten-IDs: []
  - ID: 2
    Name:
      OG: "Level 2: Connection"
      EN: "Level 2: Connection"
    originale_Karten:
      IDs: []
      davon_entfernt: []
    hinzugefügte_Karten-IDs: [1,2,3,4,5]
  - ID: 3
    Name:
      OG: "Level 3: Reflection"
      EN: "Level 3: Reflection"
    originale_Karten:
      IDs: []
      davon_entfernt: []
    hinzugefügte_Karten-IDs: [1,2,3,4,5]
  - ID: 4
    Name:
      OG: "Freitextfragen"
      DE: "Freitextfragen"
    originale_Karten:
      IDs: []
      davon_entfernt: []
    hinzugefügte_Karten-IDs: [1,2,3,4,5]
  - ID: 5
    Name:
      OG: "von 0 bis 100"
      DE: "von 0 bis 100"
    originale_Karten:
      IDs: []
      davon_entfernt: []
    hinzugefügte_Karten-IDs: [1,2,3,4,5]

Spiele:
  - ID: 1
    Name:
      OG: "We're not really strangers"
      DE: "Wir sind nicht wirklich Fremde"
      EN: "We're not really strangers"
    originale_Kategorien:
      IDs: [1,2,3]
      davon_entfernt: []
    hinzugefügte_Kategorien-IDs: [4,5]
  - ID: 2
    Name:
      OG: "Fun Facts"
      DE: "Fun Facts"
      EN: "Fun Facts"
    originale_Kategorien:
      IDs: []
      davon_entfernt: []
    hinzugefügte_Kategorien-IDs: []
  - ID: 3
    Name:
      OG: "Erzählt euch mehr"
      DE: "Erzählt euch mehr"
    originale_Kategorien:
      IDs: []
      davon_entfernt: []
    hinzugefügte_Kategorien-IDs: []
"""
    val testDatei = File.createTempFile(DATENBANK_NAME, DATENBANK_DATEIFORMAT)
    testDatei.writeText(dummyDatenbank)

    val dbs = Datenbanksystem(testDatei)

    return ImpulseViewModel(dbs)
}
