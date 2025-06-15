package de.seleri.spielelemente

const val KARTEN: String = "Karten"
const val KATEGORIEN: String = "Kategorien"
const val SPIELE: String = "Spiele"

const val EIN_TAB: String = "  "

const val ID: String = "ID"

const val TEXT: String = "Text"
const val NAME: String = "Name"

const val GESEHEN: String = "gesehen"
const val GELOESCHT: String = "gelöscht"

const val ORIGINALE: String = "originale_"
const val IDS = "${ID}s"
const val DAVON_ENTFERNT: String = "davon_entfernt"

const val HINZUGEFUEGTE: String = "hinzugefügte_"
const val BINDESTRICH_IDS: String = "-$IDS"

fun attributToYamlZeile(
    anzahlEinrueckungen: Int, attributsname: String, attributswert: Any?
): String {
    val zeile = StringBuilder()
    zeile.append(EIN_TAB.repeat(anzahlEinrueckungen))
    zeile.append(attributsname)
    zeile.append(":")

    if (attributswert != null && attributswert !is Map<*, *>) zeile.append(" ")

    when (attributswert) {
        is String -> {
            val escaped =
                attributswert.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n")
                    .replace("\t", "\\t")
            zeile.append("\"$escaped\"")
        }

        is List<*> -> {
            zeile.append("[")
            attributswert.forEachIndexed { index, item ->
                zeile.append(
                    when (item) {
                        is LokalisierbaresSpielelement -> item.id
                        else -> item.toString()
                    }
                )
                if (index < attributswert.size - 1) zeile.append(",")
            }
            zeile.append("]")
        }

        is Map<*, *> -> {
            zeile.append("\n")
            val mapContent = StringBuilder()
            attributswert.forEach { (key, value) ->
                mapContent.append(
                    attributToYamlZeile(
                        anzahlEinrueckungen + 1, key.toString(), value
                    )
                )
            }
            zeile.append(mapContent.toString().trimEnd())
        }

        null -> zeile.append("")

        else -> zeile.append(attributswert)
    }

    zeile.append("\n")
    return zeile.toString()
}

fun <T : LokalisierbaresSpielelement> findeElement(bezeichnung: String, findeIn: List<T>): T? {
    return findeIn.find { element ->
        element.localizations.values.any { localization ->
            localization.equals(bezeichnung)
        }
    }
}

fun <T : LokalisierbaresSpielelement> findeElement(id: Int, findeIn: List<T>): T =
    findeIn.find { it.id == id } ?: error("Element mit ID $id nicht gefunden")


fun <T : LokalisierbaresSpielelement> findeElemente(ids: List<Int>, findeIn: List<T>): List<T> {
    return ids.map { id ->
        findeElement(id, findeIn)
    }
}
