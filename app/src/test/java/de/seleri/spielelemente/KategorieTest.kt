package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

const val TEST_KATEGORIE_1_EINGABE: String = "Level 1"
const val TEST_KATEGORIE_1_YAML: String = """  - ID: 1
    Name:
      DE: ""
      EN: "$TEST_KATEGORIE_1_EINGABE"
    ursprüngliche_Karten-IDs: []
    weitere_Karten-IDs: [1]
"""
val TEST_KATEGORIE_1: Kategorie = Kategorie(
    id = 1, localizations = Localizations(
        mutableMapOf(
            Sprachen.DE to "", Sprachen.EN to TEST_KATEGORIE_1_EINGABE
        )
    ), urspruenglicheElemente = emptyList(), weitereElemente = listOf(TEST_KARTE_1)
)

const val TEST_KATEGORIE_2_EINGABE: String = "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
const val TEST_KATEGORIE_2_YAML: String = """  - ID: 2
    Name:
      DE: "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
      EN: ""
    ursprüngliche_Karten-IDs: [1,2]
    weitere_Karten-IDs: []
"""
val TEST_KATEGORIE_2: Kategorie = Kategorie(
    id = 2, localizations = Localizations(
        mutableMapOf(
            Sprachen.DE to TEST_KATEGORIE_2_EINGABE, Sprachen.EN to ""
        )
    ), urspruenglicheElemente = listOf(TEST_KARTE_1, TEST_KARTE_2), weitereElemente = emptyList()
)

val ALLE_TEST_KATEGORIEN: List<Kategorie> = listOf(TEST_KATEGORIE_1, TEST_KATEGORIE_2)

class KategorieTest {
    @Test
    fun `Test eingabeToKategorie() - Konvertierung von Kategorienname und zugehoerigen Karten zu Kategorie`() {
        val kategorie1 = eingabeToKategorie(1, Sprachen.EN, TEST_KATEGORIE_1_EINGABE, emptyList())
        kategorie1.weitereElemente = listOf(TEST_KARTE_1)
        assertEquals(TEST_KATEGORIE_1.id, kategorie1.id)
        assertEquals(
            TEST_KATEGORIE_1.localizations.mapSpracheZuUebersetzung,
            kategorie1.localizations.mapSpracheZuUebersetzung
        )
        assertEquals(TEST_KATEGORIE_1.urspruenglicheElemente, kategorie1.urspruenglicheElemente)
        assertEquals(TEST_KATEGORIE_1.weitereElemente, kategorie1.weitereElemente)
        assertEquals(TEST_KATEGORIE_1, kategorie1)

        val kategorie2 = eingabeToKategorie(
            2, Sprachen.DE, TEST_KATEGORIE_2_EINGABE, listOf(TEST_KARTE_1, TEST_KARTE_2)
        )
        kategorie2.weitereElemente = emptyList()
        assertEquals(TEST_KATEGORIE_2.id, kategorie2.id)
        assertEquals(
            TEST_KATEGORIE_2.localizations.mapSpracheZuUebersetzung,
            kategorie2.localizations.mapSpracheZuUebersetzung
        )
        assertEquals(TEST_KATEGORIE_2.urspruenglicheElemente, kategorie2.urspruenglicheElemente)
        assertEquals(TEST_KATEGORIE_2.weitereElemente, kategorie2.weitereElemente)
        assertEquals(TEST_KATEGORIE_2, kategorie2)
    }

    @Test
    fun `Test kategorieToYaml() - Konvertierung von Kategorie zu YAML`() {
        assertEquals(TEST_KATEGORIE_1_YAML, TEST_KATEGORIE_1.toYaml())
        assertEquals(TEST_KATEGORIE_2_YAML, TEST_KATEGORIE_2.toYaml())
    }

    @Test
    fun `Test yamlToKategorie() - Konvertierung von YAML zu Kategorie`() {
        val kategorie1 = yamlToKategorien(TEST_KATEGORIE_1_YAML, ALLE_TEST_KARTEN)[0]
        assertEquals(TEST_KATEGORIE_1, kategorie1)

        val kategorie2 = yamlToKategorien(TEST_KATEGORIE_2_YAML, ALLE_TEST_KARTEN)[0]
        assertEquals(TEST_KATEGORIE_2, kategorie2)
    }

    @Test
    fun `Test Rundreise yamlToKategorieToYaml - Konvertierung von YAML zu Kategorie und wieder zurueck`() {
        assertEquals(
            TEST_KATEGORIE_1_YAML, yamlToKategorien(TEST_KATEGORIE_1_YAML, ALLE_TEST_KARTEN)[0].toYaml()
        )
        assertEquals(
            TEST_KATEGORIE_2_YAML, yamlToKategorien(TEST_KATEGORIE_2_YAML, ALLE_TEST_KARTEN)[0].toYaml()
        )
    }

    @Test
    fun `Test getAlleKarten() - Ausgabe aller Karten einer Kategorie`() {
        assertEquals(1, TEST_KATEGORIE_1.getAlleElemente().size)
        assertEquals(2, TEST_KATEGORIE_2.getAlleElemente().size)
    }
}
