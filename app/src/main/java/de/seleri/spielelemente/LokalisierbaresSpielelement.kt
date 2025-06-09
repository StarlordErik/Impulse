package de.seleri.spielelemente

import kotlin.collections.set

open class LokalisierbaresSpielelement(
    open val id: Int, open val localizations: MutableMap<Sprachen, String>
) {
    open fun toYaml(): String {
        val output = StringBuilder()
        output.append(attributToYamlZeile(1, "- $ID", id))
        return output.toString()
    }

    fun localizationsToYaml(bezeichnung: String): String {
        return attributToYamlZeile(2, bezeichnung, localizations)
    }

    fun setUebersetzung(sprache: Sprachen, bezeichnung: String) {
        if (sprache != Sprachen.OG) localizations[sprache] = bezeichnung
    }
}


fun <T : LokalisierbaresSpielelement> findeElemente(ids: List<Int>, findeIn: List<T>): List<T> {
    return ids.map { id ->
        findeIn.find { it.id == id } ?: error("Element mit ID $id nicht gefunden")
    }
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
    val map =
        Sprachen.entries.associateWith { if (it == sprache || it == Sprachen.OG) text else "" }
            .toMutableMap()
    return constructor(id, map)
}
