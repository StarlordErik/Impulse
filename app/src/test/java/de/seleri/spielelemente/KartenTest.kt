package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

const val TEST_KARTE_1_EINGABE: String = "Are you happy?"
const val TEST_KARTE_1_YAML: String = """  - ID: 1
    Text:
      DE: ""
      EN: "$TEST_KARTE_1_EINGABE"
"""
val TEST_KARTE_1: Karte = Karte(
    id = 1, localizations = mutableMapOf(
        Sprachen.DE to "", Sprachen.EN to TEST_KARTE_1_EINGABE
    )

)

const val TEST_KARTE_2_EINGABE: String = "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
const val TEST_KARTE_2_YAML: String = """  - ID: 2
    Text:
      DE: "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
      EN: ""
"""
val TEST_KARTE_2: Karte = Karte(
    id = 2, localizations = mutableMapOf(
        Sprachen.DE to TEST_KARTE_2_EINGABE, Sprachen.EN to ""
    )

)

val ALLE_TEST_KARTEN = listOf(TEST_KARTE_1, TEST_KARTE_2)

class KartenTest {

    @Test
    fun `Test eingabeToKarte() - Konvertierung von Kartentext zu Karte`() {
        val karte1 = eingabeToKarte(1, Sprachen.EN, TEST_KARTE_1_EINGABE)
        assertEquals(TEST_KARTE_1.id, karte1.id)
        assertEquals(
            TEST_KARTE_1.localizations, karte1.localizations
        )
        assertEquals(TEST_KARTE_1, karte1)

        val karte2 = eingabeToKarte(2, Sprachen.DE, TEST_KARTE_2_EINGABE)
        assertEquals(TEST_KARTE_2.id, karte2.id)
        assertEquals(
            TEST_KARTE_2.localizations, karte2.localizations
        )
        assertEquals(TEST_KARTE_2, karte2)
    }

    @Test
    fun `Test karteToYaml() - Konvertierung von Karte zu YAML`() {
        assertEquals(TEST_KARTE_1_YAML, TEST_KARTE_1.toYaml())
        assertEquals(TEST_KARTE_2_YAML, TEST_KARTE_2.toYaml())
    }

    @Test
    fun `Test yamlToKarte() - Konvertierung von YAML zu Karte`() {
        val karte1 = yamlToKarten(TEST_KARTE_1_YAML)[0]
        assertEquals(TEST_KARTE_1, karte1)

        val karte2 = yamlToKarten(TEST_KARTE_2_YAML)[0]
        assertEquals(TEST_KARTE_2, karte2)
    }

    @Test
    fun `Test Rundreise yamlToKarteToYaml - Konvertierung von YAML zu Karte und wieder zurueck`() {
        assertEquals(TEST_KARTE_1_YAML, yamlToKarten(TEST_KARTE_1_YAML)[0].toYaml())
        assertEquals(TEST_KARTE_2_YAML, yamlToKarten(TEST_KARTE_2_YAML)[0].toYaml())
    }
}
