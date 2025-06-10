package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class DatenbanksystemTest {

    @Test
    fun `test datenbanksystemGenerieren ohne Context`() {
        // Temporäre Kopie der Originaldatei anlegen
        val inputStream = this::class.java.classLoader!!.getResourceAsStream(DATENBANK_DATEI)
        val tempFile = File.createTempFile(DATENBANK_NAME, DATENBANK_DATEIFORMAT)
        inputStream.use { input ->
            tempFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        val datenbanksystem = Datenbanksystem(tempFile)

        assert(datenbanksystem.karten.isNotEmpty())
        assertEquals(2, datenbanksystem.karten.size)

        assert(datenbanksystem.kategorien.isNotEmpty())
        assertEquals(2, datenbanksystem.kategorien.size)

        assert(datenbanksystem.spiele.isNotEmpty())
        assertEquals(2, datenbanksystem.spiele.size)

        tempFile.delete()
    }
}
