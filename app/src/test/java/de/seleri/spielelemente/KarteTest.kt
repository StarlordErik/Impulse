package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test
import org.yaml.snakeyaml.Yaml

const val DUMMY_TEXT = "Text"
const val DUMMY_GESEHEN = false
const val DUMMY_GELOESCHT = false
fun dummyKarte() = Karte(DUMMY_ID, dummyLocalizations(), DUMMY_GESEHEN, DUMMY_GELOESCHT)
fun yamlDummy() = """
        |  - $ID: $DUMMY_ID
        |    $DUMMY_BEZEICHNUNG:
        |      ${Sprachen.OG}: "$DUMMY_OG"
        |      ${Sprachen.DE}: "$DUMMY_DE"
        |    $GESEHEN: $DUMMY_GESEHEN
        |    $GELOESCHT: $DUMMY_GELOESCHT
        |
        """.trimMargin()

fun getDummyKarten(elementart: String): Set<Karte> =
    getDummyDaten("Karten.yml", elementart,Karte::fromYaml)

class KarteTest {

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
        val dummy = dummyKarte()

        val actual = dummy.toYaml()

        val expected = yamlDummy()
        assertEquals(expected, actual)
    }

    @Test
    fun `Rundreise Yaml zu Objekt zu Yaml`() {
        val yamlDummy1 = yamlDummy()

        val dummyDaten = (Yaml().load(yamlDummy1) as List<Map<String, Any>>).first()
        val dummy = Karte.fromYaml(dummyDaten).first()

        val yamlDummy2 = dummy.toYaml()
        assertEquals(yamlDummy1, yamlDummy2)
    }

    /**
     * prüft nur die Instanziierung der neuen Attribute von Karte im Vergleich zur Superklasse
     */
    private fun testKorrekteInstanziierung(
        erwartetesGesehen: Boolean, erwartetesGeloescht: Boolean, dummy: Karte
    ) {
        val actualGesehen = dummy.gesehen
        val actualGeloescht = dummy.geloescht

        assertEquals(erwartetesGesehen, actualGesehen)
        assertEquals(erwartetesGeloescht, actualGeloescht)
    }

    @Test
    fun `fromEingabe() korrekte Instanziierung durch Eingabe`() {
        val eingabeID = DUMMY_ID
        val eingabeSprache = Sprachen.DE
        val eingabeText = DUMMY_TEXT

        val dummy = Karte.fromEingabe(eingabeID, eingabeSprache, eingabeText)

        val erwartetesGesehen = false
        val erwartetesGeloescht = false
        testKorrekteInstanziierung(erwartetesGesehen, erwartetesGeloescht, dummy)
    }


    @Test
    fun `fromYaml() Yaml-Datei wird korrekt in eine Menge von Objekten verwandelt`() {
        val dummys = getDummyKarten("gültigeKarten")

        assertEquals(2, dummys.size)

        `fromYaml(7) Objekt mit der ID 7 korrekt aus der Yaml gelesen`(dummys.finde(7))
        `fromYaml(42) Objekt mit der ID 42 korrekt aus der Yaml gelesen`(dummys.finde(42))
    }

    private fun `fromYaml(7) Objekt mit der ID 7 korrekt aus der Yaml gelesen`(
        dummy: Karte
    ) {
        testKorrekteInstanziierung(
            erwartetesGesehen = true, erwartetesGeloescht = false, dummy
        )
    }

    private fun `fromYaml(42) Objekt mit der ID 42 korrekt aus der Yaml gelesen`(
        dummy: Karte
    ) {
        testKorrekteInstanziierung(
            erwartetesGesehen = false, erwartetesGeloescht = true, dummy
        )
    }

    @Test
    fun `fromYaml() Exception bei fehlenden Yaml-Attributen`() {
        assertThrows(IllegalArgumentException::class.java) {
            getDummyKarten("fehlendeKarten")
        }
        assertThrows(IllegalArgumentException::class.java) {
            getDummyKarten("fehlendesGesehen")
        }
        assertThrows(IllegalArgumentException::class.java) {
            getDummyKarten("fehlendesGelöscht")
        }
    }
}
