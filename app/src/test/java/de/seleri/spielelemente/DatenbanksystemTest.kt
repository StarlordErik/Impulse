package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

import java.io.File

class DatenbanksystemTest {

    fun tmpKopieAnlegen(): File {
        val inputStream = this::class.java.classLoader!!.getResourceAsStream(DATENBANK_DATEI)

        // erstellt jedes Mal eine einzigartige Datei
        val tmpKopie = File.createTempFile(DATENBANK_NAME, DATENBANK_DATEIFORMAT)

        inputStream.use { input ->
            tmpKopie.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return tmpKopie
    }

    @Test
    fun `test gereriereDatenbanksystem() - Objekt erstellen und laden`() {
        val tmpKopie = tmpKopieAnlegen()
        val dbs = Datenbanksystem(tmpKopieAnlegen())

        assert(dbs.karten.isNotEmpty())
        assertEquals(2, dbs.karten.size)

        assert(dbs.kategorien.isNotEmpty())
        assertEquals(2, dbs.kategorien.size)

        assert(dbs.spiele.isNotEmpty())
        assertEquals(2, dbs.spiele.size)

        tmpKopie.delete()
    }

    @Test
    fun `test neueKarten() - Karten hinzufuegen`() {
        val tmpKopie = tmpKopieAnlegen()
        val dbs = Datenbanksystem(tmpKopieAnlegen())

        assertEquals(listOf(1, 2), dbs.karten.map { it.id })

        val neueKarten = dbs.neueKarten(listOf("neue Karte 1", "neue Karte 2"), Sprachen.DE)
        val neueKartenIDs = neueKarten.map { it.id }

        assertEquals(listOf(3, 4), neueKartenIDs)

        assertEquals(2, neueKarten.size)
        assertEquals(4, dbs.karten.size)

        assertEquals(2, neueKarten.toSet().size)
        assertEquals(4, dbs.karten.toSet().size)

        assertEquals(2, neueKartenIDs.toSet().size)
        assertEquals(4, dbs.karten.map{it.id}.toSet().size)

        tmpKopie.delete()
    }

    @Test
    fun `test neueKategorie() - Kategorie hinzufuegen`() {
        val tmpKopie = tmpKopieAnlegen()
        val dbs = Datenbanksystem(tmpKopieAnlegen())

        assertEquals(listOf(1, 2), dbs.kategorien.map { it.id })

        val neueKategorie = dbs.neueKategorie("neue Kategorie", dbs.karten, Sprachen.DE)

        assertEquals(3, neueKategorie.id)
        assertEquals(3, dbs.kategorien.size)
        assertEquals(3, dbs.kategorien.toSet().size)
        assertEquals(3, dbs.kategorien.map { it.id }.toSet().size)

        tmpKopie.delete()
    }

    @Test
    fun `test neuesSpiel() - Spiel hinzufuegen`() {
        val tmpKopie = tmpKopieAnlegen()
        val dbs = Datenbanksystem(tmpKopieAnlegen())

        assertEquals(listOf(1, 2), dbs.spiele.map { it.id })

        val neuesSpiel = dbs.neuesSpiel("neues Spiel", dbs.kategorien, Sprachen.DE)

        assertEquals(3, neuesSpiel.id)
        assertEquals(3, dbs.spiele.size)
        assertEquals(3, dbs.spiele.toSet().size)
        assertEquals(3, dbs.spiele.map { it.id }.toSet().size)

        tmpKopie.delete()
    }

    @Test
    fun `test hinzufuegen() - Karten direkt zu Kategorie hinzufuegen`() {
        val tmpKopie = tmpKopieAnlegen()
        val dbs = Datenbanksystem(tmpKopieAnlegen())

        val neueKarten =
            dbs.neueKarten(listOf("neue Karte 1", "neue Karte 2"), Sprachen.DE)
        val neueKategorie =
            dbs.neueKategorie("neue Kategorie", emptyList(), Sprachen.DE)

        assertEquals(0, findeElement(neueKategorie.id, dbs.kategorien).getAlleKarten().size)

        // Wir finden die neue Kategorie im DBS und prüfen dann, ob sie die neuen Karten hinzugefügt hat
        dbs.hinzufuegen(neueKategorie, neueKarten)
        assertEquals(2, findeElement(neueKategorie.id, dbs.kategorien).getAlleKarten().size)

        // Wenn wir dieselben Karten zur derselben Kategorie nochmal hinzufügen, sollte nichts passieren
        dbs.hinzufuegen(neueKategorie, neueKarten)
        assertEquals(2, findeElement(neueKategorie.id, dbs.kategorien).getAlleKarten().size)

        tmpKopie.delete()
    }

    @Test
    fun `test hinzufuegen() - Kategorien direkt zu Spiel hinzufuegen`() {
        val tmpKopie = tmpKopieAnlegen()
        val dbs = Datenbanksystem(tmpKopieAnlegen())

        val neueKategorie1 = dbs.neueKategorie("neue Kategorie 1", emptyList(), Sprachen.DE)
        val neueKategorie2 = dbs.neueKategorie("neue Kategorie 2", emptyList(), Sprachen.DE)
        val neueKategorie3 = dbs.neueKategorie("neue Kategorie 3", emptyList(), Sprachen.DE)

        val neueKategorien = listOf(neueKategorie1, neueKategorie2, neueKategorie3)
        val neuesSpiel = dbs.neuesSpiel("neues Spiel", emptyList(), Sprachen.DE)

        assertEquals(0, findeElement(neuesSpiel.id, dbs.spiele).getAlleKategorien().size)

        // Wir finden das neues Spiel im DBS und prüfen dann, ob es die neuen Kategorien hinzugefügt hat
        dbs.hinzufuegen(neuesSpiel, neueKategorien)
        assertEquals(3, findeElement(neuesSpiel.id, dbs.spiele).getAlleKategorien().size)

        // Wenn wir dieselben Kategorien zum selben Spiel nochmal hinzufügen, sollte nichts passieren
        dbs.hinzufuegen(neuesSpiel, neueKategorien)
        assertEquals(3, findeElement(neuesSpiel.id, dbs.spiele).getAlleKategorien().size)

        tmpKopie.delete()
    }

    @Test
    fun `test hinzufuegen() - Karten per ID zu Kategorie hinzufuegen`() {
        val tmpKopie = tmpKopieAnlegen()
        val dbs = Datenbanksystem(tmpKopieAnlegen())

        val neueKarten =
            dbs.neueKarten(listOf("neue Karte 1", "neue Karte 2"), Sprachen.DE)
        val neueKartenIDs = neueKarten.map { it.id }

        val neueKategorie =
            dbs.neueKategorie("neue Kategorie", emptyList(), Sprachen.DE)

        assertEquals(0, findeElement(neueKategorie.id, dbs.kategorien).getAlleKarten().size)

        // Wir finden die neue Kategorie im DBS und prüfen dann, ob sie die neuen Karten hinzugefügt hat
        dbs.hinzufuegen(neueKategorie, neueKartenIDs)
        assertEquals(2, findeElement(neueKategorie.id, dbs.kategorien).getAlleKarten().size)

        // Wenn wir dieselben Karten zur derselben Kategorie nochmal hinzufügen, sollte nichts passieren
        dbs.hinzufuegen(neueKategorie, neueKartenIDs)
        assertEquals(2, findeElement(neueKategorie.id, dbs.kategorien).getAlleKarten().size)

        tmpKopie.delete()
    }

    @Test
    fun `test hinzufuegen() - Kategorien per ID zu Spiel hinzufuegen`() {
        val tmpKopie = tmpKopieAnlegen()
        val dbs = Datenbanksystem(tmpKopieAnlegen())

        val neueKategorie1 = dbs.neueKategorie("neue Kategorie 1", emptyList(), Sprachen.DE)
        val neueKategorie2 = dbs.neueKategorie("neue Kategorie 2", emptyList(), Sprachen.DE)
        val neueKategorie3 = dbs.neueKategorie("neue Kategorie 3", emptyList(), Sprachen.DE)

        val neueKategorien = listOf(neueKategorie1, neueKategorie2, neueKategorie3)
        val neueKategorienIDs = neueKategorien.map { it.id }

        val neuesSpiel = dbs.neuesSpiel("neues Spiel", emptyList(), Sprachen.DE)

        assertEquals(0, findeElement(neuesSpiel.id, dbs.spiele).getAlleKategorien().size)

        // Wir finden das neues Spiel im DBS und prüfen dann, ob es die neuen Kategorien hinzugefügt hat
        dbs.hinzufuegen(neuesSpiel, neueKategorienIDs)
        assertEquals(3, findeElement(neuesSpiel.id, dbs.spiele).getAlleKategorien().size)

        // Wenn wir dieselben Kategorien zum selben Spiel nochmal hinzufügen, sollte nichts passieren
        dbs.hinzufuegen(neuesSpiel, neueKategorienIDs)
        assertEquals(3, findeElement(neuesSpiel.id, dbs.spiele).getAlleKategorien().size)

        tmpKopie.delete()
    }

}
