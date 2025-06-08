package de.seleri.spielelemente

private const val EIN_TAB: String = "  "
private const val ZWEI_TAB: String = "$EIN_TAB$EIN_TAB"
private const val DREI_TAB: String = "$ZWEI_TAB$EIN_TAB"

const val ID_: String = "${EIN_TAB}- ID: "
const val TEXT__: String = "\n${ZWEI_TAB}Text:\n"
const val NAME__: String = "\n${ZWEI_TAB}Name:\n"

const val URSPRUENGLICHE: String = "${ZWEI_TAB}ursprüngliche_"
const val WEITERE: String = "${ZWEI_TAB}weitere_"
private const val IDS: String = "-IDs: ["
private const val LISTENENDE: String = "]\n"

fun localizationsToYaml(output: StringBuilder, localizations: Map<Sprachen, String>) {
    localizations.forEach { (sprache, localizedText) ->
        output.append("$DREI_TAB$sprache: \"${escapeYamlString(localizedText)}\"\n")
    }
}

fun kartenListenToYaml(output: StringBuilder, prefix: String, karten: List<Karte>) {
    listenToYaml(output, prefix, karten.map { it.id })
}

fun kategorieListenToYaml(output: StringBuilder, prefix: String, kategorien: List<Kategorie>) {
    listenToYaml(output, prefix, kategorien.map { it.id })
}

fun listenToYaml(output: StringBuilder, prefix: String, ids: List<Int>) {
    output.append("$prefix$IDS")
    if (ids.isNotEmpty()) {
        ids.forEach {
            output.append("$it,")
        }
        output.deleteCharAt(output.lastIndex)
    }
    output.append(LISTENENDE)
}

private fun escapeYamlString(input: String): String {
    return input
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\n", "\\n")
        .replace("\t", "\\t")
}
