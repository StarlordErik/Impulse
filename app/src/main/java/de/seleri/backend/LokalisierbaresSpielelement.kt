package de.seleri.backend

/**
 * Datenbankbezeichner für [LokalisierbaresSpielelement.id]
 */
const val ID: String = "ID"

/**
 * Abstrakte Basisklasse für alle Spielelemente mit ID und den localized Kartentexten/Namen
 *
 * @property id eindeutige ID des Elements
 * @property localizations Map mit den Übersetzungen des Kartentextes/Namens für verschiedene Sprachen
 */
abstract class LokalisierbaresSpielelement(
  open var id: Int, open val localizations: MutableMap<Sprachen, String?>,
): Comparable<LokalisierbaresSpielelement> {

  /**
   * Konvertiert die ID ins YAML-Format und beginnt damit den YAML-Datensatz eines Elements.
   *
   * @return nur die ID als YAML-String
   */
  open fun toYaml(): String {
    val output = StringBuilder()
    output.append(attributToYamlZeile(1, "- $ID", id))
    return output.toString()
  }

  /**
   * Konvertiert die localizations ins YAML-Format.
   *
   * @param bezeichnung Kartentext oder Name einer Sammlung (Kategorie, Spiel)
   * @return "Bezeichnung: localizedTexts" als YAML-String
   */
  protected fun localizationsToYaml(bezeichnung: String): String {
    return attributToYamlZeile(2, bezeichnung, localizations)
  }

  /**
   * Setzt die Übersetzung für eine Sprache, sofern diese nicht OG (Original) ist.
   *
   * @param sprache Sprache, in der die Übersetzung gespeichert werden soll
   * @param bezeichnung Übersetzung des Textes / der Name
   */
  fun setUebersetzung(sprache: Sprachen, bezeichnung: String) {
    if (sprache != Sprachen.OG) localizations[sprache] = bezeichnung
  }

  /**
   * Vergleicht dieses Objekt mit dem angegebenen [other] Objekt zur Bestimmung einer Reihenfolge.
   * Gibt einen negativen Wert zurück, wenn dieses Objekt kleiner ist als [other],
   * einen positiven Wert, wenn es größer ist, oder 0 bei Gleichheit.
   */
  override fun compareTo(other: LokalisierbaresSpielelement): Int {

    // 1. nach Klassenname (abgeleiteter Typ) sortieren
    return this::class.simpleName!!.compareTo(other::class.simpleName!!).takeIf {it != 0}

    // 2. nach ID sortieren
      ?: this.id.compareTo(other.id).takeIf {it != 0}

      // 3. nach Bezeichnung sortieren
      ?: this.localizations[Sprachen.OG]!!.compareTo(other.localizations[Sprachen.OG]!!)
  }

  override fun equals(other: Any?) = this::class == other?.let {it::class} && //.
    other is LokalisierbaresSpielelement && // benötigt, da sonst eine ClassCastException geworfen wird
    this.id == other.id

  override fun hashCode() = id.hashCode()

  companion object {

    /**
     * Erstellt ein neues Spielelement anhand von Benutzereingaben oder Skriptdaten.
     *
     * @param id neue, noch nicht vergebene ID
     * @param sprache Sprache der Bezeichnung
     * @param bezeichnung Kartentext / Name
     * @return Tupel (id, localizations) aka abstraktes Objekt von LokalisierbaresElement
     */
    @JvmStatic // damit die Methode protected sein kann
    protected fun fromEingabe(
      id: Int, sprache: Sprachen, bezeichnung: String,
    ): Pair<Int, MutableMap<Sprachen, String?>> {

      // für alle Sprachen:
      // speichert die Bezeichnung sowohl in der entsprechenden Sprache als auch in OG, oder setzt null
      val localizations =
        Sprachen.entries.associateWith {if (it == sprache || it == Sprachen.OG) bezeichnung else null}.toMutableMap()

      return id to localizations
    }

    /**
     * Verarbeitet **eine Liste** von Element-Daten in einer YAML-Struktur.
     *
     * Wird nur protected aufgerufen und wenn der Code weiß, dass es sich um eine Liste von Elementen handelt.
     *
     * @param T jede Art von Element (Karte, Kategorie, Spiel)
     * @param elementart Element-Bezeichnung in der YAML-Datenstruktur
     * @param yamlDaten YAML-Datenstruktur
     * @param converter rekursiver Aufruf für das einzelne Element
     * @return Eine Liste von [T].
     */
    @JvmStatic // damit die Methode protected sein kann
    protected fun <T: LokalisierbaresSpielelement> fromYamlListe(
      elementart: String, yamlDaten: Map<String, Any>, converter: (Map<String, Any>) -> Collection<T>,
    ): Set<T> {

      // extrahiert die Liste möglicher Element-Daten aus der YAML-Struktur
      val listeAnElementenImYamlformat = yamlDaten[elementart] as Collection<*>

      // macht aus List<*> --> List<Map<String, Any>>
      val listeAnMapsVonElementenImYamlformat = listeAnElementenImYamlformat.filterIsInstance<Map<String, Any>>()

      // wandelt jede Element-Map in ein Element-Objekt um
      val elementListe = listeAnMapsVonElementenImYamlformat.map {
        converter(it) // zu Fall 2
          .first() // es wird nur ein Objekt ausgegeben, aber als Liste
      }.toMutableList()

      duplikateLoeschen(elementListe)
      doppelteIDsErsetzen(elementListe)

      return elementListe.sorted().toSet()
    }

    /**
     * Entfernt Duplikate aus der gegebenen Collection von lokalisierbaren Spielelementen.
     * Ein Duplikat liegt vor, wenn zwei Elemente für alle Sprachen identische `localizations` besitzen.
     *
     * @param T Element aus der Datenbank.yml
     * @param elemente Menge von Spielelementen, aus der Duplikate entfernt werden sollen
     */
    private fun <T: LokalisierbaresSpielelement> duplikateLoeschen(elemente: MutableCollection<T>) {
      val localizationUnikate = mutableSetOf<Map<Sprachen, String?>>()

      // Man kann mit forEach nicht direkt aus der iterierten Liste entfernen.
      val zuEntfernen = mutableListOf<T>()

      elemente.forEach {element ->
        val localization = element.localizations
        if (localization in localizationUnikate) {
          zuEntfernen.add(element) // Maps in Kotlin werden standardmäßig nicht per Referenz verglichen
        } else {
          localizationUnikate.add(localization)
        }
      }

      elemente.removeAll(zuEntfernen)
    }

    /**
     * Ersetzt doppelt vorkommende IDs in der gegebenen Collection von Spielelementen durch neue eindeutige IDs.
     *
     * @param T Element aus der Datenbank.yml
     * @param elemente Menge von Spielelementen, deren IDs auf Eindeutigkeit geprüft werden sollen
     */
    private fun <T: LokalisierbaresSpielelement> doppelteIDsErsetzen(elemente: Collection<T>) {
      val idUnikate = mutableSetOf<Int>()

      var anzahlNeuerIDs = 0
      elemente.forEach {element ->
        val aktuelleID = element.id
        if (aktuelleID in idUnikate) {
          element.id = neueID(elemente) + anzahlNeuerIDs
          anzahlNeuerIDs++
        } else {
          idUnikate.add(aktuelleID)
        }
      }
    }

    /**
     * Erstellt ein Spielelement aus einem **einzigen** YAML-Datensatz.
     *
     * Wird nur protected aufgerufen und wenn der Code weiß, dass es sich um ein einziges Element handelt.
     *
     * @param yamlDatensatz YAML-Datensatz eines Elements
     * @return Tupel (id, localizations) aka abstraktes Objekt von LokalisierbaresElement
     */
    @JvmStatic // damit die Methode protected sein kann
    protected fun fromYaml(
      yamlDatensatz: Map<String, Any>,
    ): Pair<Int, MutableMap<Sprachen, String?>> {

      require(ID in yamlDatensatz && (TEXT in yamlDatensatz || NAME in yamlDatensatz)) {
        "Ungültige Elementstruktur."
      }

      val id = yamlDatensatz[ID] as Int

      @Suppress("UNCHECKED_CAST") val yamlLocalizations =
        ((yamlDatensatz[TEXT]
          ?: yamlDatensatz[NAME]) as Map<String, String?>).mapKeys {Sprachen.valueOf(it.key)}

      // für alle Sprachen: speichert die ausgelesene Übersetzung oder setzt null
      val localizations = Sprachen.entries.associateWith {
        if (it in yamlLocalizations && yamlLocalizations[it] != null) yamlLocalizations[it] else null
      }.toMutableMap()

      return id to localizations
    }
  }
}
