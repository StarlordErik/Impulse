package de.seleri.spielelemente

import kotlin.collections.set

abstract class LokalisierbaresSpielelement(
    open val id: Int, open val localizations: MutableMap<Sprachen, String>
) {
    open fun toYaml(): String {
        val output = StringBuilder()
        output.append(attributToYamlZeile(1, "- $ID", id))
        return output.toString()
    }

    open fun localizationsToYaml(bezeichnung: String): String {
        return attributToYamlZeile(2, bezeichnung, localizations)
    }

    open fun setUebersetzung(sprache: Sprachen, bezeichnung: String) {
        if (sprache != Sprachen.OG) localizations[sprache] = bezeichnung
    }

    companion object {
        fun fromEingabe(
            id: Int, sprache: Sprachen, text: String
        ): Pair<Int, MutableMap<Sprachen, String>> {
            val localizations =
                Sprachen.entries.associateWith { if (it == sprache || it == Sprachen.OG) text else "" }
                    .toMutableMap()
            return id to localizations
        }

        @Suppress("UNCHECKED_CAST")
        fun fromYaml(
            data: Map<String, Any>
        ): Pair<Int, MutableMap<Sprachen, String>> {
            val id = data[ID] as Int
            val localizations = ((data[TEXT]
                ?: data[NAME]) as Map<String, String>).mapKeys { Sprachen.valueOf(it.key) }
                .toMutableMap()
            return id to localizations
        }
    }
}
