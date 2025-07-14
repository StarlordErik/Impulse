package de.seleri.frontend.screens

import org.junit.Assert.assertEquals
import org.junit.Test

class ScreenTest {

    @Test
    fun `SpielScreen_mitDerID() erzeugt korrekten Pfad fuer SpieleScreen`() {
        val id = 42
        val screen = Screens.Spiel

        val actual = screen.mitDerID(id)

        val expected = screen.route + "/42"
        assertEquals(expected, actual)
    }
}
