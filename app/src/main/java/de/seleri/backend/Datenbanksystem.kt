package de.seleri.backend

import android.content.Context
import de.seleri.frontend.R
import org.yaml.snakeyaml.Yaml
import java.io.File

// Datenbank-Pfade
/**
 * Dateiname der Datenbank-Datei ohne Endung
 */
const val DATENBANK_NAME = "datenbank"

/**
 * Endung der Datenbank-Datei
 */
const val DATENBANK_DATEIFORMAT = ".yml"

/**
 * Dateiname der Datenbank-Datei mit Endung
 */
const val DATENBANK_DATEI = "$DATENBANK_NAME$DATENBANK_DATEIFORMAT"

/**
 * Pfad zur Datenbank-Datei
 */
const val DATENBANK_PFAD = "app/src/main/res/raw/$DATENBANK_DATEI"

/**
 * Das `Datenbanksystem` verwaltet eine YAML-basierte Datenbank für `Karte`n, `Kategorie`n und `Spiel`e.
 * Diese Datenbank wird beim Initialisieren geparst und im Speicher als Objekte dargestellt.
 * Änderungen an den Objekten werden in die YAML-Datei zurückgeschrieben.
 *
 *
 * @property datenbank Die YAML-Datei, die als Datenbank dient.
 * @constructor Initialisiert das Datenbanksystem mit der gegebenen YAML-Datei,
 * indem es die Inhalte einliest und sie in Objekte deserialisiert.
 */
class Datenbanksystem(val datenbank: File) {

  /**
   * Menge aller ausgelesenen Karten
   */
  val karten: MutableSet<Karte>

  /**
   * Menge aller ausgelesenen Kategorien
   */
  val kategorien: MutableSet<Kategorie>

  /**
   * Menge aller ausgelesenen Spiele
   */
  val spiele: MutableSet<Spiel>

  init {
    val yamlDaten = Yaml().load<Map<String, Any>>(datenbank.inputStream())

    karten = Karte.fromYaml(yamlDaten).toMutableSet()
    kategorien = Kategorie.fromYaml(yamlDaten, karten).toMutableSet()
    spiele = Spiel.fromYaml(yamlDaten, kategorien).sortedBy {
      it.localizations[Sprachen.OG]!!
    }.toMutableSet()
  }

  /**
   * Speichert den aktuellen Zustand der Datenbankobjekte (`karten`, `kategorien`, `spiele`)
   * als YAML-Datei in das Dateisystem.
   *
   * Die Daten werden serialisiert und in der Reihenfolge
   * wie beim Laden (Karten -> Kategorien -> Spiele), jedoch nach ihrer ID sortiert, zurückgeschrieben.
   */
  internal fun aktualisieren() {
    val builder = StringBuilder()

    builder.append(attributToYamlZeile(0, KARTEN, null))
    for (karte in karten.sorted()) {
      builder.append(karte.toYaml())
    }

    builder.append("\n")

    builder.append(attributToYamlZeile(0, KATEGORIEN, null))
    for (kategorie in kategorien.sorted()) {
      builder.append(kategorie.toYaml())
    }

    builder.append("\n")

    builder.append(attributToYamlZeile(0, SPIELE, null))
    for (spiel in spiele.sorted()) {
      builder.append(spiel.toYaml())
    }

    datenbank.writeText(builder.toString())
  }

  /**
   * Gibt einen zufälligen Kartentext aus einer Sammlung (`Kategorie` / `Spiel`) zurück.
   * Wenn alle Karten bereits gesehen wurden, wird der Status `gesehen` von allen zurückgesetzt.
   *
   * @param sammlung Sammlung, aus der eine Karte gewählt werden soll
   * @return Kartentext in der Originalsprache
   */
  fun getRandomKartentext(sammlung: SammlungAnSpielelementen<*>): String {
    val karten = sammlung.getUngeseheneKarten()

    // Wenn es keine ungesehenen Karten gibt, dann sind wohl schon alle durch und
    // man kann die Sammlung von vorne durchgehen.
    if (karten.isEmpty()) {
      sammlung.setKartenUngesehen()
      return getRandomKartentext(sammlung)
    }

    val randomKarte = karten.random()
    randomKarte.gesehen = true // wird die Karte ausgegeben, ist sie ebenfalls gesehen
    aktualisieren()

    return randomKarte.localizations[Sprachen.OG]!!
  }

  /**
   * Erstellt neue [Karte]n aus den gegebenen Kartentexten oder findet bestehende anhand der Namen.
   *
   * @param kartentexte Collection an (wahrscheinlich) neuen Kartentexten
   * @param sprache Sprache der Texte
   * @return Menge der neu erstellten oder gefundenen Karten
   */
  fun neueKarten(kartentexte: Collection<String>, sprache: Sprachen): Set<Karte> {
    val neueKarten = mutableSetOf<Karte>()
    var neueId = neueID(karten) - 1 // das ist die höchste ID

    kartentexte.forEach {
      neueId += 1 // für jeden Schleifendurchlauf ist die ID höher
      var neueKarte = karten.finde(it)
      if (neueKarte == null) {
        neueKarte = Karte.fromEingabe(neueId, sprache, it)
      } else {
        neueId -= 1 // neutralisiert die ID-Erhöhung der Schleife
      }
      neueKarten.add(neueKarte)
    }

    karten.addAll(neueKarten)
    aktualisieren()
    return neueKarten.toSet()
  }

  /**
   * Findet eine bestehende Sammlung ([Kategorie] oder [Spiel]) anhand des Namens oder erstellt eine neue.
   *
   * Wenn bereits eine Sammlung mit dem gegebenen Namen existiert, werden neue Elemente den OG-Elemente hinzugefügt.
   *
   * @param T Typ der Sammlung
   * @param E Elemente in der Sammlung
   * @param name Name der gesuchten oder zu erstellenden Sammlung
   * @param elemente Collection der Elemente, die in der Sammlung enthalten sein sollen
   * @param sprache Sprache des übergebenen Namens
   * @param daten Collection, in der nach einer bestehenden Sammlung gesucht wird
   * @return die gefundene oder neu erstellte Sammlung
   */
  private fun <T: SammlungAnSpielelementen<E>, E: LokalisierbaresSpielelement> alteSammlungFindenOderNeueErstellen(
    name: String, elemente: Collection<E>, sprache: Sprachen, daten: Collection<T>,
  ): T {
    var neueSammlung = daten.finde(name)

    if (neueSammlung == null) {
      val neueID = neueID(daten)

      @Suppress("UNCHECKED_CAST") //.
      neueSammlung = when (daten.first()) { // when (T)
        is Kategorie -> Kategorie.fromEingabe(
          neueID, sprache, name, elemente as Collection<Karte>
        ) as T

        is Spiel -> Spiel.fromEingabe(
          neueID, sprache, name, elemente as Collection<Kategorie>
        ) as T

        else -> error("Unbekannter Typ: ${daten.first()::class.simpleName}")
      }
    } else { // Wenn die Sammlung gefunden wurden, werden neue Elemente den OG-Elemente hinzugefügt.
      elemente.forEach {
        if (!neueSammlung.originaleElemente[IDS]!!.contains(it)) neueSammlung.originaleElemente[IDS]!!.add(
          it
        )
      }
    }
    return neueSammlung
  }

  /**
   * Erstellt eine neue [Kategorie] oder findet eine bestehende anhand des Namens.
   *
   * @param name Name der Kategorie
   * @param karten Collection an Karten, die in die Kategorie aufgenommen werden sollen
   * @param sprache Sprache des Namens
   * @return die erstellte oder gefundene Kategorie
   */
  fun neueKategorie(name: String, karten: Collection<Karte>, sprache: Sprachen): Kategorie {
    val neueKategorie = alteSammlungFindenOderNeueErstellen(name, karten, sprache, kategorien)
    kategorien.add(neueKategorie)
    aktualisieren()
    return neueKategorie
  }

  /**
   * Erstellt ein neues [Spiel] oder findet ein bestehendes anhand des Namens.
   *
   * @param name Name des Spiels
   * @param kategorien Collection an Kategorien, die in die Kategorie aufgenommen werden sollen
   * @param sprache Sprache des Namens
   * @return das erstellte oder gefundene Spiel
   */
  fun neuesSpiel(name: String, kategorien: Collection<Kategorie>, sprache: Sprachen): Spiel {
    val neuesSpiel = alteSammlungFindenOderNeueErstellen(name, kategorien, sprache, spiele)
    spiele.add(neuesSpiel)
    aktualisieren()
    return neuesSpiel
  }

  /**
   * Markiert eine Karte als gelöscht und entfernt sie aus allen Kategorien.
   *
   * @param zuLoeschendeKarte Karte, die gelöscht werden soll
   */
  fun karteLoeschen(zuLoeschendeKarte: Karte) {
    zuLoeschendeKarte.geloescht = true
    val kategorienMitZuLoeschenderKarte = kategorien.filter {
      it.getAktuelleKarten().contains(zuLoeschendeKarte)
    }
    kategorienMitZuLoeschenderKarte.forEach {
      it.karteEntfernen(zuLoeschendeKarte)
    }
    aktualisieren()
  }

  /**
   * Fügt neue Elemente zu einer Sammlung hinzu.
   *
   * @param T Sammlung (direktes Hinzufügen) oder Int (finden durch ID)
   * @param E Element, das in der Sammlung enthalten ist
   * @param neueElemente Collection von Element-Objekten oder IDs
   * @param daten Collection, zu der die Sammlung hinzugefügt werden sollen
   * @param elementFunktionZumElementHinzufuegen Funktion aus der Klasse, welche die Elemente der Sammlung hinzufügt
   */
  private fun <T: Any, E: LokalisierbaresSpielelement> elementHinzufuegen(
    neueElemente: Collection<T>, daten: Collection<E>, elementFunktionZumElementHinzufuegen: (Collection<E>) -> Unit,
  ) {
    when (neueElemente.first()) {
      is LokalisierbaresSpielelement -> @Suppress("UNCHECKED_CAST") elementFunktionZumElementHinzufuegen(
        neueElemente as Collection<E>
      )

      is Int -> @Suppress("UNCHECKED_CAST") elementFunktionZumElementHinzufuegen(
        daten.finde(
          neueElemente as List<Int>
        )
      )
    }
    aktualisieren()
  }

  /**
   * Fügt neue `Karte`n zu einer `Kategorie` hinzu.
   *
   * @param T Element (direktes Hinzufügen) oder Int (finden durch ID)
   * @param kategorie Kategorie, zu der die Karten hinzugefügt werden sollen
   * @param neueKarten Collection von `Karte`n-Objekten oder IDs
   */
  fun <T: Any> kartenZuKategorieHinzufuegen(kategorie: Kategorie, neueKarten: Collection<T>) {
    elementHinzufuegen(neueKarten, karten, kategorie::kartenHinzufuegen)
  }

  /**
   * Fügt neue `Kategorie`n zu einem `Spiel` hinzu.
   *
   * @param T Element (direktes Hinzufügen) oder Int (finden durch ID)
   * @param spiel Spiel, zu dem die Kategorien hinzugefügt werden sollen
   * @param neueKategorien Collection von `Kategorie`-Objekten oder IDs
   */
  fun <T: Any> kategorienZuSpielHinzufuegen(spiel: Spiel, neueKategorien: Collection<T>) {
    elementHinzufuegen(neueKategorien, kategorien, spiel::kategorienHinzufuegen)
  }

  /**
   * Entfernt eine bestimmte `Karte` aus einer `Kategorie`.
   *
   * @param zuEntfernendeKarte Karte, die entfernt werden soll
   * @param ausKategorie Kategorie, aus der die Karte entfernt werden soll
   */
  fun karteAusKategorieEntfernen(zuEntfernendeKarte: Karte, ausKategorie: Kategorie) {
    ausKategorie.karteEntfernen(zuEntfernendeKarte)
    aktualisieren()
  }

  /**
   * Entfernt eine bestimmte `Kategorie` aus einem `Spiel`.
   *
   * @param zuEntfernendeKategorie Kategorie, die entfernt werden soll
   * @param ausSpiel Spiel, aus der die Kategorie entfernt werden soll
   */
  fun kategorieAusSpielEntfernen(zuEntfernendeKategorie: Kategorie, ausSpiel: Spiel) {
    ausSpiel.kategorieEntfernen(zuEntfernendeKategorie)
    aktualisieren()
  }

  companion object {
    /**
     * Erstellt ein `Datenbanksystem` aus einer lokalen Datei im App-Dateisystem,
     * oder falls die Datei noch nicht existiert, wird sie aus den Ressourcen kopiert.
     *
     * @param context Android-Context von der Activity, die das Datenbanksystem erzeugt
     * @return Instanz des Datenbanksystems mit geladenen Daten
     */
    fun generieren(context: Context): Datenbanksystem {
      val datei = File(context.filesDir, DATENBANK_DATEI) // Nutzer-spezifische Datei
      if (!datei.exists()) {
        val datenkbankInResRaw = context.resources.openRawResource(R.raw.datenbank) // Standard-Daten
        datei.writeBytes(datenkbankInResRaw.readBytes())
      }
      return Datenbanksystem(datei)
    }
  }
}
