package de.seleri.frontend

import de.seleri.backend.dummyDatenbanksystem
import de.seleri.backend.dummySpiel1
import de.seleri.backend.finde
import de.seleri.viewModel.ImpulseViewModel
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

  @Test
  fun `getName() ausgegebener Name entspricht einer Lokalisierung`() {
    val viewModel = dummyImpulseViewModel()
    val spiel = viewModel.getSpiel(1)

    val actual = viewModel.getName(spiel)

    val expectedScope = dummySpiel1().localizations.values
    assertTrue(expectedScope.contains(actual))
  }

  @Test
  fun `getRandomKartentext() ausgegebener Kartentext kommt aus der Sammlung`() {
    val viewModel = dummyImpulseViewModel()
    val kategorie = viewModel.getSpiel(5).getAktuelleKategorien().finde(5)

    val expectedScope = kategorie.getUngeseheneKarten().flatMap {it.localizations.values}

    val actual = viewModel.getRandomKartentext(kategorie)
    assertTrue(expectedScope.contains(actual))
  }
}



