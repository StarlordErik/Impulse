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
    override var id: Int,
    override val localizations: MutableMap<Sprachen, String?>,
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
            val gesehen = false
            val geloescht = false

            return Karte(id, localizations, gesehen, geloescht)
        }

        /**
         * Erstellt eine Liste von Karten aus einer von SnakeYaml deserialisierten YAML-Datei.
         *
         * Wieso gibt es nur fromYaml und nicht fromYamlListe? Beides würde nur
         * den Parameter Map<String, Any> bekommen; Man müsste also definitiv die Daten kennen,
         * die man reintut - so differenziert das die Funktion ganz von allein.
         *
         * @param yamlDaten YAML-Daten einer oder mehrerer Karten
         * @return Menge von Karten mit ausgelesenen Attributwerten
         */
        fun fromYaml(yamlDaten: Map<String, Any>): Set<Karte> {
            when {

                // Fall 1: mehrere Karten
                KARTEN in yamlDaten -> {
                    return fromYamlListe(KARTEN, yamlDaten) { fromYaml(it) }
                }

                // Fall 2: einzelne Karte
                GESEHEN in yamlDaten && GELOESCHT in yamlDaten -> {
                    val (id, localizations) = LokalisierbaresSpielelement.fromYaml(yamlDaten)
                    val gesehen = yamlDaten[GESEHEN] as Boolean
                    val geloescht = yamlDaten[GELOESCHT] as Boolean

                    return setOf(Karte(id, localizations, gesehen, geloescht))
                }

                // Fall 3: ungültige Struktur
                else -> throw IllegalArgumentException("Ungültige Kartenstruktur.")
            }
        }
    }
}

