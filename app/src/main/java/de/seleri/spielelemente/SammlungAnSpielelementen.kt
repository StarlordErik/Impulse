package de.seleri.spielelemente

abstract class SammlungAnSpielelementen<T>(
    override val id: Int,
    override val localizations: Localizations,
    open val urspruenglicheElemente: List<T>,
    open var weitereElemente: List<T>
) : LokalisierbaresSpielelement(id, localizations), ToYaml {
    fun getAlleElemente(): List<T> = urspruenglicheElemente + weitereElemente
}

fun <T : SammlungAnSpielelementen<E>, E : LokalisierbaresSpielelement> eingabeToSammlung(
    id: Int,
    sprache: Sprachen,
    name: String,
    urspruenglicheElemente: List<E>,
    constructor: (Int, Localizations, List<E>, List<E>) -> T
): T {
    // Hilfsklasse für temporären Dummy
    class DummyElement(override val id: Int, override val localizations: Localizations) :
        LokalisierbaresSpielelement(id, localizations)

    // Erzeuge ein Dummy-Objekt, nur um id und Localizations zu extrahieren
    val dummy = eingabeToLokalisierbaresElement(id, sprache, name, ::DummyElement)

    return constructor(dummy.id, dummy.localizations, urspruenglicheElemente, listOf())
}

fun <T : LokalisierbaresSpielelement> sammlungToYaml(
    output: StringBuilder, prefix: String, elemente: List<T>
) {
    output.append("$prefix$IDS_")
    if (elemente.isNotEmpty()) {
        elemente.forEach { output.append("${it.id},") }
        output.deleteCharAt(output.lastIndex) // letztes Komma entfernen
    }
    output.append(LISTENENDE)
}
