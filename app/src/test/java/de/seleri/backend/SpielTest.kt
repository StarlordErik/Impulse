package de.seleri.backend

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import org.yaml.snakeyaml.Yaml

fun alleDummySpiele(): Set<Spiel> {
  val dummySpiele = getDummySpiele("dummySpiele")
  val actual = dummySpiele.size

  val expected = 5
  assertEquals(expected, actual)

  return dummySpiele
}

fun dummySpiel1(): Spiel = alleDummySpiele().finde(1)
fun dummySpiel2(): Spiel = alleDummySpiele().finde(2)
fun dummySpiel3(): Spiel = alleDummySpiele().finde(3)
fun dummySpiel4(): Spiel = alleDummySpiele().finde(4)
fun dummySpiel5(): Spiel = alleDummySpiele().finde(5)

fun yamlDummySpiel(): String = """
        |  - $ID: $DUMMY_ID
        |    $DUMMY_NAME:
        |      ${Sprachen.OG}: "$DUMMY_OG"
        |      ${Sprachen.DE}: "$DUMMY_DE"
        |    $ORIGINALE$KATEGORIEN:
        |      $IDS: [1,2,3,4]
        |      $DAVON_ENTFERNT: [2,4]
        |    $HINZUGEFUEGTE$KATEGORIEN$BINDESTRICH_IDS: []
        |
        """.trimMargin()

fun getDummySpiele(elementart: String): Set<Spiel> =
  getDummyDaten("Spiel.yml", elementart) {Spiel.fromYaml(it, alleDummyKategorien())}

fun dummyOriginaleKategorienIDs(): MutableSet<Kategorie> =
  mutableSetOf(dummyKategorie1(), dummyKategorie2(), dummyKategorie3(), dummyKategorie4())

fun dummyDavonEntfernteKategorien(): MutableSet<Kategorie> = mutableSetOf(dummyKategorie2(), dummyKategorie4())
fun dummyOriginaleKategorien(): Map<String, MutableSet<Kategorie>> =
  mapOf(IDS to dummyOriginaleKategorienIDs(), DAVON_ENTFERNT to dummyDavonEntfernteKategorien())

fun dummyHinzugefuegteKategorien(): MutableSet<Kategorie> = mutableSetOf()
fun dummySpiel() = Spiel(
  DUMMY_ID, dummyLocalizations(), dummyOriginaleKategorien(), dummyHinzugefuegteKategorien()
)

class SpielTest {

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
    val dummy = dummySpiel()

    val actual = dummy.toYaml()

    val expected = yamlDummySpiel()
    assertEquals(expected, actual)
  }

  @Test
  fun `Rundreise Yaml zu Objekt zu Yaml`() {
    val yamlDummy1 = yamlDummySpiel()

    val dummyDaten = (Yaml().load(yamlDummy1) as List<Map<String, Any>>).first()
    val dummy = Spiel.fromYaml(dummyDaten, alleDummyKategorien()).first()

    val yamlDummy2 = dummy.toYaml()
    assertEquals(yamlDummy1, yamlDummy2)
  }

  @Test
  fun `getKategorien() gibt alle Kategorien des Spiels zurueck`() {
    val dummy = dummySpiel()

    val actual = dummy.getKategorien()

    val expected = setOf(dummyKategorie1(), dummyKategorie2(), dummyKategorie3(), dummyKategorie4())
    assertEquals(expected, actual)
  }

  @Test
  fun `getAktuelleKategorien() gibt alle Kategorien zurueck, die nicht entfernt wurden`() {
    val dummy = dummySpiel()

    val actual = dummy.getAktuelleKategorien()

    val expected = setOf(dummyKategorie1(), dummyKategorie3())
    assertEquals(expected, actual)
  }

  @Test
  fun `getKarten() gibt alle Karten des Spiels zurueck`() {
    val dummy = dummySpiel()

    val actual = dummy.getKarten()

    val expected = setOf(dummyKarte1(), dummyKarte2(), dummyKarte3(), dummyKarte4())
    assertEquals(expected, actual)
  }

  @Test
  fun `getAktuelleKarten() gibt alle Karten des Spiels zurueck, die nicht entfernt worden sind`() {
    val dummy = dummySpiel()

    val actual = dummy.getAktuelleKarten()

    val expected = setOf(dummyKarte1(), dummyKarte3())
    assertEquals(expected, actual)
  }

  @Test
  fun `getUngeseheneKarten() gibt alle noch nicht gesehenen Karten des Spiels zurueck`() {
    val dummy = dummySpiel()

    val actual = dummy.getUngeseheneKarten()

    val expected = setOf(dummyKarte1())
    assertEquals(expected, actual)
  }

  @Test
  fun `setKartenUngesehen() setzt alle Karten auf ungesehen`() {
    val dummy = dummySpiel()

    dummy.setKartenUngesehen()
    val actual = dummy.getUngeseheneKarten()

    val expected = dummy.getAktuelleKarten()
    assertEquals(expected, actual)
  }

  @Test
  fun `kategorienHinzufuegen() fuegt neue Kategorien zum Spiel hinzu`() {
    val dummy = dummySpiel()
    val neueKategorien = setOf(dummyKategorie2(), dummyKategorie5())

    dummy.kategorienHinzufuegen(neueKategorien)
    val actual = dummy.getAktuelleKarten()

    val expected = setOf(dummyKarte1(), dummyKarte2(), dummyKarte3(), dummyKarte5())
    assertEquals(expected, actual)
  }

  @Test
  fun `kategorieEntfernen() entfernt eine Kategorie, die bislang Teil des Spiels war`() {
    val dummy = dummySpiel()
    val zuEntfernendeKategorie = dummyKategorie1()
    assertTrue(dummy.getAktuelleKategorien().contains(zuEntfernendeKategorie))

    val expected = dummy.getAktuelleKategorien() - zuEntfernendeKategorie

    dummy.kategorieEntfernen(zuEntfernendeKategorie)
    val actual = dummy.getAktuelleKategorien()

    assertEquals(expected, actual)
  }

  /**
   * prüft nur die Instanziierung der neuen Attribute von der Sammlung im Vergleich zur Superklasse
   */
  private fun testKorrekteInstanziierung(
    erwarteteOriginale: Map<String, Collection<Kategorie>>,
    erwarteteHinzugefuegte: Collection<Kategorie>,
    dummy: Spiel,
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
    val eingabeKategorien = alleDummyKategorien()

    val dummy = Spiel.fromEingabe(
      eingabeID, eingabeSprache, eingabeName, eingabeKategorien
    )

    val erwarteteOriginale = mapOf(IDS to eingabeKategorien, DAVON_ENTFERNT to emptySet())
    val erwarteteHinzugefuegte = emptySet<Kategorie>()
    testKorrekteInstanziierung(erwarteteOriginale, erwarteteHinzugefuegte, dummy)
  }

  @Test
  fun `fromYaml() Yaml-Datei wird korrekt in eine Menge von Objekten verwandelt`() {
    val dummys = getDummySpiele("gültigeSpiele")

    val actual = dummys.size

    val expected = 2
    assertEquals(expected, actual)

    `fromYaml(7) Objekt mit der ID 7 korrekt aus der Yaml gelesen`(dummys.finde(7))
    `fromYaml(42) Objekt mit der ID 42 korrekt aus der Yaml gelesen`(dummys.finde(42))
  }

  private fun `fromYaml(7) Objekt mit der ID 7 korrekt aus der Yaml gelesen`(
    dummy: Spiel,
  ) {
    val originaleKategorienIDs = setOf(dummyKategorie1())
    val davonEntfernte = emptySet<Kategorie>()

    val erwarteteOriginale = mapOf(IDS to originaleKategorienIDs, DAVON_ENTFERNT to davonEntfernte)
    val erwarteteHinzugefuegte = setOf(dummyKategorie5())
    testKorrekteInstanziierung(erwarteteOriginale, erwarteteHinzugefuegte, dummy)
  }

  private fun `fromYaml(42) Objekt mit der ID 42 korrekt aus der Yaml gelesen`(
    dummy: Spiel,
  ) {
    val originaleKategorienIDs = setOf(dummyKategorie1(), dummyKategorie2(), dummyKategorie3(), dummyKategorie4())
    val davonEntfernte = setOf(dummyKategorie2(), dummyKategorie4())

    val erwarteteOriginale = mapOf(IDS to originaleKategorienIDs, DAVON_ENTFERNT to davonEntfernte)
    val erwarteteHinzugefuegte = emptySet<Kategorie>()
    testKorrekteInstanziierung(erwarteteOriginale, erwarteteHinzugefuegte, dummy)
  }

  @Test
  fun `fromYaml() Exception bei fehlenden Yaml-Attributen`() {
    assertThrows(IllegalArgumentException::class.java) {
      getDummySpiele("fehlende_Spiele")
    }
    assertThrows(IllegalArgumentException::class.java) {
      getDummySpiele("fehlende_originale_Kategorien")
    }
    assertThrows(IllegalArgumentException::class.java) {
      getDummySpiele("fehlende_hinzugefuegte_Kategorien-IDs")
    }
  }
}
