package de.seleri.spielelemente

abstract class SammlungAnSpielelementen<T : LokalisierbaresSpielelement>(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    open val urspruenglicheElemente: List<T>,
    open var weitereElemente: List<T>
) : LokalisierbaresSpielelement(id, localizations) {
    fun getAlleElemente(): List<T> = urspruenglicheElemente + weitereElemente

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        stringZwischenfuegen(output, "$id", NAME__, true)
        sammlungToYaml(output, TAB_URSPRUENGLICHE, urspruenglicheElemente)
        sammlungToYaml(output, TAB_WEITERE, weitereElemente)
        return output.toString()
    }
}

fun stringZweiMalZwischenfuegen(output: StringBuilder, nach1: String, nach2: String, einfuegen: String) {
    stringZwischenfuegen(output, nach1, einfuegen, false)
    stringZwischenfuegen(output, nach2, einfuegen, false)
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

@Suppress("UNCHECKED_CAST")
fun <T : SammlungAnSpielelementen<E>, E : LokalisierbaresSpielelement> yamlToSammlung(
    data: Map<String, Any>,
    moeglicheKarten: List<E>,
    constructor: (Int, MutableMap<Sprachen, String>, List<E>, List<E>) -> T
): T {
    val dummy = yamlToLokalisierbaresElement(data, ::LokalisierbaresSpielelement)

    val urspruenglicheIds =
        (data["$URSPRUENGLICHE$KARTEN$IDS"] ?: data["$URSPRUENGLICHE$KATEGORIEN$IDS"]) as List<Int>
    val weitereIds = (data["$WEITERE$KARTEN$IDS"] ?: data["$WEITERE$KATEGORIEN$IDS"]) as List<Int>

    return constructor(
        dummy.id,
        dummy.localizations,
        findeElemente(urspruenglicheIds, moeglicheKarten),
        findeElemente(weitereIds, moeglicheKarten)
    )
}

fun <T : SammlungAnSpielelementen<E>, E : LokalisierbaresSpielelement> eingabeToSammlung(
    id: Int,
    sprache: Sprachen,
    name: String,
    urspruenglicheElemente: List<E>,
    constructor: (Int, MutableMap<Sprachen, String>, List<E>, List<E>) -> T
): T {
    val dummy = eingabeToKarte(id, sprache, name)
    return constructor(dummy.id, dummy.localizations, urspruenglicheElemente, listOf())
}
