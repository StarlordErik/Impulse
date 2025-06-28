package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

const val DUMMY_GESEHEN = false
const val DUMMY_GELOESCHT = false
fun dummyKarte() = Karte(DUMMY_ID, dummyLocalizations(), DUMMY_GESEHEN, DUMMY_GELOESCHT)

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

        val expected = """
        |  - $ID: $DUMMY_ID
        |    $DUMMY_BEZEICHNUNG:
        |      ${Sprachen.OG}: "$DUMMY_OG"
        |      ${Sprachen.DE}: "$DUMMY_DE"
        |    $GESEHEN: $DUMMY_GESEHEN
        |    $GELOESCHT: $DUMMY_GELOESCHT
        |
        """.trimMargin()
        assertEquals(expected, actual)
    }
}
