package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test
import org.yaml.snakeyaml.Yaml

/** Dummy-Implementierung, da Klasse abstrakt ist und manche Methoden protected */
data class DummyLokalisierbaresSpielelement(
    override var id: Int, override val localizations: MutableMap<Sprachen, String?>
): LokalisierbaresSpielelement(id, localizations) {
    fun dummyLocalizationsToYaml(bezeichnung: String) = super.localizationsToYaml(bezeichnung)

    companion object {
        fun fromEingabe(id: Int, sprache: Sprachen, bezeichnung: String) =
            LokalisierbaresSpielelement.fromEingabe(id, sprache, bezeichnung)

        fun fromYaml(yamlDatensatz: Map<String, Any>): Set<DummyLokalisierbaresSpielelement> =
            setOf(
                // erst kommt die normale Konvertierungsfunktion - Problem; es gibt statt einem Objekt ein Tupel aus
                LokalisierbaresSpielelement.fromYaml(yamlDatensatz)
                    // also wird jedes Tupel in ein Objekt umgewandelt
                    .let { (id, localizations) ->
                        DummyLokalisierbaresSpielelement(
                            id, localizations
                        )
                    }) // und diese Tupel werden dann in ein Set umgewandelt

        fun fromYamlListe(
            element: String,
            yamlDaten: Map<String, Any>,
            converter: (Map<String, Any>) -> Collection<DummyLokalisierbaresSpielelement>
        ) = LokalisierbaresSpielelement.fromYamlListe(element, yamlDaten, converter)
    }
}

const val DUMMY_ID = 42
const val DUMMY_BEZEICHNUNG = "Text"
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

        val actual = dummy.dummyLocalizationsToYaml(DUMMY_BEZEICHNUNG)

        val expected = """
            |    $DUMMY_BEZEICHNUNG:
            |      ${Sprachen.OG}: "$DUMMY_OG"
            |      ${Sprachen.DE}: "$DUMMY_DE"
            |
        """.trimMargin()
        assertEquals(expected, actual)
    }

    @Test
    fun `Rundreise Objekt zu Yaml zu Objekt zu Yaml`() {
        val dummy1 = dummyLokalisierbaresSpielelement()
        val yamlDummy1 = dummyToYaml(dummy1)

        val dummyDaten = (Yaml().load(yamlDummy1) as List<Map<String, Any>>).first()
        val dummy2 = DummyLokalisierbaresSpielelement.fromYaml(dummyDaten).first()

        assertEquals(dummy1, dummy2) // Objekt --> Yaml --> Objekt


        val yamlDummy2 = """
            |  - $ID: $DUMMY_ID
            |    $DUMMY_BEZEICHNUNG:
            |      ${Sprachen.OG}: "$DUMMY_OG"
            |      ${Sprachen.DE}: "$DUMMY_DE"
            |
        """.trimMargin()

        assertEquals(yamlDummy2, yamlDummy1) // Yaml --> Objekt --> Yaml
    }

    private fun dummyToYaml(dummy: DummyLokalisierbaresSpielelement) : String {
        val dummyStringBuilder = StringBuilder()
        dummyStringBuilder.append(dummy.toYaml())
        dummyStringBuilder.append(dummy.dummyLocalizationsToYaml(DUMMY_BEZEICHNUNG))
        return dummyStringBuilder.toString()
    }

    @Test
    fun `setUebersetzung() aendert die Uebersetzung, WENN sprache != Sprachen_OG`() {
        val dummy = dummyLokalisierbaresSpielelement()
        val sprache = Sprachen.EN

        dummy.setUebersetzung(sprache, DUMMY_BEZEICHNUNG)
        val actual = dummy.localizations[sprache]

        val expected = DUMMY_BEZEICHNUNG
        assertEquals(expected, actual)
    }

    @Test
    fun `setUebersetzung() aendert die Uebersetzung NICHT, WENN sprache == Sprachen_OG`() {
        val dummy = dummyLokalisierbaresSpielelement()
        val sprache = Sprachen.OG

        val expected = dummy.localizations[sprache]

        dummy.setUebersetzung(sprache, DUMMY_BEZEICHNUNG)
        val actual = dummy.localizations[sprache]

        assertEquals(expected, actual)
    }

    @Test
    fun `compareTo() Vergleich unter verschiedenen Klassen`() {
        val dummy1 = dummyLokalisierbaresSpielelement()

        class Dummy2LokalisierbaresSpielelement(
            override var id: Int, override val localizations: MutableMap<Sprachen, String?>
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
        erwarteteID: Int,
        erwarteteOGBezeichnung: String,
        erwarteteDEBezeichnung: String?,
        erwarteteENBezeichnung: String?,
        dummy: DummyLokalisierbaresSpielelement
    ) {
        val actualID = dummy.id
        val actualOGBezeichnung = dummy.localizations[Sprachen.OG]
        val actualDEBezeichnung = dummy.localizations[Sprachen.DE]
        val actualENBezeichnung = dummy.localizations[Sprachen.EN]


        assertEquals(erwarteteID, actualID)
        assertEquals(erwarteteOGBezeichnung, actualOGBezeichnung)
        assertEquals(erwarteteDEBezeichnung, actualDEBezeichnung)
        assertEquals(erwarteteENBezeichnung, actualENBezeichnung)
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

        testKorrekteInstanziierung(
            eingabeID, eingabeBezeichnung, eingabeBezeichnung, null, dummyFromEingabe
        )
    }

    private fun getSaemtlicheDummyDaten() : Map<String, Any> {
        val dummyDaten = ladeYamlDaten("LokalisierbaresSpielelement.yml")
        assertTrue(dummyDaten.isNotEmpty()) // Test, ob die Yaml-Datei nicht leer ist
        return dummyDaten
    }

    private fun getDummyDaten(titel: String) : Set<DummyLokalisierbaresSpielelement>{
        val dummyElement = titel
        val dummyDaten = getSaemtlicheDummyDaten()

        assertTrue(dummyElement in dummyDaten) // Test, ob die gesuchte Daten auffindbar sind

        return DummyLokalisierbaresSpielelement.fromYamlListe(
            dummyElement, dummyDaten
        ) { DummyLokalisierbaresSpielelement.fromYaml(it) }
    }

    @Test
    fun `fromYaml() Exception bei fehlenden Yaml-Attributen`() {
        assertThrows(IllegalArgumentException::class.java) {
            getDummyDaten("fehlendeID")
        }
        assertThrows(IllegalArgumentException::class.java) {
            getDummyDaten("fehlenderTextOderName")
        }
    }

    @Test
    fun `duplikateLoeschen() Duplikate werden geloescht`(){
        val dummys = getDummyDaten("Duplikate")

        val actual = dummys.size

        val expected = 4
        assertEquals(expected, actual)
    }

    @Test
    fun `doppelteIDsErsetzen() doppelt belegte IDs werden neu belegt`(){
        val dummys = getDummyDaten("doppelteIDs")

        val dummyIDs = (dummys.map { it.id }).toSet()
        val actual = dummyIDs.size

        val expected = 3
        assertEquals(expected, actual)
    }

    private fun `fromYaml(7) Objekt mit der ID 7 korrekt aus der Yaml gelesen`(
        dummy: DummyLokalisierbaresSpielelement
    ) {
        testKorrekteInstanziierung(
            erwarteteID = 7,
            erwarteteOGBezeichnung = "OG",
            erwarteteDEBezeichnung = "DE",
            erwarteteENBezeichnung = "EN",
            dummy
        )
    }

    private fun `fromYaml(42) Objekt mit der ID 42 korrekt aus der Yaml gelesen`(
        dummy: DummyLokalisierbaresSpielelement
    ) {
        testKorrekteInstanziierung(
            erwarteteID = 42,
            erwarteteOGBezeichnung = "nur OG",
            erwarteteDEBezeichnung = null,
            erwarteteENBezeichnung = null,
            dummy
        )
    }

    @Test
    fun `fromYamlListe() Yaml-Datei wird korrekt in eine Menge von Objekten verwandelt`() {
        val dummys = getDummyDaten("DummyLokalisierbaresSpielelement")

        assertEquals(2, dummys.size)

        val sortedDummys = dummys.sorted()
        `fromYaml(7) Objekt mit der ID 7 korrekt aus der Yaml gelesen`(sortedDummys[0])
        `fromYaml(42) Objekt mit der ID 42 korrekt aus der Yaml gelesen`(sortedDummys[1])
    }
}
