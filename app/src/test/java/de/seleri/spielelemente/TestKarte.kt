package de.seleri.spielelemente
/*
import org.junit.Assert.*
import org.junit.Test

class TestKarte {

    private val englischeTestKartentexte = listOf(
        "Do I look kind? Explain.",
        "What is my body language telling you right now?",
        "!\"§\$%&/()=?\n\t ,.-;:_#+'*\\{[]}´`~^°@€"
    )

    private fun createTestCards(): List<Karte> {
        return englischeTestKartentexte.mapIndexed { index, text ->
            val localized: MutableMap<Sprachen, String> = mutableMapOf()
            for (sprache in Sprachen.entries) {
                localized[sprache] = if (sprache == Sprachen.EN) text else ""
            }
            Karte(
                id = index + 1,
                localizedKartentexte = localized
            )
        }
    }

    @Test
    fun `objToYaml produziert korrekt formatierten YAML-Block`() {
        val card = Karte(
            id = 1,
            localizedKartentexte = mapOf(
                Sprachen.EN to "Hello: World",
                Sprachen.DE to "Hallo: Welt"
            )
        )

        val expected =
"""  - id: 1
    text:
      en: "Hello: World"
      de: "Hallo: Welt"
"""

        assertEquals(expected, card.objToYaml())
    }

    @Test
    fun `yamlToObj parst YAML korrekt zurueck in Karte`() {
        val yamlInput = """
            - id: 42
              text:
                en: "Test-Text"
                de: "Prüf-Text"
        """.trimIndent()

        val card = Karte().yamlToObj(yamlInput)
        assertEquals(42, card.id)
        assertEquals("Test-Text", card.localizedKartentexte[Sprachen.EN])
        assertEquals("Prüf-Text", card.localizedKartentexte[Sprachen.DE])
    }

    @Test
    fun `Roundtrip objToYaml und yamlToObj liefert identisches Ergebnis`() {
        val cards = createTestCards()
        val yamls = cards.map { it.objToYaml() }
        val reparsed = yamls.map {
            Karte().yamlToObj(it)
        }
        val yamls2 = reparsed.map { it.objToYaml() }

        // Vergleiche jedes YAML-Fragment
        for (i in yamls.indices) {
            assertEquals(
                "Roundtrip bei Karte ${i + 1} stimmt nicht",
                yamls[i],
                yamls2[i]
            )
        }
    }
}
 */
