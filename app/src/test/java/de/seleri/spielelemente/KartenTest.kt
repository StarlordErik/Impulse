package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test
import org.yaml.snakeyaml.Yaml

const val TEST_KARTE_1_EINGABE: String = "Are you happy?"
fun testKarte1Yaml(): String = """
    |Karten:
    |  - ID: 1
    |    Text:
    |      OG: "$TEST_KARTE_1_EINGABE"
    |      DE: ""
    |      EN: "$TEST_KARTE_1_EINGABE"
    |    gesehen: false
    |    gelöscht: true
    |""".trimMargin().lines().drop(1).joinToString("\n")

fun testKarte1(): Karte = Karte(
    id = 1, localizations = mutableMapOf(
        Sprachen.OG to TEST_KARTE_1_EINGABE, Sprachen.DE to "", Sprachen.EN to TEST_KARTE_1_EINGABE
    ), gesehen = false, geloescht = true
)

const val TEST_KARTE_2_EINGABE: String = "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
fun testKarte2Yaml(): String = """
    |Karten:
    |  - ID: 2
    |    Text:
    |      OG: "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
    |      DE: "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
    |      EN: ""
    |    gesehen: true
    |    gelöscht: false
    |""".trimMargin().lines().drop(1).joinToString("\n")

fun testKarte2(): Karte = Karte(
    id = 2, localizations = mutableMapOf(
        Sprachen.OG to TEST_KARTE_2_EINGABE, Sprachen.DE to TEST_KARTE_2_EINGABE, Sprachen.EN to ""
    ), gesehen = true, geloescht = false
)

fun alleTestKarten() = listOf(testKarte1(), testKarte2())

const val OG_TEXT = "Original"
const val DE_TEXT = "deutscher Text"
const val EN_TEXT = "English Text"

class KartenTest {

    @Test
    fun `Test eingabeToKarte() - Konvertierung von Kartentext zu Karte`() {
        val karte1 = Karte.fromEingabe(1, Sprachen.EN, TEST_KARTE_1_EINGABE)
        assertEquals(testKarte1().id, karte1.id)
        assertEquals(testKarte1().localizations, karte1.localizations)
        karte1.geloescht = true
        assertEquals(testKarte1(), karte1)

        val karte2 = Karte.fromEingabe(2, Sprachen.DE, TEST_KARTE_2_EINGABE)
        assertEquals(testKarte2().id, karte2.id)
        assertEquals(testKarte2().localizations, karte2.localizations)
        karte2.gesehen = true
        assertEquals(testKarte2(), karte2)
    }

    @Test
    fun `Test karteToYaml() - Konvertierung von Karte zu YAML`() {
        assertEquals(testKarte1Yaml(), testKarte1().toYaml())
        assertEquals(testKarte2Yaml(), testKarte2().toYaml())
    }
/*
    @Test
    fun `Test yamlToKarte() - Konvertierung von YAML zu Karte`() {
        val yamlInput1 = Yaml().load<Map<String, Any>>(testKarte1Yaml())
        val karte1 = Karte.fromYaml(yamlInput1)[0]
        assertEquals(testKarte1(), karte1)

        val yamlInput2 = Yaml().load<Map<String, Any>>(testKarte2Yaml())
        val karte2 = Karte.fromYaml(yamlInput2)[0]
        assertEquals(testKarte2(), karte2)
    }

    @Test
    fun `Test Rundreise yamlToKarteToYaml - Konvertierung von YAML zu Karte und wieder zurueck`() {
        val yamlInput1 = Yaml().load<Map<String, Any>>(testKarte1Yaml())
        val karte1 = Karte.fromYaml(yamlInput1)[0]
        assertEquals(testKarte1Yaml(), karte1.toYaml())

        val yamlInput2 = Yaml().load<Map<String, Any>>(testKarte2Yaml())
        val karte2 = Karte.fromYaml(yamlInput2)[0]
        assertEquals(testKarte2Yaml(), karte2.toYaml())
    }
*/
    @Test
    fun `Test setUebersetzung() - Ueberarbeiten der Uebersetzungen`() {
        val karte1 = testKarte1()
        val karte2 = testKarte2()

        karte1.setUebersetzung(Sprachen.OG, OG_TEXT)
        karte1.setUebersetzung(Sprachen.DE, DE_TEXT)
        karte1.setUebersetzung(Sprachen.EN, EN_TEXT)

        karte2.setUebersetzung(Sprachen.OG, OG_TEXT)
        karte2.setUebersetzung(Sprachen.DE, DE_TEXT)
        karte2.setUebersetzung(Sprachen.EN, EN_TEXT)

        assertEquals(TEST_KARTE_1_EINGABE, karte1.localizations[Sprachen.OG])
        assertEquals(DE_TEXT, karte1.localizations[Sprachen.DE])
        assertEquals(EN_TEXT, karte1.localizations[Sprachen.EN])

        assertEquals(TEST_KARTE_2_EINGABE, karte2.localizations[Sprachen.OG])
        assertEquals(DE_TEXT, karte2.localizations[Sprachen.DE])
        assertEquals(EN_TEXT, karte2.localizations[Sprachen.EN])
    }

}
