package de.seleri.frontend

/*
class ImpulseViewModelTest {

    private lateinit var datenbanksystem: Datenbanksystem
    private lateinit var viewModel: ImpulseViewModel

    @Before
    fun setup() {
        datenbanksystem = mockk()

        every { datenbanksystem.spiele } returns alleDummySpiele().toMutableSet()

        every { datenbanksystem.getRandomKartentext(any()) } returns "Testkarte"

        viewModel = ImpulseViewModel(Datenbanksystem(tmpDatenbankDatei()))
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


 */