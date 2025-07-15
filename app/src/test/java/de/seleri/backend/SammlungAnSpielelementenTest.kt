package de.seleri.backend

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import org.yaml.snakeyaml.Yaml

data class DummySammlungAnSpielelementen(
  override var id: Int,
  override val localizations: MutableMap<Sprachen, String?>,
  override val originaleElemente: Map<String, MutableSet<Karte>>,
  override val hinzugefuegteElemente: MutableSet<Karte>
): SammlungAnSpielelementen<Karte>(
  id, localizations, originaleElemente, hinzugefuegteElemente
) {
  fun dummyOriginaleUndHinzugefuegteElementeToYaml(elemente: String): String =
    originaleUndHinzugefuegteElementeToYaml(elemente)

  override fun getKarten(): Set<Karte> = getAlleElemente()
  override fun getAktuelleKarten(): Set<Karte> = getAlleAktuellenElemente()
  override fun getUngeseheneKarten(): Set<Karte> = geseheneKartenRausfiltern(getAktuelleKarten())
  override fun setKartenUngesehen() = setKartenUngesehen(getAktuelleKarten())

  fun dummyElementeHinzufuegen(neueElemente: Collection<Karte>) = elementeHinzufuegen(neueElemente)

  fun dummyElementeEntfernen(element: Karte) = elementEntfernen(element)

  companion object {
    fun fromEingabe(
      id: Int, sprache: Sprachen, name: String, originaleKarten: Collection<Karte>
    ): DummySammlungAnSpielelementen =
      fromEingabe(id, sprache, name, originaleKarten, ::DummySammlungAnSpielelementen)

    fun fromYamlListe(
      elementart: String,
      yamlDaten: Map<String, Any>,
      converter: (Map<String, Any>) -> Collection<DummySammlungAnSpielelementen>
    ) = LokalisierbaresSpielelement.fromYamlListe(elementart, yamlDaten, converter)

    fun fromYaml(
      yamlDaten: Map<String, Any>, moeglicheKarten: Collection<Karte>
    ): Set<DummySammlungAnSpielelementen> =
      setOf(fromYaml(yamlDaten, moeglicheKarten, ::DummySammlungAnSpielelementen))
  }
}

fun alleDummyKarten(): Set<Karte> {
  val dummyKarten = getDummyKarten("dummyKarten")
  val actual = dummyKarten.size

  val expected = 5
  assertEquals(expected, actual)

  return dummyKarten
}

fun dummyKarte1(): Karte = alleDummyKarten().finde(1)
fun dummyKarte2(): Karte = alleDummyKarten().finde(2)
fun dummyKarte3(): Karte = alleDummyKarten().finde(3)
fun dummyKarte4(): Karte = alleDummyKarten().finde(4)
fun dummyKarte5(): Karte = alleDummyKarten().finde(5)

const val DUMMY_NAME = "Name"
fun dummyOriginaleKartenIDs(): MutableSet<Karte> =
  mutableSetOf(dummyKarte1(), dummyKarte2(), dummyKarte3(), dummyKarte4())

fun dummyDavonEntfernteKarten(): MutableSet<Karte> = mutableSetOf(dummyKarte2(), dummyKarte4())
fun dummyOriginaleKarten(): Map<String, MutableSet<Karte>> =
  mapOf(IDS to dummyOriginaleKartenIDs(), DAVON_ENTFERNT to dummyDavonEntfernteKarten())

fun dummyHinzugefuegteKarten(): MutableSet<Karte> = mutableSetOf()
fun dummySammlung() = DummySammlungAnSpielelementen(
  DUMMY_ID, dummyLocalizations(), dummyOriginaleKarten(), dummyHinzugefuegteKarten()
)

fun yaml1DummySammlung() = """
        |  - $ID: $DUMMY_ID
        |    $DUMMY_NAME:
        |      ${Sprachen.OG}: "$DUMMY_OG"
        |      ${Sprachen.DE}: "$DUMMY_DE"
        |
        """.trimMargin()

fun yaml2DummySammlung() = """
        |    $ORIGINALE$KARTEN:
        |      $IDS: [1,2,3,4]
        |      $DAVON_ENTFERNT: [2,4]
        |    $HINZUGEFUEGTE$KARTEN$BINDESTRICH_IDS: []
        |
        """.trimMargin()

fun yamlDummySammlung() = yaml1DummySammlung() + yaml2DummySammlung()

class SammlungAnSpielelementenTest {

  /*
  ------------------------------------------------------------------------------------------------
  WICHTIG: Wie ist jeder Test aufgebaut?

  1: benötigte Variablen & Konstanten instantiieren

      (1.5: ggf. wird hier expected instanziiert, falls sich nichts ändern soll)

  2: actual = Ausführung des zu testenden Codes

      (2.5: bei AssertTrue wird 2 & 3 ersetzt durch condition = Ausführung des zu testenden Codes)

  3: expected instanzieeren & Test ausführen
  ------------------------------------------------------------------------------------------------
  */

  @Test
  fun `toYaml() wandelt das Objekt korrekt in Yaml-Text um`() {
    val dummy = dummySammlung()

    val actual = dummy.toYaml()

    val expected = yaml1DummySammlung()
    assertEquals(expected, actual)
  }

  @Test
  fun `originaleUndHinzugefuegteElementeToYaml() wandelt die Elemente-Map korrekt in Yaml-Text um`() {
    val dummy = dummySammlung()

    val actual = dummy.dummyOriginaleUndHinzugefuegteElementeToYaml(KARTEN)

    val expected = yaml2DummySammlung()
    assertEquals(expected, actual)
  }

  @Test
  fun `Rundreise Yaml zu Objekt zu Yaml`() {
    val yamlDummy1 = yamlDummySammlung()

    val dummyDaten = (Yaml().load(yamlDummy1) as List<Map<String, Any>>).first()
    val dummy = DummySammlungAnSpielelementen.fromYaml(dummyDaten, alleDummyKarten()).first()

    val yamlDummy2a = dummy.toYaml()
    val yamlDummy2b = dummy.dummyOriginaleUndHinzugefuegteElementeToYaml(KARTEN)
    val yamlDummy2 = yamlDummy2a + yamlDummy2b
    assertEquals(yamlDummy1, yamlDummy2)
  }

  @Test
  fun `getAlleElemente() gibt alle Elemente der Sammlung zurueck`() {
    val dummy = dummySammlung()

    val actual = dummy.getKarten()

    val expected = setOf(dummyKarte1(), dummyKarte2(), dummyKarte3(), dummyKarte4())
    assertEquals(expected, actual)
  }

  @Test
  fun `getAlleAktuellenElemente() gibt alle Elemente der Sammlung zurueck, die nicht entfernt worden sind`() {
    val dummy = dummySammlung()

    val actual = dummy.getAktuelleKarten()

    val expected = setOf(dummyKarte1(), dummyKarte3())
    assertEquals(expected, actual)
  }

  @Test
  fun `getUngeseheneKarten() gibt alle noch nicht gesehenen Elemente der Sammlung zurueck`() {
    val dummy = dummySammlung()

    val actual = dummy.getUngeseheneKarten()

    val expected = setOf(dummyKarte1())
    assertEquals(expected, actual)
  }

  @Test
  fun `setKartenUngesehen() setzt alle Karten auf ungesehen`() {
    val dummy = dummySammlung()

    dummy.setKartenUngesehen()
    val actual = dummy.getUngeseheneKarten()

    val expected = dummy.getAktuelleKarten()
    assertEquals(expected, actual)
  }

  @Test
  fun `elementeHinzufuegen() fuegt neue Elemente zur Sammlung hinzu`() {
    val dummy = dummySammlung()
    val neueElemente = setOf(dummyKarte2(), dummyKarte5())

    dummy.dummyElementeHinzufuegen(neueElemente)
    val actual = dummy.getAktuelleKarten()

    val expected = setOf(dummyKarte1(), dummyKarte2(), dummyKarte3(), dummyKarte5())
    assertEquals(expected, actual)
  }

  @Test
  fun `elementEntfernen() entfernt ein originales Element, was noch nicht entfernt wurde`() {
    val dummy = dummySammlung()
    val zuEntfernendesElement = dummyKarte1()
    assertTrue(dummy.getAktuelleKarten().contains(zuEntfernendesElement))

    val expected = dummy.getAktuelleKarten() - zuEntfernendesElement

    dummy.dummyElementeEntfernen(zuEntfernendesElement)
    val actual = dummy.getAktuelleKarten()

    assertEquals(expected, actual)
  }

  @Test
  fun `elementEntfernen() entfernt ein originales Element, was bereits entfernt wurde`() {
    val dummy = dummySammlung()
    val zuEntfernendesElement = dummyKarte2()
    assertTrue(dummy.getKarten().contains(zuEntfernendesElement))
    assertFalse(dummy.getAktuelleKarten().contains(zuEntfernendesElement))

    val expected = dummy.getAktuelleKarten()

    dummy.dummyElementeEntfernen(zuEntfernendesElement)
    val actual = dummy.getAktuelleKarten()

    assertEquals(expected, actual)
  }

  @Test
  fun `elementEntfernen() entfernt ein hinzugefuegtes Element`() {
    val dummy = dummySammlung()
    val zuEntfernendesElement = dummyKarte5()

    assertFalse(dummy.getKarten().contains(zuEntfernendesElement))
    val expected = dummy.getAktuelleKarten()

    dummy.dummyElementeHinzufuegen(setOf(zuEntfernendesElement))
    assertTrue(dummy.getAktuelleKarten().contains(zuEntfernendesElement))

    dummy.dummyElementeEntfernen(zuEntfernendesElement)
    val actual = dummy.getAktuelleKarten()

    assertEquals(expected, actual)
  }

  @Test
  fun `elementEntfernen() entfernt ein der Sammlung unbekanntes Element`() {
    val dummy = dummySammlung()
    val zuEntfernendesElement = dummyKarte5()

    assertFalse(dummy.getKarten().contains(zuEntfernendesElement))
    val expected = dummy.getAktuelleKarten()

    dummy.dummyElementeEntfernen(zuEntfernendesElement)
    val actual = dummy.getAktuelleKarten()

    assertEquals(expected, actual)
  }

  /**
   * prüft nur die Instanziierung der neuen Attribute von der Sammlung im Vergleich zur Superklasse
   */
  private fun testKorrekteInstanziierung(
    erwarteteOriginale: Map<String, Collection<Karte>>,
    erwarteteHinzugefuegte: Collection<Karte>,
    dummy: DummySammlungAnSpielelementen
  ) {
    val actualOriginale = dummy.originaleElemente
    val actualHinzugefuegte = dummy.hinzugefuegteElemente

    assertEquals(erwarteteOriginale, actualOriginale)
    assertEquals(erwarteteHinzugefuegte, actualHinzugefuegte)
  }

  @Test
  fun `fromEingabe() korrekte Instanziierung durch Eingabe`() {
    val eingabeID = DUMMY_ID
    val eingabeSprache = Sprachen.DE
    val eingabeName = DUMMY_NAME
    val eingabeKarten = alleDummyKarten()

    val dummy = DummySammlungAnSpielelementen.fromEingabe(
      eingabeID, eingabeSprache, eingabeName, eingabeKarten
    )

    val erwarteteOriginale = mapOf(IDS to eingabeKarten, DAVON_ENTFERNT to emptySet())
    val erwarteteHinzugefuegte = emptySet<Karte>()
    testKorrekteInstanziierung(erwarteteOriginale, erwarteteHinzugefuegte, dummy)
  }

  private fun getDummySammlungen(elementart: String): Set<DummySammlungAnSpielelementen> =
    getDummyDaten("SammlungAnSpielelementen.yml", elementart) { yamlDaten ->
      DummySammlungAnSpielelementen.fromYamlListe(
        elementart, yamlDaten
      ) {
        DummySammlungAnSpielelementen.fromYaml(it, alleDummyKarten())
      }
    }

  @Test
  fun `fromYaml() Yaml-Datei wird korrekt in eine Menge von Objekten verwandelt`() {
    val dummys = getDummySammlungen("gültigeDummySammlungAnSpielelementen")

    val actual = dummys.size

    val expected = 2
    assertEquals(expected, actual)

    `fromYaml(7) Objekt mit der ID 7 korrekt aus der Yaml gelesen`(dummys.finde(7))
    `fromYaml(42) Objekt mit der ID 42 korrekt aus der Yaml gelesen`(dummys.finde(42))
  }

  private fun `fromYaml(7) Objekt mit der ID 7 korrekt aus der Yaml gelesen`(
    dummy: DummySammlungAnSpielelementen
  ) {
    val originaleKartenIDs = setOf(dummyKarte1())
    val davonEntfernte = emptySet<Karte>()

    val erwarteteOriginale = mapOf(IDS to originaleKartenIDs, DAVON_ENTFERNT to davonEntfernte)
    val erwarteteHinzugefuegte = setOf(dummyKarte5())
    testKorrekteInstanziierung(erwarteteOriginale, erwarteteHinzugefuegte, dummy)
  }

  private fun `fromYaml(42) Objekt mit der ID 42 korrekt aus der Yaml gelesen`(
    dummy: DummySammlungAnSpielelementen
  ) {
    val originaleKartenIDs = setOf(dummyKarte1(), dummyKarte2(), dummyKarte3(), dummyKarte4())
    val davonEntfernte = setOf(dummyKarte2(), dummyKarte4())

    val erwarteteOriginale = mapOf(IDS to originaleKartenIDs, DAVON_ENTFERNT to davonEntfernte)
    val erwarteteHinzugefuegte = emptySet<Karte>()
    testKorrekteInstanziierung(erwarteteOriginale, erwarteteHinzugefuegte, dummy)
  }

  @Test
  fun `fromYaml() Exception bei fehlenden Yaml-Attributen`() {
    assertThrows(IllegalArgumentException::class.java) {
      getDummySammlungen("fehlendesOriginale_")
    }
    assertThrows(IllegalArgumentException::class.java) {
      getDummySammlungen("fehlendeOriginaleIDs")
    }
    assertThrows(IllegalArgumentException::class.java) {
      getDummySammlungen("fehlendeDavonEntfernte")
    }
    assertThrows(IllegalArgumentException::class.java) {
      getDummySammlungen("fehlendesHinzugefuegte_IDs")
    }
  }
}
