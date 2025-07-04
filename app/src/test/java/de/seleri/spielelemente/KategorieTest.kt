package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.yaml.snakeyaml.Yaml

fun dummyKategorie() = Kategorie(DUMMY_ID, dummyLocalizations(), dummyOriginaleKarten(), dummyHinzugefuegteKarten()
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
    fun `getAktuelleKarten() gibt alle Karten der Sammlung zurueck, die nicht entfernt worden sind`() {
        val dummy = dummyKategorie()

        val actual = dummy.getAktuelleKarten()

        val expected = setOf(dummyKarte1(), dummyKarte3())
        assertEquals(expected, actual)
    }

    @Test
    fun `getUngeseheneKarten() gibt alle noch nicht gesehenen Karten der Sammlung zurueck`() {
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
    fun `kartenHinzufuegen() fuegt neue Karten zur Sammlung hinzu`() {
        val dummy = dummyKategorie()
        val neueKarten = setOf(dummyKarte2(), dummyKarte5())

        dummy.kartenHinzufuegen(neueKarten)
        val actual = dummy.getAktuelleKarten()

        val expected = setOf(dummyKarte1(), dummyKarte2(), dummyKarte3(), dummyKarte5())
        assertEquals(expected, actual)
    }

    @Test
    fun `karteEntfernen() entfernt eine Karten, die bislang Teil der Kategorie ist`() {
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
        erwarteteOriginale: Map<String, Collection<Karte>>,
        erwarteteHinzugefuegte: Collection<Karte>,
        dummy: Kategorie
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

}
