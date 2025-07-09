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

}