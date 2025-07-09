package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.yaml.snakeyaml.Yaml
import java.io.File

class DatenbanksystemTest {

    fun tmpDatenbankDatei(): File {
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
    fun `init() Datenbanksystem initialisieren`() {
        val datenbank = tmpDatenbankDatei()

        val dbs = Datenbanksystem(datenbank)

        assertTrue(dbs.karten.isNotEmpty())
        assertTrue(dbs.kategorien.isNotEmpty())
        assertTrue(dbs.spiele.isNotEmpty())
    }

    @Test
    fun `aktualisieren() Datenbank aktualisieren`() {
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
        val kartentexte1 =
            dummyKategorie.getUngeseheneKarten().map { it.localizations[Sprachen.OG]!! }
                .toMutableSet()
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
        val kartentexte1 =
            dummySpiel.getUngeseheneKarten().map { it.localizations[Sprachen.OG]!! }.toMutableSet()
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

    @Test
    fun `neueKarten() eine Menge neue Karten erstellen`() {
        val dbs = Datenbanksystem(tmpDatenbankDatei())
        val neueKartentexte = setOf(
            "Dummy 1", "neue Karte", "neue Karte"
        )

        val eingegebeneKarten = dbs.neueKarten(neueKartentexte, Sprachen.OG)

        // Test 1: die neuen Karten sind in der Datenbank
        assertTrue(dbs.karten.containsAll(eingegebeneKarten))

        // Test 2: doppelte Karten sind der Datenbank nicht hinzugefügt worden
        assertEquals(6, dbs.karten.size)

        // Test 3: wurden die neue ID richtig gewählt?
        val actual = eingegebeneKarten.map { it.id }.sorted()
        val expected = listOf(1, 6)
        assertEquals(expected, actual)
    }

    @Test
    fun `neueKategorie() eine neue Kategorie erstellen`() {
        val dbs = Datenbanksystem(tmpDatenbankDatei())
        val neueKarten = alleDummyKarten()

        // Test 1: die Kategorie ist in der Datenbank
        val neueKategorie = dbs.neueKategorie("neue Kategorie", neueKarten, Sprachen.OG)
        assertTrue(dbs.kategorien.contains(neueKategorie))

        // Test 2: die Kategorie hat die korrekte ID
        val actual = neueKategorie.id
        val expected = 6
        assertEquals(expected, actual)
    }

    @Test
    fun `neueKategorie() eine bekannte Kategorie hinzufuegen`() {
        val dbs = Datenbanksystem(tmpDatenbankDatei())
        val bekannteKarten = listOf(dummyKarte1())

        // Test 1: die Kategorie ist in der Datenbank
        val bekannteKategorieMitBekanntenKarten =
            dbs.neueKategorie("dummyKategorie1", bekannteKarten, Sprachen.OG)
        assertTrue(dbs.kategorien.contains(bekannteKategorieMitBekanntenKarten))

        // Test 2: doppelte Kategorien sind der Datenbank nicht hinzugefügt worden
        assertEquals(5, dbs.kategorien.size)
    }

    @Test
    fun `neueKategorie() eine bekannte Kategorie neu beschreiben`() {
        val dbs = Datenbanksystem(tmpDatenbankDatei())
        val neueKarten = alleDummyKarten()

        // Test 1: die Kategorie ist in der Datenbank
        val bekannteKategorieMitNeuenKarten =
            dbs.neueKategorie("dummyKategorie1", neueKarten, Sprachen.OG)
        assertTrue(dbs.kategorien.contains(bekannteKategorieMitNeuenKarten))

        // Test 2: die doppelte Kategorie ist der Datenbank nicht hinzugefügt worden
        assertEquals(5, dbs.kategorien.size)

        // Test 3: die bekannte Kategorie hat nun alle neuen Karten gespeichert
        assertTrue(bekannteKategorieMitNeuenKarten.originaleElemente[IDS]!!.containsAll(neueKarten))
    }

    @Test
    fun `neuesSpiel() ein neues Spiel erstellen`() {
        val dbs = Datenbanksystem(tmpDatenbankDatei())
        val neueKategorien = alleDummyKategorien()

        // Test 1: das Spiel ist in der Datenbank
        val neuesSpiel = dbs.neuesSpiel("neues Spiel", neueKategorien, Sprachen.OG)
        assertTrue(dbs.spiele.contains(neuesSpiel))

        // Test 2: das Spiel hat die korrekte ID
        val actual = neuesSpiel.id
        val expected = 6
        assertEquals(expected, actual)
    }

    @Test
    fun `karteLoeschen() eine Karte loeschen`() {
        val dbs = Datenbanksystem(tmpDatenbankDatei())
        val zuLoeschendeKarte = dummyKarte1()

        dbs.karteLoeschen(zuLoeschendeKarte)

        // Test 1: die Karte ist als gelöscht markiert worden
        assertTrue(zuLoeschendeKarte.geloescht)

        // Test 2: die Karte ist in keinen aktuellen Karten einer Kategorie mehr enthalten
        dbs.kategorien.forEach {
            assertTrue(!it.getAktuelleKarten().contains(zuLoeschendeKarte))
        }
    }

    @Test
    fun `kartenZuKategorieHinzufuegen() Karten per Objekt zu einer Kategorie hinzufuegen`() {
        val dbs = Datenbanksystem(tmpDatenbankDatei())
        val neueKarten = alleDummyKarten()
        val zuAenderndeKategorie = dbs.kategorien.finde("dummyKategorie1")!!

        dbs.kartenZuKategorieHinzufuegen(zuAenderndeKategorie, neueKarten)

        assertTrue(zuAenderndeKategorie.getKarten().containsAll(alleDummyKarten()))
    }

    @Test
    fun `kategorienZuSpielHinzufuegen() Kategorien per ID zu einem Spiel hinzufuegen`() {
        val dbs = Datenbanksystem(tmpDatenbankDatei())
        val neueKategorien = listOf(1,2,3,4,5)
        val zuAenderndesSpiel = dbs.spiele.finde("dummySpiel1")!!

        dbs.kategorienZuSpielHinzufuegen(zuAenderndesSpiel, neueKategorien)

        assertTrue(zuAenderndesSpiel.getKategorien().containsAll(alleDummyKategorien()))
    }

}
