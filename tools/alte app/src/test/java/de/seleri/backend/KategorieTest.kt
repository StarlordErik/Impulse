package de.seleri.backend

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import org.yaml.snakeyaml.Yaml

fun alleDummyKategorien(): Set<Kategorie> {
  val dummyKategorien = getDummyKategorien("dummyKategorien")
  val actual = dummyKategorien.size

  val expected = 5
  assertEquals(expected, actual)

  return dummyKategorien
}

fun dummyKategorie1(): Kategorie = alleDummyKategorien().finde(1)
fun dummyKategorie2(): Kategorie = alleDummyKategorien().finde(2)
fun dummyKategorie3(): Kategorie = alleDummyKategorien().finde(3)
fun dummyKategorie4(): Kategorie = alleDummyKategorien().finde(4)
fun dummyKategorie5(): Kategorie = alleDummyKategorien().finde(5)

fun dummyKategorie() = Kategorie(
  DUMMY_ID, dummyLocalizations(), dummyOriginaleKarten(), dummyHinzugefuegteKarten()
)

fun yamlDummyKategorie() = """
        |  - $ID: $DUMMY_ID
        |    $DUMMY_NAME:
        |      ${Sprachen.OG}: "$DUMMY_OG"
        |      ${Sprachen.DE}: "$DUMMY_DE"
        |    $ORIGINALE$KARTEN:
        |      $IDS: [1,2,3,4]
        |      $DAVON_ENTFERNT: [2,4]
        |    $HINZUGEFUEGTE$KARTEN$BINDESTRICH_IDS: []
        |
        """.trimMargin()

fun getDummyKategorien(elementart: String): Set<Kategorie> =
  getDummyDaten("Kategorie.yml", elementart) {Kategorie.fromYaml(it, alleDummyKarten())}

class KategorieTest {

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
    val dummy = dummyKategorie()

    val actual = dummy.toYaml()

    val expected = yamlDummyKategorie()
    assertEquals(expected, actual)
  }

  @Test
  fun `Rundreise Yaml zu Objekt zu Yaml`() {
    val yamlDummy1 = yamlDummyKategorie()

    val dummyDaten = (Yaml().load(yamlDummy1) as List<Map<String, Any>>).first()
    val dummy = Kategorie.fromYaml(dummyDaten, alleDummyKarten()).first()

    val yamlDummy2 = dummy.toYaml()
    assertEquals(yamlDummy1, yamlDummy2)
  }

  @Test
  fun `getKarten() gibt alle Karten der Kategorie zurueck`() {
    val dummy = dummyKategorie()

    val actual = dummy.getKarten()

    val expected = setOf(dummyKarte1(), dummyKarte2(), dummyKarte3(), dummyKarte4())
    assertEquals(expected, actual)
  }

  @Test
  fun `getAktuelleKarten() gibt alle Karten der Kategorie zurueck, die nicht entfernt worden sind`() {
    val dummy = dummyKategorie()

    val actual = dummy.getAktuelleKarten()

    val expected = setOf(dummyKarte1(), dummyKarte3())
    assertEquals(expected, actual)
  }

  @Test
  fun `getUngeseheneKarten() gibt alle noch nicht gesehenen Karten der Kategorie zurueck`() {
    val dummy = dummyKategorie()

    val actual = dummy.getUngeseheneKarten()

    val expected = setOf(dummyKarte1())
    assertEquals(expected, actual)
  }

  @Test
  fun `setKartenUngesehen() setzt alle Karten auf ungesehen`() {
    val dummy = dummyKategorie()

    dummy.setKartenUngesehen()
    val actual = dummy.getUngeseheneKarten()

    val expected = dummy.getAktuelleKarten()
    assertEquals(expected, actual)
  }

  @Test
  fun `kartenHinzufuegen() fuegt neue Karten zur Kategorie hinzu`() {
    val dummy = dummyKategorie()
    val neueKarten = setOf(dummyKarte2(), dummyKarte5())

    dummy.kartenHinzufuegen(neueKarten)
    val actual = dummy.getAktuelleKarten()

    val expected = setOf(dummyKarte1(), dummyKarte2(), dummyKarte3(), dummyKarte5())
    assertEquals(expected, actual)
  }

  @Test
  fun `karteEntfernen() entfernt eine Karte, die bislang Teil der Kategorie war`() {
    val dummy = dummyKategorie()
    val zuEntfernendeKarte = dummyKarte1()
    assertTrue(dummy.getAktuelleKarten().contains(zuEntfernendeKarte))

    val expected = dummy.getAktuelleKarten() - zuEntfernendeKarte

    dummy.karteEntfernen(zuEntfernendeKarte)
    val actual = dummy.getAktuelleKarten()

    assertEquals(expected, actual)
  }

  /**
   * prüft nur die Instanziierung der neuen Attribute von der Sammlung im Vergleich zur Superklasse
   */
  private fun testKorrekteInstanziierung(
    erwarteteOriginale: Map<String, Collection<Karte>>, erwarteteHinzugefuegte: Collection<Karte>, dummy: Kategorie,
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

    val dummy = Kategorie.fromEingabe(
      eingabeID, eingabeSprache, eingabeName, eingabeKarten
    )

    val erwarteteOriginale = mapOf(IDS to eingabeKarten, DAVON_ENTFERNT to emptySet())
    val erwarteteHinzugefuegte = emptySet<Karte>()
    testKorrekteInstanziierung(erwarteteOriginale, erwarteteHinzugefuegte, dummy)
  }

  @Test
  fun `fromYaml() Yaml-Datei wird korrekt in eine Menge von Objekten verwandelt`() {
    val dummys = getDummyKategorien("gültigeKategorien")

    val actual = dummys.size

    val expected = 2
    assertEquals(expected, actual)

    `fromYaml(7) Objekt mit der ID 7 korrekt aus der Yaml gelesen`(dummys.finde(7))
    `fromYaml(42) Objekt mit der ID 42 korrekt aus der Yaml gelesen`(dummys.finde(42))
  }

  private fun `fromYaml(7) Objekt mit der ID 7 korrekt aus der Yaml gelesen`(
    dummy: Kategorie,
  ) {
    val originaleKartenIDs = setOf(dummyKarte1())
    val davonEntfernte = emptySet<Karte>()

    val erwarteteOriginale = mapOf(IDS to originaleKartenIDs, DAVON_ENTFERNT to davonEntfernte)
    val erwarteteHinzugefuegte = setOf(dummyKarte5())
    testKorrekteInstanziierung(erwarteteOriginale, erwarteteHinzugefuegte, dummy)
  }

  private fun `fromYaml(42) Objekt mit der ID 42 korrekt aus der Yaml gelesen`(
    dummy: Kategorie,
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
      getDummyKategorien("fehlende_Kategorie")
    }
    assertThrows(IllegalArgumentException::class.java) {
      getDummyKategorien("fehlende_originale_Karten")
    }
    assertThrows(IllegalArgumentException::class.java) {
      getDummyKategorien("fehlende_hinzugefuegte_Karten-IDs")
    }
  }
}
