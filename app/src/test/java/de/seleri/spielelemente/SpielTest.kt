package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

const val TEST_SPIEL_1_EINGABE: String = "Impulse"
fun testSpiel1Yaml(): String = """
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
    |""".trimMargin().lines().drop(1).joinToString("\n")

fun testSpiel1(): Spiel = Spiel(
    id = 1, localizations = mutableMapOf(
        Sprachen.OG to TEST_SPIEL_1_EINGABE, Sprachen.DE to "", Sprachen.EN to TEST_SPIEL_1_EINGABE
    ), originaleElemente = mapOf(
        IDS to mutableSetOf(testKategorie1()), DAVON_ENTFERNT to mutableSetOf(testKategorie1())
    ), hinzugefuegteElemente = mutableSetOf(testKategorie2())
)

const val TEST_SPIEL_2_EINGABE: String = "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
fun testSpiel2Yaml(): String = """
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
    |""".trimMargin().lines().drop(1).joinToString("\n")

fun testSpiel2(): Spiel = Spiel(
    id = 2, localizations = mutableMapOf(
        Sprachen.OG to TEST_SPIEL_2_EINGABE, Sprachen.DE to TEST_SPIEL_2_EINGABE, Sprachen.EN to ""
    ), originaleElemente = mapOf(
        IDS to mutableSetOf(testKategorie1(), testKategorie2()), DAVON_ENTFERNT to mutableSetOf()
    ), hinzugefuegteElemente = mutableSetOf()
)

class SpielTest {

    @Test
    fun `Test eingabeToSpiel() - Konvertierung von Spielname und zugehoerigen Kategorien zu Spiel`() {
        val spiel1 = Spiel.fromEingabe(1, Sprachen.EN, TEST_SPIEL_1_EINGABE, listOf(testKategorie1()))
        spiel1.originaleElemente[DAVON_ENTFERNT]!!.clear()
        spiel1.originaleElemente[DAVON_ENTFERNT]!!.add(testKategorie1())
        spiel1.hinzugefuegteElemente.clear()
        spiel1.hinzugefuegteElemente.add(testKategorie2())

        assertEquals(testSpiel1().id, spiel1.id)
        assertEquals(testSpiel1().localizations, spiel1.localizations)
        assertEquals(testSpiel1().originaleElemente, spiel1.originaleElemente)
        assertEquals(testSpiel1().hinzugefuegteElemente, spiel1.hinzugefuegteElemente)
        assertEquals(testSpiel1(), spiel1)

        val spiel2 = Spiel.fromEingabe(
            2, Sprachen.DE, TEST_SPIEL_2_EINGABE, listOf(testKategorie1(), testKategorie2())
        )
        spiel2.hinzugefuegteElemente.clear()

        assertEquals(testSpiel2().id, spiel2.id)
        assertEquals(testSpiel2().localizations, spiel2.localizations)
        assertEquals(testSpiel2().originaleElemente, spiel2.originaleElemente)
        assertEquals(testSpiel2().hinzugefuegteElemente, spiel2.hinzugefuegteElemente)
        assertEquals(testSpiel2(), spiel2)
    }

    @Test
    fun `Test spielToYaml() - Konvertierung von Spiel zu YAML`() {
        assertEquals(testSpiel1Yaml(), testSpiel1().toYaml())
        assertEquals(testSpiel2Yaml(), testSpiel2().toYaml())
    }
/*
    @Test
    fun `Test yamlToSpiel() - Konvertierung von YAML zu Spiel`() {
        val spiel1 = Spiel.fromYaml(testSpiel1Yaml(), alleTestKategorien())[0]
        assertEquals(testSpiel1(), spiel1)

        val spiel2 = Spiel.fromYaml(testSpiel2Yaml(), alleTestKategorien())[0]
        assertEquals(testSpiel2(), spiel2)
    }

    @Test
    fun `Test Rundreise yamlToSpielToYaml - Konvertierung von YAML zu Spiel und wieder zurueck`() {
        assertEquals(
            testSpiel1Yaml(), Spiel.fromYaml(testSpiel1Yaml(), alleTestKategorien())[0].toYaml()
        )
        assertEquals(
            testSpiel2Yaml(), Spiel.fromYaml(testSpiel2Yaml(), alleTestKategorien())[0].toYaml()
        )
    }
*/
    @Test
    fun `Test getAlleKategorien() - Ausgabe aller Kategorien des Spiels`() {
        assertEquals(2, testSpiel1().getAlleKategorien().size)
        assertEquals(2, testSpiel2().getAlleKategorien().size)
    }

    @Test
    fun `Test getAlleAktuellenKategorien() - Ausgabe aller aktuellen Kategorien des Spiels`() {
        assertEquals(1, testSpiel1().getAlleAktuellenKategorien().size)
        assertEquals(2, testSpiel2().getAlleAktuellenKategorien().size)
    }

    @Test
    fun `Test getAlleKarten() - Ausgabe aller Karten des Spiels`() {
        assertEquals(2, testSpiel1().getAlleKarten().size)
        assertEquals(2, testSpiel2().getAlleKarten().size)
    }

    @Test
    fun `Test getAlleAktuellenKarten() - Ausgabe aller aktuellen Karten des Spiels`() {
        assertEquals(2, testSpiel1().getAlleAktuellenKarten().size)
        assertEquals(2, testSpiel2().getAlleAktuellenKarten().size)
    }
}
