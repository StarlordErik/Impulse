package de.seleri.spielelemente

/**
 * Findet ein Spielelement anhand einer Texteingabe.
 *
 * @param bezeichnung Kartentext oder Sammlungs-Name einer beliebigen Sprache
 * @param findeIn Collection der zu durchsuchenden Elemente
 * @return gefundenes Element oder null (um im nächsten Schritt das Element erstellen zu können)
 */
internal fun <T : LokalisierbaresSpielelement> findeElement(bezeichnung: String, findeIn: Collection<T>): T? {
    return findeIn.find { element ->
        element.localizations.values // Greift auf alle übersetzten Bezeichnungen des Elements zu.
            .any { localization -> localization == bezeichnung }
            // Überprüft, ob irgendeine dieser Übersetzungen exakt mit dem gesuchten Text übereinstimmt.
    }
}

/**
 * Findet ein Spielelement anhand seiner ID.
 *
 * @param id gesuchte ID
 * @param findeIn Collection der zu durchsuchenden Elemente
 * @return gefundenes Element oder Error (es ist illegal, nach IDs zu suchen, die nicht existieren)
 */
internal fun <T : LokalisierbaresSpielelement> findeElement(id: Int, findeIn: Collection<T>): T =
    findeIn.find { it.id == id } ?: error("Element mit ID $id nicht gefunden")

/**
 * Findet mehrere Spielelemente anhand einer Collection von IDs.
 *
 * @param ids Collection gesuchter IDs
 * @param findeIn Collection der zu durchsuchenden Elemente
 * @return Menge gefundener Elemente
 */
internal fun <T : LokalisierbaresSpielelement> findeElemente(ids: Collection<Int>, findeIn: Collection<T>): Set<T> {
    return ids.mapTo(mutableSetOf()) { id -> findeElement(id, findeIn) }
}

/**
 * Konvertiert ein Attribut zu einer Zeile im YAML-Format
 *
 * @param anzahlEinrueckungen Anzahl an Tabs zur Einrückung der Zeile
 * @param attributsname Name des Attributs, welcher angezeigt werden soll
 * @param attributswert Wert des Attributs (beliebiger Typ)
 * @return YAML-Zeile als String
 */
internal fun attributToYamlZeile(
    anzahlEinrueckungen: Int, attributsname: String, attributswert: Any?
): String {
    val zeile = StringBuilder()

    // TabsAttributsname:
    zeile.append("  ".repeat(anzahlEinrueckungen))
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
        is Collection<*> -> {
            zeile.append("[")

            // sicheres Casten und Sortieren nach ID
            val sortierteAttributswertListe = (attributswert.filterIsInstance<LokalisierbaresSpielelement>())
                .sortedBy { it.id }

            sortierteAttributswertListe.forEachIndexed { index, item ->
                zeile.append(item.id)

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
