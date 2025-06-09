package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

const val TEST_KATEGORIE_1_EINGABE: String = "Level 1"
fun testKategorie1Yaml(): String = """
    |Kategorien:
    |  - ID: 1
    |    Name:
    |      OG: "$TEST_KATEGORIE_1_EINGABE"
    |      DE: ""
    |      EN: "$TEST_KATEGORIE_1_EINGABE"
    |    originale_Karten:
    |      IDs: [1]
    |      davon_entfernt: [1]
    |    hinzugefügte_Karten-IDs: [2]
    |""".trimMargin().lines().drop(1).joinToString("\n")

fun testKategorie1(): Kategorie = Kategorie(
    id = 1,
    localizations = mutableMapOf(
        Sprachen.OG to TEST_KATEGORIE_1_EINGABE,
        Sprachen.DE to "",
        Sprachen.EN to TEST_KATEGORIE_1_EINGABE
    ),
    originaleElemente = mutableMapOf(
        IDS to listOf(testKarte1()),
        DAVON_ENTFERNT to listOf(testKarte1())
    ),
    hinzugefuegteElemente = listOf(testKarte2())
)

const val TEST_KATEGORIE_2_EINGABE: String = "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
fun testKategorie2Yaml(): String = """
    |Kategorien:
    |  - ID: 2
    |    Name:
    |      OG: "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
    |      DE: "^ß´\tü+\nöä#<,.-°!\"§$ %&/()=?`Ü*ÖÄ'>;:_²³{[]}\\@€~|"
    |      EN: ""
    |    originale_Karten:
    |      IDs: [1,2]
    |      davon_entfernt: []
    |    hinzugefügte_Karten-IDs: []
    |""".trimMargin().lines().drop(1).joinToString("\n")

fun testKategorie2(): Kategorie = Kategorie(
    id = 2,
    localizations = mutableMapOf(
        Sprachen.OG to TEST_KATEGORIE_2_EINGABE,
        Sprachen.DE to TEST_KATEGORIE_2_EINGABE,
        Sprachen.EN to ""
    ),
    originaleElemente = mutableMapOf(
        IDS to listOf(testKarte1(), testKarte2()),
        DAVON_ENTFERNT to emptyList()
    ),
    hinzugefuegteElemente = emptyList()
)

fun alleTestKategorien(): List<Kategorie> = listOf(testKategorie1(), testKategorie2())

class KategorieTest {
    @Test
    fun `Test eingabeToKategorie() - Konvertierung von Kategorienname und zugehoerigen Karten zu Kategorie`() {
        val kategorie1 = eingabeToKategorie(1, Sprachen.EN, TEST_KATEGORIE_1_EINGABE, listOf(testKarte1()))
        kategorie1.hinzugefuegteElemente = listOf(testKarte2())
        kategorie1.originaleElemente[DAVON_ENTFERNT] = listOf(testKarte1())
        assertEquals(testKategorie1().id, kategorie1.id)
        assertEquals(testKategorie1().localizations, kategorie1.localizations)
        assertEquals(testKategorie1().originaleElemente, kategorie1.originaleElemente)
        assertEquals(testKategorie1().hinzugefuegteElemente, kategorie1.hinzugefuegteElemente)
        assertEquals(testKategorie1(), kategorie1)

        val kategorie2 = eingabeToKategorie(
            2, Sprachen.DE, TEST_KATEGORIE_2_EINGABE, listOf(testKarte1(), testKarte2())
        )
        kategorie2.hinzugefuegteElemente = emptyList()
        assertEquals(testKategorie2().id, kategorie2.id)
        assertEquals(testKategorie2().localizations, kategorie2.localizations)
        assertEquals(testKategorie2().originaleElemente, kategorie2.originaleElemente)
        assertEquals(testKategorie2().hinzugefuegteElemente, kategorie2.hinzugefuegteElemente)
        assertEquals(testKategorie2(), kategorie2)
    }

    @Test
    fun `Test kategorieToYaml() - Konvertierung von Kategorie zu YAML`() {
        assertEquals(testKategorie1Yaml(), testKategorie1().toYaml())
        assertEquals(testKategorie2Yaml(), testKategorie2().toYaml())
    }

    @Test
    fun `Test yamlToKategorie() - Konvertierung von YAML zu Kategorie`() {
        val kategorie1 = yamlToKategorien(testKategorie1Yaml(), alleTestKarten())[0]
        assertEquals(testKategorie1(), kategorie1)

        val kategorie2 = yamlToKategorien(testKategorie2Yaml(), alleTestKarten())[0]
        assertEquals(testKategorie2(), kategorie2)
    }

    @Test
    fun `Test Rundreise yamlToKategorieToYaml - Konvertierung von YAML zu Kategorie und wieder zurueck`() {
        assertEquals(testKategorie1Yaml(), yamlToKategorien(testKategorie1Yaml(), alleTestKarten())[0].toYaml())
        assertEquals(testKategorie2Yaml(), yamlToKategorien(testKategorie2Yaml(), alleTestKarten())[0].toYaml())
    }

    @Test
    fun `Test getAlleKarten() - Ausgabe aller Karten einer Kategorie`() {
        assertEquals(2, testKategorie1().getAlleKarten().size)
        assertEquals(2, testKategorie2().getAlleKarten().size)
    }

    @Test
    fun `Test getAlleAktuellenKarten() - Ausgabe aller aktuellen Karten einer Kategorie`() {
        assertEquals(1, testKategorie1().getAlleAktuellenKarten().size)
        assertEquals(2, testKategorie2().getAlleAktuellenKarten().size)
    }
}
