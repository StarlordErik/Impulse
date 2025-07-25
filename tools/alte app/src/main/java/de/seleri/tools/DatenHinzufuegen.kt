package de.seleri.tools

import de.seleri.backend.Datenbanksystem
import de.seleri.backend.Sprachen

/**
 * um neue Daten in die Datenbank einzufügen
 */
@Suppress("LongMethod", "MaxLineLength")
fun main() {

  println("Hallo Welt!\n")

  // Hier die Daten eingeben - für bestehende Sammlungen die Namen aus der Datenbank kopieren:

  val spielName = "Erzählt euch mehr - für Paare"
  val kategorieName = "Reflexion"
  val sprache = Sprachen.DE
  val kartenTexte = listOf(
    "Sex"
  )

  // oben die Daten eingeben

  val dbs = Datenbanksystem.generieren()

  val neueKarten = dbs.neueKarten(kartenTexte, sprache)
  println("Folgende KartenIDs wurden eingegeben: ${neueKarten.map {it.id}}")

  val neueKategorie = dbs.neueKategorie(kategorieName, neueKarten, sprache)
  println("Folgende KategorienID wurden eingegeben: ${neueKategorie.id}:${neueKategorie.localizations[sprache]}")
  println("Sie besitzt folgende Karten:          ${neueKategorie.getKarten().map {it.id}}")

  val neuesSpiel = dbs.neuesSpiel(spielName, listOf(neueKategorie), sprache)
  println("Folgende SpielID wurde eingegeben: ${neuesSpiel.id}:${neuesSpiel.localizations[sprache]}")
  println("Das Spiel besitzt folgende Kategorien: ${neuesSpiel.getKategorien().map {it.localizations[sprache]}}")
}


