package de.seleri.spielelemente

const val KARTEN: String = "Karten"
const val TEXT: String = "Text"
const val GESEHEN: String = "gesehen"
const val GELOESCHT: String = "gelöscht"

/**
 * Karten mit Text und Statusinformationen
 *
 * @property id eindeutige ID der Karte
 * @property localizations Map mit den Übersetzungen des Kartentextes für verschiedene Sprachen
 * @property gesehen gibt an, ob die Karte bereits angezeigt wurde
 * @property geloescht gibt an, ob die Karte gelöscht wurde
 */
data class Karte(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    var gesehen: Boolean,
    var geloescht: Boolean
): LokalisierbaresSpielelement(id, localizations) {

    /**
     * Konvertiert die Karte ins YAML-Format.
     *
     * @return String mit allen Eigenschaften der Karte im YAML-Format
     */
    override fun toYaml(): String {
        val output = StringBuilder()

        // ID an erster Stelle (mit Bindestrich)
        output.append(super.toYaml())

        // Kartentexte in allen Sprachen
        output.append(localizationsToYaml(TEXT))

        // Statusinformationen
        output.append(attributToYamlZeile(2, GESEHEN, gesehen))
        output.append(attributToYamlZeile(2, GELOESCHT, geloescht))
        return output.toString()
    }

    companion object {

        /**
         * Erstellt eine neue Karte anhand von Benutzereingaben oder Skriptdaten.
         *
         * @param id neue, noch nicht vergebene Karten-ID
         * @param sprache Sprache des Kartentextes
         * @param kartentext Kartentext
         * @return neue Karte: ungesehen und ungelöscht
         */
        fun fromEingabe(id: Int, sprache: Sprachen, kartentext: String): Karte {

            // lässt id, sprache und kartentext in der Superklasse verarbeiten
            val (id, localizations) = LokalisierbaresSpielelement.fromEingabe(
                id, sprache, kartentext
            )

            return Karte(id, localizations, false, false)
        }

        /**
         * Erstellt eine Liste von Karten aus einer von SnakeYaml deserialisierten YAML-Datei.
         *
         * @param data YAML-Daten einer Karte oder einer Liste von Karten
         * @return Liste von Karten mit ausgelesenen Attributwerten
         */
        fun fromYaml(data: Map<String, Any>): List<Karte> {
            when {

                // Fall 1: mehrere Karten
                KARTEN in data -> {
                    return fromYamlListe(KARTEN, data) { fromYaml(it) }
                }

                // Fall 2: einzelne Karte
                GESEHEN in data && GELOESCHT in data -> {
                    val (id, localizations) = LokalisierbaresSpielelement.fromYaml(data)
                    val gesehen = data[GESEHEN] as Boolean
                    val geloescht = data[GELOESCHT] as Boolean

                    return listOf(Karte(id, localizations, gesehen, geloescht))
                }

                // Fall 3: ungültige Struktur
                else -> throw IllegalArgumentException("Ungültige Kartenstruktur.")
            }
        }
    }
}

