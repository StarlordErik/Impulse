package de.seleri.spielelemente

import org.junit.Assert.assertEquals

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

        fun fromYaml(yamlDaten: Map<String, Any>, moeglicheKarten: Collection<Karte>): DummySammlungAnSpielelementen =
            fromYaml(yamlDaten, moeglicheKarten, ::DummySammlungAnSpielelementen)
    }
}

fun alleDummyKarten() : Set<Karte> {
    val dummyKarten = getDummyKarten("dummyKarten")
    val actual = dummyKarten.size

    val expected = 5
    assertEquals(expected, actual)

    return dummyKarten
}

class SammlungAnSpielelementenTest
