package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

const val TEST_SPIEL_1_EINGABE: String = "Impulse"
const val TEST_SPIEL_1_YAML: String = """  - ID: 1
    Name:
      DE: ""
      EN: "Impulse"
    ursprüngliche_Kategorien-IDs: []
    weitere_Kategorien-IDs: [1]
"""
val TEST_SPIEL_1: Spiel = Spiel(
    id = 1, localizations = Localizations(
        mapOf(
            Sprachen.DE to "", Sprachen.EN to TEST_SPIEL_1_EINGABE
        )
    ), urspruenglicheElemente = emptyList(), weitereElemente = listOf(TEST_KATEGORIE_1)
)

const val TEST_SPIEL_2_EINGABE: String = "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
const val TEST_SPIEL_2_YAML: String = """  - ID: 2
    Name:
      DE: "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
      EN: ""
    ursprüngliche_Kategorien-IDs: [1,2]
    weitere_Kategorien-IDs: []
"""
val TEST_SPIEL_2: Spiel = Spiel(
    id = 2,
    localizations = Localizations(
        mapOf(
            Sprachen.DE to TEST_SPIEL_2_EINGABE, Sprachen.EN to ""
        )
    ),
    urspruenglicheElemente = listOf(TEST_KATEGORIE_1, TEST_KATEGORIE_2),
    weitereElemente = emptyList()
)

class SpielTest {
    @Test
    fun `Test eingabeToSpiel() - Konvertierung von Spielname und zugehoerigen Kategorien zu Spiel`() {
        val spiel1 = eingabeToSpiel(1, Sprachen.EN, TEST_SPIEL_1_EINGABE, emptyList())
        spiel1.weitereElemente = listOf(TEST_KATEGORIE_1)

        assertEquals(TEST_SPIEL_1.id, spiel1.id)
        assertEquals(TEST_SPIEL_1.localizations, spiel1.localizations)
        assertEquals(TEST_SPIEL_1.urspruenglicheElemente, spiel1.urspruenglicheElemente)
        assertEquals(TEST_SPIEL_1.weitereElemente, spiel1.weitereElemente)
        assertEquals(TEST_SPIEL_1, spiel1)

        val spiel2 = eingabeToSpiel(
            2, Sprachen.DE, TEST_SPIEL_2_EINGABE, listOf(TEST_KATEGORIE_1, TEST_KATEGORIE_2)
        )
        spiel2.weitereElemente = emptyList()

        assertEquals(TEST_SPIEL_2.id, spiel2.id)
        assertEquals(TEST_SPIEL_2.localizations, spiel2.localizations)
        assertEquals(TEST_SPIEL_2.urspruenglicheElemente, spiel2.urspruenglicheElemente)
        assertEquals(TEST_SPIEL_2.weitereElemente, spiel2.weitereElemente)
        assertEquals(TEST_SPIEL_2, spiel2)
    }

    @Test
    fun `Test spielToYaml() - Konvertierung von Spiel zu YAML`() {
        assertEquals(TEST_SPIEL_1_YAML, TEST_SPIEL_1.toYaml())
        assertEquals(TEST_SPIEL_2_YAML, TEST_SPIEL_2.toYaml())
    }

    @Test
    fun `Test yamlToSpiel() - Konvertierung von YAML zu Spiel`() {
        val spiel1 = yamlToSpiel(TEST_SPIEL_1_YAML, ALLE_TEST_KATEGORIEN)
        assertEquals(TEST_SPIEL_1, spiel1)

        val spiel2 = yamlToSpiel(TEST_SPIEL_2_YAML, ALLE_TEST_KATEGORIEN)
        assertEquals(TEST_SPIEL_2, spiel2)
    }

    @Test
    fun `Test Rundreise yamlToSpielToYaml - Konvertierung von YAML zu Spiel und wieder zurueck`() {
        assertEquals(
            TEST_SPIEL_1_YAML, yamlToSpiel(TEST_SPIEL_1_YAML, ALLE_TEST_KATEGORIEN).toYaml()
        )
        assertEquals(
            TEST_SPIEL_2_YAML, yamlToSpiel(TEST_SPIEL_2_YAML, ALLE_TEST_KATEGORIEN).toYaml()
        )
    }

    @Test
    fun `Test getAlleKategorien() - Ausgabe aller Kategorien des Spiels`() {
        assertEquals(1, TEST_SPIEL_1.getAlleElemente().size)
        assertEquals(2, TEST_SPIEL_2.getAlleElemente().size)
    }

    @Test
    fun `Test getAlleKarten() - Ausgabe aller Karten des Spiels`() {
        assertEquals(1, TEST_SPIEL_1.getAlleKarten().size)
        assertEquals(2, TEST_SPIEL_2.getAlleKarten().size)
    }
}
