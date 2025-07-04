package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

data class DummySammlungAnSpielelementen(
    override var id: Int,
    override val localizations: MutableMap<Sprachen, String?>,
    override val originaleElemente: Map<String, MutableSet<Karte>>,
    override val hinzugefuegteElemente: MutableSet<Karte>
): SammlungAnSpielelementen<Karte>(
    id, localizations, originaleElemente, hinzugefuegteElemente
) {
    fun dummyOriginaleUndHinzugefuegteElementeToYaml(elemente: String): String =
        originaleUndHinzugefuegteElementeToYaml(elemente)

    override fun getKarten(): Set<Karte> = getAlleElemente()
    override fun getAktuelleKarten(): Set<Karte> = getAlleAktuellenElemente()
    override fun getUngeseheneKarten(): Set<Karte> = geseheneKartenRausfiltern(getAktuelleKarten())
    override fun setKartenUngesehen() = setKartenUngesehen(getAktuelleKarten())

    fun dummyElementeHinzufuegen(neueElemente: Collection<Karte>) =
        elementeHinzufuegen(neueElemente)

    fun dummyElementeEntfernen(element: Karte) = elementEntfernen(element)

    companion object {
        fun fromEingabe(
            id: Int, sprache: Sprachen, name: String, originaleKarten: Collection<Karte>
        ): DummySammlungAnSpielelementen =
            fromEingabe(id, sprache, name, originaleKarten, ::DummySammlungAnSpielelementen)

        fun fromYaml(
            yamlDaten: Map<String, Any>, moeglicheKarten: Collection<Karte>
        ): DummySammlungAnSpielelementen =
            fromYaml(yamlDaten, moeglicheKarten, ::DummySammlungAnSpielelementen)
    }
}

fun alleDummyKarten(): Set<Karte> {
    val dummyKarten = getDummyKarten("dummyKarten")
    val actual = dummyKarten.size

    val expected = 5
    assertEquals(expected, actual)

    return dummyKarten
}

fun karte1(): Karte = alleDummyKarten().finde(1)
fun karte2(): Karte = alleDummyKarten().finde(2)
fun karte3(): Karte = alleDummyKarten().finde(3)

const val DUMMY_NAME = "Name"
fun dummyOriginaleKartenIDs(): MutableSet<Karte> = mutableSetOf(karte1(), karte2())
fun dummyDavonEntfernteKarten(): MutableSet<Karte> = mutableSetOf(karte1())
fun dummyOriginaleKarten(): Map<String, MutableSet<Karte>> =
    mapOf(IDS to dummyOriginaleKartenIDs(), DAVON_ENTFERNT to dummyDavonEntfernteKarten())

fun dummyHinzugefuegteKarten(): MutableSet<Karte> = mutableSetOf(karte3())
fun dummySammlung() = DummySammlungAnSpielelementen(
    DUMMY_ID, dummyLocalizations(), dummyOriginaleKarten(), dummyHinzugefuegteKarten()
)

class SammlungAnSpielelementenTest {

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
        val dummy = dummySammlung()

        val actual = dummy.toYaml()

        val expected = """
        |  - $ID: $DUMMY_ID
        |    $DUMMY_NAME:
        |      ${Sprachen.OG}: "$DUMMY_OG"
        |      ${Sprachen.DE}: "$DUMMY_DE"
        |
        """.trimMargin()
        assertEquals(expected, actual)
    }

}
