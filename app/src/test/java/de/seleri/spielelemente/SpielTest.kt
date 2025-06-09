package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

const val TEST_SPIEL_1_EINGABE: String = "Impulse"
val TEST_SPIEL_1_YAML: String = """
    |Spiele:
    |  - ID: 1
    |    Name:
    |      OG: "$TEST_SPIEL_1_EINGABE"
    |      DE: ""
    |      EN: "$TEST_SPIEL_1_EINGABE"
    |    originale_Kategorien:
    |      IDs: [1]
    |      davon_entfernt: [1]
    |    hinzugefügte_Kategorien-IDs: [2]
    |""".trimMargin().lines().drop(1).joinToString("\n") // entfernt die erste Zeile
val TEST_SPIEL_1: Spiel = Spiel(
    id = 1, localizations = mutableMapOf(
        Sprachen.OG to TEST_SPIEL_1_EINGABE, Sprachen.DE to "", Sprachen.EN to TEST_SPIEL_1_EINGABE
    ), originaleElemente = mutableMapOf(
        IDS to listOf(TEST_KATEGORIE_1), DAVON_ENTFERNT to listOf(TEST_KATEGORIE_1)
    ), hinzugefuegteElemente = listOf(TEST_KATEGORIE_2)
)

const val TEST_SPIEL_2_EINGABE: String = "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
val TEST_SPIEL_2_YAML: String = """
    |Spiele:
    |  - ID: 2
    |    Name:
    |      OG: "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
    |      DE: "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
    |      EN: ""
    |    originale_Kategorien:
    |      IDs: [1,2]
    |      davon_entfernt: []
    |    hinzugefügte_Kategorien-IDs: []
    |""".trimMargin().lines().drop(1).joinToString("\n") // entfernt die erste Zeile
val TEST_SPIEL_2: Spiel = Spiel(
    id = 2, localizations = mutableMapOf(
        Sprachen.OG to TEST_SPIEL_2_EINGABE, Sprachen.DE to TEST_SPIEL_2_EINGABE, Sprachen.EN to ""
    ), originaleElemente = mutableMapOf(
        IDS to listOf(TEST_KATEGORIE_1, TEST_KATEGORIE_2), DAVON_ENTFERNT to emptyList()
    ), hinzugefuegteElemente = emptyList()
)

class SpielTest {
    @Test
    fun `Test eingabeToSpiel() - Konvertierung von Spielname und zugehoerigen Kategorien zu Spiel`() {
        val spiel1 = eingabeToSpiel(1, Sprachen.EN, TEST_SPIEL_1_EINGABE, listOf(TEST_KATEGORIE_1))
        spiel1.originaleElemente[DAVON_ENTFERNT] = listOf(TEST_KATEGORIE_1)
        spiel1.hinzugefuegteElemente = listOf(TEST_KATEGORIE_2)

        assertEquals(TEST_SPIEL_1.id, spiel1.id)
        assertEquals(TEST_SPIEL_1.localizations, spiel1.localizations)
        assertEquals(TEST_SPIEL_1.originaleElemente, spiel1.originaleElemente)
        assertEquals(TEST_SPIEL_1.hinzugefuegteElemente, spiel1.hinzugefuegteElemente)
        assertEquals(TEST_SPIEL_1, spiel1)

        val spiel2 = eingabeToSpiel(
            2, Sprachen.DE, TEST_SPIEL_2_EINGABE, listOf(TEST_KATEGORIE_1, TEST_KATEGORIE_2)
        )
        spiel2.hinzugefuegteElemente = emptyList()

        assertEquals(TEST_SPIEL_2.id, spiel2.id)
        assertEquals(TEST_SPIEL_2.localizations, spiel2.localizations)
        assertEquals(TEST_SPIEL_2.originaleElemente, spiel2.originaleElemente)
        assertEquals(TEST_SPIEL_2.hinzugefuegteElemente, spiel2.hinzugefuegteElemente)
        assertEquals(TEST_SPIEL_2, spiel2)
    }

    @Test
    fun `Test spielToYaml() - Konvertierung von Spiel zu YAML`() {
        assertEquals(TEST_SPIEL_1_YAML, TEST_SPIEL_1.toYaml())
        assertEquals(TEST_SPIEL_2_YAML, TEST_SPIEL_2.toYaml())
    }

    @Test
    fun `Test yamlToSpiel() - Konvertierung von YAML zu Spiel`() {
        val spiel1 = yamlToSpiele(TEST_SPIEL_1_YAML, ALLE_TEST_KATEGORIEN)[0]
        assertEquals(TEST_SPIEL_1, spiel1)

        val spiel2 = yamlToSpiele(TEST_SPIEL_2_YAML, ALLE_TEST_KATEGORIEN)[0]
        assertEquals(TEST_SPIEL_2, spiel2)
    }

    @Test
    fun `Test Rundreise yamlToSpielToYaml - Konvertierung von YAML zu Spiel und wieder zurueck`() {
        assertEquals(
            TEST_SPIEL_1_YAML, yamlToSpiele(TEST_SPIEL_1_YAML, ALLE_TEST_KATEGORIEN)[0].toYaml()
        )
        assertEquals(
            TEST_SPIEL_2_YAML, yamlToSpiele(TEST_SPIEL_2_YAML, ALLE_TEST_KATEGORIEN)[0].toYaml()
        )
    }

    @Test
    fun `Test getAlleKategorien() - Ausgabe aller Kategorien des Spiels`() {
        assertEquals(2, TEST_SPIEL_1.getAlleElemente().size)
        assertEquals(2, TEST_SPIEL_2.getAlleElemente().size)
    }

    @Test
    fun `Test getAlleKarten() - Ausgabe aller Karten des Spiels`() {
        assertEquals(2, TEST_SPIEL_1.getAlleKarten().size)
        assertEquals(2, TEST_SPIEL_2.getAlleKarten().size)
    }
}
