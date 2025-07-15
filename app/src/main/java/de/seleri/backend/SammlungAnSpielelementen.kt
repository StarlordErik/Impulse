package de.seleri.backend

const val NAME: String = "Name"

const val ORIGINALE: String = "originale_"
const val IDS = "${ID}s"
const val DAVON_ENTFERNT: String = "davon_entfernt"

const val HINZUGEFUEGTE: String = "hinzugefügte_"
const val BINDESTRICH_IDS: String = "-$IDS"

/**
 * Abstrakte Basisklasse für alle Spielelemente, die eine Sammlung von anderen Elementen beeinhalten
 *
 * @param T Typ des Elementes, das in der Sammlung enthalten ist
 * @property id eindeutige ID des Elements
 * @property localizations Map mit den Übersetzungen des Namens für verschiedene Sprachen
 * @property originaleElemente zwei Mengen mit den originalen Elementen und denen, die vom Nutzer entfernt wurden
 * @property hinzugefuegteElemente Menge der vom Nutzer hinzugefügten Elemente zur Sammlung
 */
abstract class SammlungAnSpielelementen<T: LokalisierbaresSpielelement>(
  override var id: Int,
  override val localizations: MutableMap<Sprachen, String?>,
  open val originaleElemente: Map<String, MutableSet<T>>,
  open val hinzugefuegteElemente: MutableSet<T>,
): LokalisierbaresSpielelement(id, localizations) {

  /**
   * Konvertiert die ID mit Namen ins YAML-Format und beginnt damit den YAML-Datensatz einer Sammlung.
   *
   * @return nur ID und Name als YAML-String
   */
  override fun toYaml(): String {
    val output = StringBuilder()
    output.append(super.toYaml())
    output.append(localizationsToYaml(NAME))
    return output.toString()
  }

  /**
   * Konvertiert die originalen, davon entfernten und hinzugefügten Elemente ins YAML-Format.
   *
   * @param elemente Bezeichnung der Elemente (/Bestandteile) der Sammlung
   * @return "originale-T: IDS: [.,.,.] davon_entfernt: [.,.,.] \n hinzugefuegte-T: [.,.,.]" als YAML-String
   */
  protected fun originaleUndHinzugefuegteElementeToYaml(elemente: String): String {
    val output = StringBuilder()

    output.append(attributToYamlZeile(2, "$ORIGINALE$elemente", originaleElemente))
    output.append(
      attributToYamlZeile(
        2, "$HINZUGEFUEGTE$elemente$BINDESTRICH_IDS", hinzugefuegteElemente
      )
    )
    return output.toString()
  }

  /**
   * Gibt alle Elemente der Sammlung zurück und ignoriert die davon entfernten.
   *
   * @return originale Elemente + hinzugefügte Elemente
   */
  protected fun getAlleElemente(): Set<T> {
    val alleElemente = mutableSetOf<T>()
    alleElemente.addAll(originaleElemente[IDS]!!)
    alleElemente.addAll(hinzugefuegteElemente)
    return alleElemente.toSet()
  }

  /**
   * Gibt alle Karten der Sammlung zurück und ignoriert die davon entfernten.
   *
   * @return originale Karten + hinzugefügte Kartem
   */
  abstract fun getKarten(): Set<Karte>

  /**
   * Gibt alle Elemente der Sammlung zurück ohne die "davon entfernten" Elemente.
   *
   * @return (originale Elemente - davon entfernte Elemente) + hinzugefügte Elemente
   */
  protected fun getAlleAktuellenElemente(): Set<T> {
    val aktuelleElemente = getAlleElemente().toMutableSet()
    aktuelleElemente.removeAll(originaleElemente[DAVON_ENTFERNT]!!)
    return aktuelleElemente.toSet()
  }

  /**
   * Gibt alle Karten der Sammlung zurück ohne die "davon entfernten" Karten.
   *
   * @return (originale Karten - davon entfernten Karten) + hinzugefügte Karten
   */
  abstract fun getAktuelleKarten(): Set<Karte>

  /**
   * Gibt alle Karten der Sammlung zurück, die noch nicht gesehen wurden.
   *
   * @param aktuelleKarten Collection aller aktuellen Karten der Sammlung
   * @return Menge an Karten, die noch nicht gesehen wurden
   */
  protected fun geseheneKartenRausfiltern(aktuelleKarten: Collection<Karte>): Set<Karte> {
    return aktuelleKarten.filter {!it.gesehen}.toSet()
  }

  /**
   * Gibt alle noch nicht gesehenen Karten der Sammlung zurück.
   *
   * @return noch nicht gesehene Karten
   */
  abstract fun getUngeseheneKarten(): Set<Karte>

  /**
   * Setzt alle Karten der Sammlung auf "ungesehen".
   *
   * @param aktuelleKarten Collection aller aktuellen Karten der Sammlung
   */
  protected fun setKartenUngesehen(aktuelleKarten: Collection<Karte>) {
    aktuelleKarten.forEach {it.gesehen = false}
  }

  /**
   * Setzt alle Karten der Sammlung auf "ungesehen".
   */
  abstract fun setKartenUngesehen()

  /**
   * Fügt neue Elemente zur Sammlung hinzu.
   * Dafür werden sie der der Menge der hinzugefügten Elemente hinzugefügt und für den Fall,
   * dass es schon in den originalen Elementen enthalten war und entfernt wurde, wird es rehabilitiert.
   *
   * @param neueElemente Elemente, die zur Sammlung hinzugefügt werden sollen
   */
  protected fun elementeHinzufuegen(neueElemente: Collection<T>) {
    val alleElemente = getAlleElemente() // Caching
    neueElemente.forEach {neuesElement ->

      // rehabilitert die Elemente, die vorher entfernt wurden
      originaleElemente[DAVON_ENTFERNT]!!.remove(neuesElement)

      // fügt es nur hinzu, wenn es nicht bereits Teil der originalen (und hinzugefügten) Elemente ist
      if (!alleElemente.contains(neuesElement)) {
        hinzugefuegteElemente.add(neuesElement)
      }
    }
  }

  /**
   * Entfernt ein Element aus der Sammlung.
   * Dafür wird es der Menge der davon entfernten Elemente hinzugefügt
   * und/oder aus den hinzugefügten Elementen gelöscht.
   *
   * @param zuEntfernendesElement Element, das aus der Sammlung entfernt werden soll
   */
  protected fun elementEntfernen(zuEntfernendesElement: T) {
    originaleElemente[DAVON_ENTFERNT]!!.add(zuEntfernendesElement)
    hinzugefuegteElemente.remove(zuEntfernendesElement)
  }

  companion object {

    /**
     * Erstellt eine Sammlung anhand von Benutzereingaben oder Skriptdaten.
     *
     * @param T Sammlung, die erstellt wird
     * @param E Element, das in der Sammlung enthalten ist
     * @param id neue, noch nicht vergebene ID
     * @param sprache Sprache des Namens
     * @param name Name
     * @param originaleElemente Collection der original enthaltenen Elemente
     * @param constructor Konstruktor der Sammlung vom Typ T
     * @return neue Sammlung vom Typ T ohne entfernte oder hinzugefügte Elemente
     */
    @JvmStatic // damit die Methode protected sein kann
    protected fun <T: SammlungAnSpielelementen<E>, E: LokalisierbaresSpielelement> fromEingabe(
      id: Int,
      sprache: Sprachen,
      name: String,
      originaleElemente: Collection<E>,
      constructor: (Int, MutableMap<Sprachen, String?>, Map<String, MutableSet<E>>, MutableSet<E>) -> T,
    ): T {

      // lässt id, sprache und name in der Superklasse verarbeiten
      val (id, localizations) = fromEingabe(id, sprache, name)

      val originaleElementeMitNullEntfernten = mapOf(
        IDS to originaleElemente.toMutableSet(), DAVON_ENTFERNT to mutableSetOf()
      )
      return constructor(
        id, localizations, originaleElementeMitNullEntfernten, mutableSetOf()
      )
    }

    /**
     * Erstellt eine Sammlung aus einem **einzigen** YAML-Datensatz.
     *
     * Wieso gibt es nur fromYaml und nicht fromYamlListe? Die super-Funktion reicht völlig.
     *
     * @param T Sammlung, die erstellt wird
     * @param E Element, das in der Sammlung enthalten ist
     * @param yamlDaten YAML-Datensatz einer Sammlung
     * @param moeglicheElemente Collection aller Elemente vom Typ T, aus denen die Sammlung bestehen **könnte** -
     * im Zweifel einfach alle möglichen Elemente
     * @param constructor Konstruktor der Sammlung vom Typ T
     * @return neue Sammlung vom Typ T mit ausgelesenen Attributswerten
     */
    @JvmStatic // damit die Methode protected sein kann
    protected fun <T: SammlungAnSpielelementen<E>, E: LokalisierbaresSpielelement> fromYaml(
      yamlDaten: Map<String, Any>,
      moeglicheElemente: Collection<E>,
      constructor: (Int, MutableMap<Sprachen, String?>, Map<String, MutableSet<E>>, MutableSet<E>) -> T,
    ): T {

      require(
        ("$ORIGINALE$KARTEN" in yamlDaten || "$ORIGINALE$KATEGORIEN" in yamlDaten) && //.
          ("$HINZUGEFUEGTE$KARTEN$BINDESTRICH_IDS" in yamlDaten || //.
            "$HINZUGEFUEGTE$KATEGORIEN$BINDESTRICH_IDS" in yamlDaten)
      ) {
        "Ungültige Sammlungsstruktur."
      }

      // lässt id und localizations in der Superklasse verarbeiten
      val (id, localizations) = fromYaml(yamlDaten)

      @Suppress("UNCHECKED_CAST") val originaleElementeIDs =
        ((yamlDaten["$ORIGINALE$KARTEN"]
          ?: yamlDaten["$ORIGINALE$KATEGORIEN"]) as Map<String, List<Int>>)

      require(IDS in originaleElementeIDs && DAVON_ENTFERNT in originaleElementeIDs) {
        "Ungültige originale Elemente."
      }

      @Suppress("UNCHECKED_CAST") val hinzugefuegteIDs =
        (yamlDaten["$HINZUGEFUEGTE$KARTEN$BINDESTRICH_IDS"]
          ?: yamlDaten["$HINZUGEFUEGTE$KATEGORIEN$BINDESTRICH_IDS"]) as List<Int>

      // findet alle Elemente per ID aus der Liste aller möglichen Elemente
      val originaleElemente = moeglicheElemente.finde(originaleElementeIDs[IDS]!!)
      val entfernteElemente = moeglicheElemente.finde(originaleElementeIDs[DAVON_ENTFERNT]!!)
      val hinzugefuegteElemente = moeglicheElemente.finde(hinzugefuegteIDs)

      val originaleUndDavonEntfernteElemente = mapOf(
        IDS to originaleElemente.toMutableSet(), DAVON_ENTFERNT to entfernteElemente.toMutableSet()
      )

      return constructor(
        id, localizations, originaleUndDavonEntfernteElemente, hinzugefuegteElemente.toMutableSet()
      )
    }
  }
}
