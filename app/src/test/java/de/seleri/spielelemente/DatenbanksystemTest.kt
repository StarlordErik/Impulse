package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.yaml.snakeyaml.Yaml
import java.io.File

class DatenbanksystemTest {

    fun tmpDatenbankDatei() : File {
        val builder = StringBuilder()

        builder.append(attributToYamlZeile(0, KARTEN, null))
        for (karte in alleDummyKarten().sorted()) {
            builder.append(karte.toYaml())
        }

        builder.append("\n")

        builder.append(attributToYamlZeile(0, KATEGORIEN, null))
        for (kategorie in alleDummyKategorien().sorted()) {
            builder.append(kategorie.toYaml())
        }

        builder.append("\n")

        builder.append(attributToYamlZeile(0, SPIELE, null))
        for (spiel in alleDummySpiele().sorted()) {
            builder.append(spiel.toYaml())
        }

        val datenbank = File.createTempFile(DATENBANK_NAME, DATENBANK_DATEIFORMAT)
        datenbank.writeText(builder.toString())
        return datenbank
    }

    @Test
    fun `tmpDatenbankDatei() Testdatenbank aus den Dummys erzeugen`() {
        val datenbank = tmpDatenbankDatei()

        val daten = Yaml().load<Map<String, Any>>(datenbank.inputStream())

        assertTrue(daten.isNotEmpty())
        assertTrue(daten.containsKey(KARTEN))
        assertTrue(daten.containsKey(KATEGORIEN))
        assertTrue(daten.containsKey(SPIELE))
    }

    @Test
    fun `init() Datenbanksystem initialisieren`(){
        val datenbank = tmpDatenbankDatei()

        val dbs = Datenbanksystem(datenbank)

        assertTrue(dbs.karten.isNotEmpty())
        assertTrue(dbs.kategorien.isNotEmpty())
        assertTrue(dbs.spiele.isNotEmpty())
    }

    @Test
    fun `aktualisieren() Datenbank aktualisieren`(){
        val datenbank = tmpDatenbankDatei()
        val dbs = Datenbanksystem(datenbank)

        val expected = datenbank.readText()

        dbs.aktualisieren()
        val actual = datenbank.readText()

        assertEquals(expected, actual)
    }

    @Test
    fun `getRandomKartentext() zufaelliger Kartentext einer Kategorie ausgeben`() {
        val dbs = Datenbanksystem(tmpDatenbankDatei())
        val dummyKategorie = dbs.kategorien.finde("dummyKategorie5")!!
        val kartentexte1 = dummyKategorie.getUngeseheneKarten().map { it.localizations[Sprachen.OG]!! }.toMutableSet()
        val kartentexte2 = kartentexte1.toList()

        // Test 1: alle ungesehenen Karten werden ohne Duplikate ausgegeben
        kartentexte2.forEach { _ -> // wir iterieren über eine Kopie, die in der Schleife nicht verändert wird
            val randomKartentext = dbs.getRandomKartentext(dummyKategorie)
            assertTrue(kartentexte1.contains(randomKartentext))
            kartentexte1.remove(randomKartentext)
        }
        assertEquals(0, kartentexte1.size)

        // Test 2: nachdem alle Karten bereits gesehen wurden, kann jede wieder gesehen werden
        val randomKartentext = dbs.getRandomKartentext(dummyKategorie)
        assertTrue(kartentexte2.contains(randomKartentext))
    }

    @Test
    fun `getRandomKartentext() zufaelliger Kartentext eines Spiels ausgeben`() {
        val dbs = Datenbanksystem(tmpDatenbankDatei())
        val dummySpiel = dbs.spiele.finde("dummySpiel5")!!
        val kartentexte1 = dummySpiel.getUngeseheneKarten().map { it.localizations[Sprachen.OG]!! }.toMutableSet()
        val kartentexte2 = kartentexte1.toList()

        // Test 1: alle ungesehenen Karten werden ohne Duplikate ausgegeben
        kartentexte2.forEach { _ -> // wir iterieren über eine Kopie, die in der Schleife nicht verändert wird
            val randomKartentext = dbs.getRandomKartentext(dummySpiel)
            assertTrue(kartentexte1.contains(randomKartentext))
            kartentexte1.remove(randomKartentext)
        }
        assertEquals(0, kartentexte1.size)

        // Test 2: nachdem alle Karten bereits gesehen wurden, kann jede wieder gesehen werden
        val randomKartentext = dbs.getRandomKartentext(dummySpiel)
        assertTrue(kartentexte2.contains(randomKartentext))
    }

}