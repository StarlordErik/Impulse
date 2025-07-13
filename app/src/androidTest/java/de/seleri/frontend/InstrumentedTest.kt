package de.seleri.frontend

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class InstrumentedTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("de.seleri.frontend", appContext.packageName)
    }

    /*
    vor dem Composable-Transfer:

    @Test
    fun datenbanksystem_ist_generiert() {
        val dbs = (ApplicationProvider.getApplicationContext<Context>() as Impulse).dbs

        assertNotNull(dbs)

        assert(dbs.karten.isNotEmpty())
        assertNotNull(dbs.karten.finde("Do I look kind? Explain."))

        assert(dbs.kategorien.isNotEmpty())
        assertNotNull(dbs.kategorien.finde("Level 1: Perception"))

        assert(dbs.spiele.isNotEmpty())
        assertNotNull(dbs.spiele.finde("We're not really strangers"))
    }

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun activity_kann_auf_das_datenbanksystem_zugreifen() {
        activityRule.scenario.onActivity { activity ->
            val db = (activity.application as Impulse).dbs
            assertNotNull(db)
        }
    }
    */
}
