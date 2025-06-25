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

class UtilsTest {

    @Test
    fun `ladeYamlDaten() Test-Dateien problemlos auslesen`() {
        assertThrows(IllegalArgumentException::class.java)  {
            ladeYamlDaten("nicht_existierend.yml")
        }

        val daten = ladeYamlDaten("UtilsTest.yml")
        assertTrue(daten.isNotEmpty())
        assertTrue(daten["Test"] as Boolean)
    }
}