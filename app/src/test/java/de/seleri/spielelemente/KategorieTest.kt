package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

const val TEST_KATEGORIE_1_EINGABE: String = "Level 1"
const val TEST_KATEGORIE_1_YAML: String =
    """  - ID: 1
    Name:
      DE: ""
      EN: "$TEST_KATEGORIE_1_EINGABE"
    ursprüngliche_Karten-IDs: []
    weitere_Karten-IDs: [1]
"""
val TEST_KATEGORIE_1: Kategorie = Kategorie(
    id = 1,
    localizedNamen = mapOf(
        Sprachen.DE to "",
        Sprachen.EN to TEST_KATEGORIE_1_EINGABE
    ),
    urspruenglicheKarten = emptyList(),
    weitereKarten = listOf(TEST_KARTE_1)
)

const val TEST_KATEGORIE_2_EINGABE: String = "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
const val TEST_KATEGORIE_2_YAML: String =
    """  - ID: 2
    Name:
      DE: "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
      EN: ""
    ursprüngliche_Karten-IDs: [1,2]
    weitere_Karten-IDs: []
"""
val TEST_KATEGORIE_2: Kategorie = Kategorie(
    id = 2,
    localizedNamen = mapOf(
        Sprachen.DE to TEST_KATEGORIE_2_EINGABE,
        Sprachen.EN to ""
    ),
    urspruenglicheKarten = listOf(TEST_KARTE_1, TEST_KARTE_2),
    weitereKarten = emptyList()
)

val ALLE_TEST_KATEGORIEN: List<Kategorie> = listOf(TEST_KATEGORIE_1, TEST_KATEGORIE_2)

class KategorieTest {
    @Test
    fun `Test eingabeToKategorie() - Konvertierung von Kategorienname und zugehoerigen Karten zu Kategorie`() {
        val kategorie1 = eingabeToKategorie(1, Sprachen.EN, TEST_KATEGORIE_1_EINGABE, emptyList())
        kategorie1.weitereKarten = listOf(TEST_KARTE_1)
        assertEquals(TEST_KATEGORIE_1.id, kategorie1.id)
        assertEquals(
            TEST_KATEGORIE_1.localizedNamen[Sprachen.DE], kategorie1.localizedNamen[Sprachen.DE]
        )
        assertEquals(
            TEST_KATEGORIE_1.localizedNamen[Sprachen.EN], kategorie1.localizedNamen[Sprachen.EN]
        )
        assertEquals(TEST_KATEGORIE_1.localizedNamen, kategorie1.localizedNamen)
        assertEquals(TEST_KATEGORIE_1.urspruenglicheKarten, kategorie1.urspruenglicheKarten)
        assertEquals(TEST_KATEGORIE_1.weitereKarten, kategorie1.weitereKarten)
        assertEquals(TEST_KATEGORIE_1, kategorie1)

        val kategorie2 = eingabeToKategorie(
            2,
            Sprachen.DE,
            TEST_KATEGORIE_2_EINGABE,
            listOf(TEST_KARTE_1, TEST_KARTE_2)
        )
        kategorie2.weitereKarten = emptyList()
        assertEquals(TEST_KATEGORIE_2.id, kategorie2.id)
        assertEquals(
            TEST_KATEGORIE_2.localizedNamen[Sprachen.DE], kategorie2.localizedNamen[Sprachen.DE])
        assertEquals(
            TEST_KATEGORIE_2.localizedNamen[Sprachen.EN], kategorie2.localizedNamen[Sprachen.EN])
        assertEquals(TEST_KATEGORIE_2.localizedNamen, kategorie2.localizedNamen)
        assertEquals(TEST_KATEGORIE_2.urspruenglicheKarten, kategorie2.urspruenglicheKarten)
        assertEquals(TEST_KATEGORIE_2.weitereKarten, kategorie2.weitereKarten)
        assertEquals(TEST_KATEGORIE_2, kategorie2)
    }

    @Test
    fun `Test kategorieToYaml() - Konvertierung von Kategorie zu YAML`() {
        assertEquals(TEST_KATEGORIE_1_YAML, TEST_KATEGORIE_1.toYaml())
        assertEquals(TEST_KATEGORIE_2_YAML, TEST_KATEGORIE_2.toYaml())
    }

    @Test
    fun `Test yamlToKategorie() - Konvertierung von YAML zu Kategorie`() {
        val kategorie1 = yamlToKategorie(TEST_KATEGORIE_1_YAML, ALLE_TEST_KARTEN)
        assertEquals(TEST_KATEGORIE_1.id, kategorie1.id)
        assertEquals(
            TEST_KATEGORIE_1.localizedNamen[Sprachen.DE], kategorie1.localizedNamen[Sprachen.DE]
        )
        assertEquals(
            TEST_KATEGORIE_1.localizedNamen[Sprachen.EN], kategorie1.localizedNamen[Sprachen.EN]
        )
        assertEquals(TEST_KATEGORIE_1.localizedNamen, kategorie1.localizedNamen)
        assertEquals(TEST_KATEGORIE_1.urspruenglicheKarten, kategorie1.urspruenglicheKarten)
        assertEquals(TEST_KATEGORIE_1.weitereKarten, kategorie1.weitereKarten)
        assertEquals(TEST_KATEGORIE_1, kategorie1)

        val kategorie2 = yamlToKategorie(TEST_KATEGORIE_2_YAML, ALLE_TEST_KARTEN)
        assertEquals(TEST_KATEGORIE_2.id, kategorie2.id)
        assertEquals(
            TEST_KATEGORIE_2.localizedNamen[Sprachen.DE], kategorie2.localizedNamen[Sprachen.DE])
        assertEquals(
            TEST_KATEGORIE_2.localizedNamen[Sprachen.EN], kategorie2.localizedNamen[Sprachen.EN])
        assertEquals(TEST_KATEGORIE_2.localizedNamen, kategorie2.localizedNamen)
        assertEquals(TEST_KATEGORIE_2.urspruenglicheKarten, kategorie2.urspruenglicheKarten)
        assertEquals(TEST_KATEGORIE_2.weitereKarten, kategorie2.weitereKarten)
        assertEquals(TEST_KATEGORIE_2, kategorie2)
    }

    @Test
    fun `Test Rundreise yamlToKategorieToYaml - Konvertierung von YAML zu Kategorie und wieder zurueck`() {
        assertEquals(TEST_KATEGORIE_1_YAML, yamlToKategorie(TEST_KATEGORIE_1_YAML, ALLE_TEST_KARTEN).toYaml())
        assertEquals(TEST_KATEGORIE_2_YAML, yamlToKategorie(TEST_KATEGORIE_2_YAML, ALLE_TEST_KARTEN).toYaml())
    }

    @Test
    fun `Test getAlleKarten() - Ausgabe aller Karten einer Kategorie`() {
        assertEquals(1, TEST_KATEGORIE_1.getAlleKarten().size)
        assertEquals(2, TEST_KATEGORIE_2.getAlleKarten().size)
    }
}
