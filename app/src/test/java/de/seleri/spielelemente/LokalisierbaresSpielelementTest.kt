package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** Dummy-Implementierung, da Klasse abstrakt ist und manche Methoden protected */
class DummyLokalisierbaresSpielelement(
    override val id: Int, override val localizations: MutableMap<Sprachen, String?>
): LokalisierbaresSpielelement(id, localizations) {
    fun dummyLocalizationsToYaml(bezeichnung: String) = super.localizationsToYaml(bezeichnung)

    companion object {
        fun fromEingabe(id: Int, sprache: Sprachen, bezeichnung: String) =
            LokalisierbaresSpielelement.fromEingabe(id, sprache, bezeichnung)

        fun fromYaml(yamlDatensatz: Map<String, Any>) =
            LokalisierbaresSpielelement.fromYaml(yamlDatensatz)

        fun fromYamlListe(
            element: String,
            yamlDaten: Map<String, Any>,
            converter: (Map<String, Any>) -> Collection<DummyLokalisierbaresSpielelement>
        ) = LokalisierbaresSpielelement.fromYamlListe(element, yamlDaten, converter)
    }
}

const val DUMMY_ID = 42
const val DUMMY_OG = "Original"
const val DUMMY_DE = "Deutsch"
fun dummyLocalizations() =
    mutableMapOf(Sprachen.OG to DUMMY_OG, Sprachen.DE to DUMMY_DE, Sprachen.EN to null)

fun dummyLokalisierbaresSpielelement() =
    DummyLokalisierbaresSpielelement(DUMMY_ID, dummyLocalizations())

class LokalisierbaresSpielelementTest {

    /*
    ------------------------------------------------------------------------------------------------
    WICHTIG: Wie ist jeder Test aufgebaut?

    1: benötigte Variablen & Konstanten instantiieren

        (1.5: ggf. wird hier expected instanziiert, falls sich nichts ändern soll)

    2: actual = Ausführung des zu testenden Codes

        (2.5: bei AssertTrue wird 2 & 3 ersetzt durch condition = Ausführung des zu testenden Codes)

    3: expected instanzieeren & Test ausführen
    ------------------------------------------------------------------------------------------------
    */

    @Test
    fun `toYaml() wandelt das Objekt korrekt in Yaml-Text um`() {
        val dummy = dummyLokalisierbaresSpielelement()

        val actual = dummy.toYaml()

        val expected = "  - $ID: $DUMMY_ID\n"
        assertEquals(expected, actual)
    }

    @Test
    fun `localizationsToYaml() wandelt die Localization-Map korrekt in Yaml-Text um`() {
        val dummy = dummyLokalisierbaresSpielelement()
        val bezeichnung = "Bezeichnung"

        val actual = dummy.dummyLocalizationsToYaml(bezeichnung)

        val expected = """
            |    $bezeichnung:
            |      ${Sprachen.OG}: "$DUMMY_OG"
            |      ${Sprachen.DE}: "$DUMMY_DE"
            |      ${Sprachen.EN}:
            |
        """.trimMargin()
        assertEquals(expected, actual)
    }

    @Test
    fun `setUebersetzung() aendert die Uebersetzung, WENN sprache != Sprachen_OG`() {
        val dummy = dummyLokalisierbaresSpielelement()
        val sprache = Sprachen.EN
        val bezeichnung = "Temporär"

        dummy.setUebersetzung(sprache, bezeichnung)
        val actual = dummy.localizations[sprache]

        val expected = bezeichnung
        assertEquals(expected, actual)
    }

    @Test
    fun `setUebersetzung() aendert die Uebersetzung NICHT, WENN sprache == Sprachen_OG`() {
        val dummy = dummyLokalisierbaresSpielelement()
        val sprache = Sprachen.OG
        val bezeichnung = "Temporär"

        val expected = dummy.localizations[sprache]

        dummy.setUebersetzung(sprache, bezeichnung)
        val actual = dummy.localizations[sprache]

        assertEquals(expected, actual)
    }

    @Test
    fun `compareTo() Vergleich unter verschiedenen Klassen`() {
        val dummy1 = dummyLokalisierbaresSpielelement()

        class Dummy2LokalisierbaresSpielelement(
            override val id: Int, override val localizations: MutableMap<Sprachen, String?>
        ): LokalisierbaresSpielelement(id, localizations)

        val dummy2 = Dummy2LokalisierbaresSpielelement(DUMMY_ID, dummyLocalizations())

        val actual = dummy1.compareTo(dummy2)

        val unexpected = 0
        assertNotEquals(unexpected, actual)
    }

    @Test
    fun `compareTo() Vergleich unter gleichen Klassen, aber unterschiedlichen IDs`() {
        val dummy1 = DummyLokalisierbaresSpielelement(1, dummyLocalizations())
        val dummy2 = DummyLokalisierbaresSpielelement(2, dummyLocalizations())

        val condition = dummy1 < dummy2 // implizierter compareTo()-Aufruf

        assertTrue(condition)
    }

    @Test
    fun `compareTo() Vergleich unter gleichen Klassen und IDs, aber unterschiedlichen Bezeichnungen`() {
        val dummy1 = DummyLokalisierbaresSpielelement(DUMMY_ID, mutableMapOf(Sprachen.OG to "a"))
        val dummy2 = DummyLokalisierbaresSpielelement(DUMMY_ID, mutableMapOf(Sprachen.OG to "b"))

        val condition = dummy1 < dummy2 // implizierter compareTo()-Aufruf

        assertTrue(condition)
    }

    private fun testKorrekteInstanziierung(
        instanziierteID: Int,
        instanziierteSprache: Sprachen,
        instanziierteBezeichnung: String,
        dummy: DummyLokalisierbaresSpielelement
    ) {
        val dummyID = dummy.id
        val dummyLocalizations = dummy.localizations

        assertEquals(instanziierteID, dummyID)
        for (sprache in Sprachen.entries) {
            val actual = dummyLocalizations[sprache]
            if (sprache == instanziierteSprache || sprache == Sprachen.OG) {
                assertEquals(instanziierteBezeichnung, actual)
            } else {
                assertEquals(null, actual)
            }
        }
    }

    @Test
    fun `fromEingabe() korrekte Instanziierung durch Eingabe`() {
        val eingabeID = 42
        val eingabeSprache = Sprachen.DE
        val eingabeBezeichnung = "Bezeichnung"

        val (dummyID, dummyLocalizations) = DummyLokalisierbaresSpielelement.fromEingabe(
            eingabeID, eingabeSprache, eingabeBezeichnung
        )
        val dummyFromEingabe = DummyLokalisierbaresSpielelement(dummyID, dummyLocalizations)

        testKorrekteInstanziierung(eingabeID, eingabeSprache, eingabeBezeichnung, dummyFromEingabe)
    }

}
