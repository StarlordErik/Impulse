package de.seleri.spielelemente

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
    val inputStream = object {}.javaClass.classLoader?.getResourceAsStream(pfad)
        ?: throw IllegalArgumentException("Datei nicht gefunden: $pfad")
    return Yaml().load(inputStream)
}

fun <T> getDummyDaten(
    dateiname: String,
    dummyElementart: String,
    factory: (Map<String, Any>) -> Set<T>
): Set<T> {
    val dummyDaten = ladeYamlDaten(dateiname)
    assertTrue(dummyDaten.isNotEmpty()) // Test, ob die Yaml-Datei nicht leer ist

    assertTrue(dummyElementart in dummyDaten) // Test, ob die gesuchte Daten auffindbar sind

    @Suppress("UNCHECKED_CAST")
    val daten = dummyDaten[dummyElementart] as Map<String, Any>

    return factory(daten)
}

class UtilsForTests {

    @Test
    fun `ladeYamlDaten() Exception bei einem ungueltigen Dateipfad`() {
        assertThrows(IllegalArgumentException::class.java) {
            ladeYamlDaten("nicht_existierend.yml")
        }
    }

    @Test
    fun `ladeYamlDaten() Test-Dateien problemlos auslesen`() {
        val daten = ladeYamlDaten("Utils.yml")
        assertTrue(daten.isNotEmpty())
        assertTrue(daten["Test"] as Boolean)
    }
}
