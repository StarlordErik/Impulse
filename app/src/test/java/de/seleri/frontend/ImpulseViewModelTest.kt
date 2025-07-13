package de.seleri.frontend

import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Kategorie
import de.seleri.spielelemente.SammlungAnSpielelementen
import de.seleri.spielelemente.Spiel
import de.seleri.spielelemente.Sprachen
import de.seleri.spielelemente.dummySpiel
import de.seleri.spielelemente.finde
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ImpulseViewModelTest {

    private lateinit var datenbanksystem: Datenbanksystem
    private lateinit var viewModel: ImpulseViewModel

    @Before
    fun setup() {
        datenbanksystem = mockk()

        every { datenbanksystem.spiele } returns mutableSetOf(
            dummySpiel()
        )
        every { datenbanksystem.getRandomKartentext(any()) } returns "Testkarte"

        viewModel = ImpulseViewModel(datenbanksystem)
    }

    @Test
    fun `getSpiel returns correct Spiel`() {
        val id = 1
        every { datenbanksystem.spiele.finde(id) } returns Spiel(id)

        val result = viewModel.getSpiel(id)
        assertEquals(id, result.id)
    }

    @Test
    fun `getName returns OG localization`() {
        val sammlung = SammlungAnSpielelementen<Kategorie>().apply {
            localizations = mapOf(Sprachen.OG to "Originalname")
        }

        val result = viewModel.getName(sammlung)
        assertEquals("Originalname", result)
    }

    @Test
    fun `getRandomKartentext returns expected value`() {
        val kategorie = mockk<Kategorie>()
        val result = viewModel.getRandomKartentext(kategorie)
        assertEquals("Testkarte", result)
    }
}
