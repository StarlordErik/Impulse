package de.seleri.spielelemente

data class Localizations(val mapSpracheZuUebersetzung: MutableMap<Sprachen, String>) : ToYaml {
    override fun toYaml(): String {
        val output = StringBuilder()
        mapSpracheZuUebersetzung.forEach { (sprache, text) ->
            output.append("$DREI_TAB$sprache: \"${escapeYamlString(text)}\"\n")
        }
        return output.toString()
    }

    fun setUebersetzung(sprache: Sprachen, text: String){
        mapSpracheZuUebersetzung[sprache] = text
    }

}

fun eingabeToLocalizations(sprache: Sprachen, text: String): Localizations {
    val map = Sprachen.entries.associateWith { if (it == sprache) text else "" }
    return Localizations(map.toMutableMap())
}

@Suppress("UNCHECKED_CAST")
fun yamlToLocalization(data: Map<String, Any>): Localizations {
    val bezeichnung = data[TEXT] ?: data[NAME]
    val text = bezeichnung as Map<String, String>
    return Localizations(text.mapKeys { Sprachen.valueOf(it.key) }.toMutableMap())
}

