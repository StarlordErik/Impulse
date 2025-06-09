package de.seleri.spielelemente

abstract class SammlungAnSpielelementen<T : LokalisierbaresSpielelement>(
    override val id: Int,
    override val localizations: Localizations,
    open val urspruenglicheElemente: List<T>,
    open var weitereElemente: List<T>
) : LokalisierbaresSpielelement(id, localizations){
    fun getAlleElemente(): List<T> = urspruenglicheElemente + weitereElemente

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        val woStehtText = output.indexOf(TEXT__)
        output.replace(woStehtText, woStehtText + TEXT__.length, NAME__)
        sammlungToYaml(output, TAB_URSPRUENGLICHE, urspruenglicheElemente)
        sammlungToYaml(output, TAB_WEITERE, weitereElemente)
        return output.toString()
    }
}

fun <T : SammlungAnSpielelementen<E>, E : LokalisierbaresSpielelement> eingabeToSammlung(
    id: Int,
    sprache: Sprachen,
    name: String,
    urspruenglicheElemente: List<E>,
    constructor: (Int, Localizations, List<E>, List<E>) -> T
): T {
    val dummy = eingabeToLokalisierbaresElement(id, sprache, name, ::LokalisierbaresSpielelement)

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
