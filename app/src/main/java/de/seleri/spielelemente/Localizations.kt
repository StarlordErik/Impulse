package de.seleri.spielelemente

data class Localizations(val mapSpracheZuUebersetzung: Map<Sprachen, String>) : ToYaml {
    override fun toYaml(): String {
        val output = StringBuilder()
        mapSpracheZuUebersetzung.forEach { (sprache, text) ->
            output.append("$DREI_TAB$sprache: \"${escapeYamlString(text)}\"\n")
        }
        return output.toString()
    }
}

fun eingabeToLocalizations(sprache: Sprachen, text: String): Localizations {
    val map = Sprachen.entries.associateWith { if (it == sprache) text else "" }
    return Localizations(map)
}
