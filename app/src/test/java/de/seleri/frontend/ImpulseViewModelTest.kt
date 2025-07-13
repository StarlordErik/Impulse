package de.seleri.frontend

import de.seleri.spielelemente.dummyDatenbanksystem
import de.seleri.spielelemente.dummySpiel1
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

fun dummyImpulseViewModel() = ImpulseViewModel(dummyDatenbanksystem())

class ImpulseViewModelTest {

    @Test
    fun `dummyImpulseViewModel() ViewModel initialisieren`() {
        val viewModel = dummyImpulseViewModel()

        assertTrue(viewModel.spiele.isNotEmpty())
    }

    @Test
    fun `getSpiel() Spiel wird gefunden`() {
        val viewModel = dummyImpulseViewModel()

        val actual = viewModel.getSpiel(1)

        val expected = dummySpiel1()
        assertEquals(expected, actual)
    }
}


