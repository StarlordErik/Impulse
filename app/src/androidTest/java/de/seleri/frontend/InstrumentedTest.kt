package de.seleri.frontend

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import de.seleri.backend.DATENBANK_DATEIFORMAT
import de.seleri.backend.DATENBANK_NAME
import de.seleri.backend.Datenbanksystem
import de.seleri.backend.finde
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import java.io.File


@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

  @Test
  fun rawDatenbankKorrektAusgelesen() {
    val context = InstrumentationRegistry.getInstrumentation().targetContext
    val rawDatenbank = context.resources.openRawResource(R.raw.datenbank)

    val datenbank = File.createTempFile(DATENBANK_NAME, DATENBANK_DATEIFORMAT)
    rawDatenbank.use { input ->
      datenbank.outputStream().use { output ->
        input.copyTo(output)
      }
    }

    val dbs = Datenbanksystem(datenbank)

    assertNotNull(dbs.spiele.finde("We're not really strangers"))
    assertNotNull(dbs.kategorien.finde("Level 1: Perception"))
    assertNotNull(dbs.karten.finde("Do I look kind? Explain."))
  }

  @Test
  fun datenbanksystemKorrektGeneriert() {
    val context = InstrumentationRegistry.getInstrumentation().targetContext

    val dbs = Datenbanksystem.generieren(context)

    assertNotNull(dbs.spiele.finde("We're not really strangers"))
    assertNotNull(dbs.kategorien.finde("Level 1: Perception"))
    assertNotNull(dbs.karten.finde("Do I look kind? Explain."))
  }

}
