package de.seleri.spielelemente

// YAML-Bezeichnungen der Attribute
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

/**
 * Konvertiert ein Attribut zu einer Zeile im YAML-Format
 *
 * @param anzahlEinrueckungen Anzahl an Tabs zur Einrückung der Zeile
 * @param attributsname Name des Attributs, welcher angezeigt werden soll
 * @param attributswert Wert des Attributs (beliebiger Typ)
 * @return YAML-Zeile als String
 */
fun attributToYamlZeile(
    anzahlEinrueckungen: Int, attributsname: String, attributswert: Any?
): String {
    val zeile = StringBuilder()

    // TabsAttributsname:
    zeile.append(EIN_TAB.repeat(anzahlEinrueckungen))
    zeile.append(attributsname)
    zeile.append(":")

    // Kein Leerzeichen ohne direkten Attributswert (inklusive für den Titel einer Map)
    if (attributswert != null && attributswert !is Map<*, *>) zeile.append(" ")

    when (attributswert) {

        // der String wird escaped
        is String -> {
            val escaped = attributswert.replace("\\", "\\\\") // \
                .replace("\"", "\\\"") // "
                .replace("\n", "\\n")  // Zeilenumbruch
                .replace("\t", "\\t")  // Tab
            zeile.append("\"$escaped\"")
        }

        // [Element1,Element2,Element3]
        is List<*> -> {
            zeile.append("[")
            attributswert.forEachIndexed { index, item ->
                zeile.append(
                    when (item) {

                        // bei Spielelementen wird nur die eindeutige ID gespeichert
                        is LokalisierbaresSpielelement -> item.id

                        else -> item.toString()
                    }
                )

                // nur Kommata zwischen den Elementen (nicht hinter dem letzten)
                if (index < attributswert.size - 1) zeile.append(",")
            }
            zeile.append("]")
        }

        /*
        * Mapname:
        *   rekursiverAufruf(ersterSchlüssel)
        *   rekursiverAufruf(zweiterSchlüssel)
        */
        is Map<*, *> -> {
            zeile.append("\n")
            val mapInhalt = StringBuilder()
            attributswert.forEach { (key, value) ->
                mapInhalt.append(
                    attributToYamlZeile(
                        anzahlEinrueckungen + 1, key.toString(), value
                    )
                )
            }
            // entfernt den letzten Zeilenumbruch und fügt es zu der "Zeile" hinzu
            zeile.append(mapInhalt.toString().trimEnd())
        }

        // ohne Attributswert
        null -> zeile.append("")

        // bei primitiven Datentypen (v.a. Integer, Booleans)
        else -> zeile.append(attributswert)
    }

    zeile.append("\n")
    return zeile.toString()
}

/**
 * Findet ein Spielelement anhand einer Texteingabe.
 *
 * @param bezeichnung Kartentext oder Sammlungs-Name einer beliebigen Sprache
 * @param findeIn Liste der zu durchsuchenden Elemente
 * @return gefundenes Element oder null (um im nächsten Schritt das Element erstellen zu können)
 */
fun <T : LokalisierbaresSpielelement> findeElement(bezeichnung: String, findeIn: List<T>): T? {
    return findeIn.find { element ->
        element.localizations.values.any { localization ->
            localization == bezeichnung
        }
    }
}

/**
 * Findet ein Spielelement anhand seiner ID.
 *
 * @param id gesuchte ID
 * @param findeIn Liste der zu durchsuchenden Elemente
 * @return gefundenes Element oder Error (es ist illegal, nach IDs zu suchen, die nicht existieren)
 */
fun <T : LokalisierbaresSpielelement> findeElement(id: Int, findeIn: List<T>): T =
    findeIn.find { it.id == id } ?: error("Element mit ID $id nicht gefunden")

/**
 * Findet mehrere Spielelemente anhand einer Liste von IDs.
 *
 * @param ids Liste gesuchter IDs
 * @param findeIn Liste der zu durchsuchenden Elemente
 * @return Liste gefundener Elemente
 */
fun <T : LokalisierbaresSpielelement> findeElemente(ids: List<Int>, findeIn: List<T>): List<T> {
    return ids.map { id ->
        findeElement(id, findeIn)
    }
}
