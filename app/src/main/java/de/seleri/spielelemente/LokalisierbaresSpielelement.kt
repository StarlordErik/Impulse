package de.seleri.spielelemente

import kotlin.collections.set

abstract class LokalisierbaresSpielelement(
    open val id: Int, open val localizations: MutableMap<Sprachen, String>
) {
    open fun toYaml(): String {
        val output = StringBuilder()
        output.append("$ID_$id")
        localizations.forEach { (sprache, uebersetzung) ->
            val escapedUebersetzung = uebersetzung
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\t", "\\t")
            output.append("$DREI_TAB$sprache: \"$escapedUebersetzung\"\n")
        }
        return output.toString()
    }

    fun setUebersetzung(sprache: Sprachen, bezeichnung: String) {
        localizations[sprache] = bezeichnung
    }
}

fun <T : LokalisierbaresSpielelement> findeElemente(ids: List<Int>, findeIn: List<T>): List<T> {
    return ids.map { id ->
        findeIn.find { it.id == id } ?: error("Element mit ID $id nicht gefunden")
    }
}

fun stringZwischenfuegen(output: StringBuilder, nach: String, einfuegen: String, vorLocalization: Boolean) {
    if (vorLocalization) output.insert(output.indexOf(nach) + nach.length, einfuegen)
    else output.insert(output.lastIndexOf(nach) + nach.length, einfuegen)
}

@Suppress("UNCHECKED_CAST")
fun <T : LokalisierbaresSpielelement> yamlToLokalisierbaresElement(
    data: Map<String, Any>, constructor: (Int, MutableMap<Sprachen, String>) -> T
): T {
    val id = data[ID] as Int
    val localizations =
        ((data[TEXT] ?: data[NAME]) as Map<String, String>).mapKeys { Sprachen.valueOf(it.key) }
            .toMutableMap()
    return constructor(id, localizations)
}

fun <T : LokalisierbaresSpielelement> eingabeToLokalisierbaresElement(
    id: Int, sprache: Sprachen, text: String, constructor: (Int, MutableMap<Sprachen, String>) -> T
): T {
    val map = Sprachen.entries.associateWith { if (it == sprache) text else "" }.toMutableMap()
    return constructor(id, map)
}
