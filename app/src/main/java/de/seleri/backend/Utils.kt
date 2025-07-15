package de.seleri.backend

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
            val escaped = attributswert //.
                .replace("\\", "\\\\") // \
                .replace("\"", "\\\"") // "
                .replace("\n", "\\n")  // Zeilenumbruch
                .replace("\t", "\\t")  // Tab
            zeile.append("\"$escaped\"")
        }

        // [Element1,Element2,Element3]
        is Collection<*> -> {
            zeile.append("[")

            // sicheres Casten und Sortieren
            val sortierteAttributswertListe =
                (attributswert.filterIsInstance<LokalisierbaresSpielelement>()).sorted()

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
                if (value != null) { // Einträge ohne Wert werden übersprungen
                    mapInhalt.append(
                        attributToYamlZeile(
                            anzahlEinrueckungen + 1, key.toString(), value
                        )
                    )
                }
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
 * Ermittelt eine neue, eindeutige ID, die um 1 höher ist als jede ID in der gegebenen Collection.
 * Lücken werden also nicht aufgefüllt!
 *
 * @param hoeherAlsIn Liste von Spielelementen, deren höchste ID bestimmt wird
 * @return neue, eindeutige ID
 */
internal fun neueID(hoeherAlsIn: Collection<LokalisierbaresSpielelement>): Int {
    return (hoeherAlsIn.maxOfOrNull { it.id } ?: 0) + 1
}

/**
 * Findet ein Spielelement anhand einer Texteingabe in der Collection<T>.
 *
 * @param bezeichnung Kartentext oder Sammlungs-Name einer beliebigen Sprache
 * @return gefundenes Element oder null (um im nächsten Schritt das Element erstellen zu können)
 */
internal fun <T: LokalisierbaresSpielelement> Collection<T>.finde(
    bezeichnung: String
): T? {
    return find { element ->
        element.localizations.values // Greift auf alle übersetzten Bezeichnungen des Elements zu.
            .any { localization -> localization == bezeichnung }
        // Überprüft, ob irgendeine dieser Übersetzungen exakt mit dem gesuchten Text übereinstimmt.
    }
}

/**
 * Findet ein Spielelement anhand seiner ID in der Collection<T>.
 *
 * @param id gesuchte ID
 * @return gefundenes Element oder Error (es ist illegal, nach IDs zu suchen, die nicht existieren)
 */
internal fun <T: LokalisierbaresSpielelement> Collection<T>.finde(
    id: Int
): T = find { it.id == id } ?: error("Element mit ID $id nicht gefunden")

/**
 * Findet mehrere Spielelemente anhand einer Collection von IDs in der Collection<T>.
 *
 * @param ids Collection gesuchter IDs
 * @return Menge gefundener Elemente
 */
internal fun <T: LokalisierbaresSpielelement> Collection<T>.finde(
    ids: Collection<Int>
): Set<T> {
    return ids.mapTo(mutableSetOf()) { id -> finde(id) }
}
