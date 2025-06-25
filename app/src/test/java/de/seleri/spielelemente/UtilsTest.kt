package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import org.yaml.snakeyaml.Yaml

/**
 * Einlesen von YAML-Dateien mit SnakeYAML in der Testumgebung.
 *
 * @param pfad Der Pfad zur YAML-Datei, relativ zum Klassenpfad.
 * @return Eine Map mit den eingelesenen Daten.
 */
fun ladeYamlDaten(pfad: String): Map<String, Any> {
    val inputStream = object{}.javaClass.classLoader?.getResourceAsStream(pfad)
        ?: throw IllegalArgumentException("Datei nicht gefunden: $pfad")
    return Yaml().load(inputStream)
}

/** kürzere Schreibeweise von attributToYamlZeile() */
fun atyz(anzahlEinrueckungen: Int, attributsname: String, attributswert: Any?) =
    attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

fun testAttribute() : Map<String, Any?>{
    val collectionAnSpielelementen = setOf<LokalisierbaresSpielelement>(
 // TODO dummy Objekte (in LokalisierbaresSpielementTest.kt erstellen)
    ) as Collection<LokalisierbaresSpielelement>

    // keys sind die Klassentypen der Attributstypen
    return mapOf(
        "String" to "String",
        "Collection<Int>" to listOf(1, 2, 3) as Collection<Int>,
        "Collection<LokalisierbaresSpielelement>" to collectionAnSpielelementen,
        "null" to null
    )
}

class UtilsTest {

    @Test
    fun `ladeYamlDaten() ungueltiger Dateipfad wirft Exception`() {
        assertThrows(IllegalArgumentException::class.java)  {
            ladeYamlDaten("nicht_existierend.yml")
        }
    }

    @Test
    fun `ladeYamlDaten() Test-Dateien problemlos auslesen`() {
        val daten = ladeYamlDaten("UtilsTest.yml")
        assertTrue(daten.isNotEmpty())
        assertTrue(daten["Test"] as Boolean)
    }
}