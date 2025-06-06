package de.seleri.spielelemente

const val EIN_TAB: String = "  "
const val ZWEI_TAB: String = "$EIN_TAB$EIN_TAB"
const val DREI_TAB: String = "$ZWEI_TAB$EIN_TAB"

fun escapeYamlString(input: String): String {
    return input
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\n", "\\n")
        .replace("\t", "\\t")
}
