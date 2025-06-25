package de.seleri.spielelemente

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

            // sicheres Casten und Sortieren
            val sortierteAttributswertListe = (attributswert.filterIsInstance<LokalisierbaresSpielelement>())
                .sorted()

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
