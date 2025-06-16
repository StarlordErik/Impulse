package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

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
) : LokalisierbaresSpielelement(id, localizations) {

    /**
     * Wandelt die Karte in einen YAML-kompatiblen String um.
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
         * Erstellt **eine** Karte aus einem YAML-Datensatz.
         *
         * @param data YAML-Datensatz **einer** Karte
         * @return neue Karte mit ausgelesenen Attributswerten
         */
        fun fromYaml(data: Map<String, Any>): Karte {

            // lässt id und localizations in der Superklasse verarbeiten
            val (id, localizations) = LokalisierbaresSpielelement.fromYaml(data)

            val gesehen = data[GESEHEN] as Boolean
            val geloescht = data[GELOESCHT] as Boolean
            return Karte(id, localizations, gesehen, geloescht)
        }

        /**
         * Erstellt **eine Liste mehrerer** Karten aus einem Karten-Abschnitt von YAML-Datensätzen.
         *
         * @param yamlInput Karten-Abschnitt von YAML-Datensätzen **mehrerer** Karte
         * @return Liste an neuen Karten mit ausgelesenen Attributswerten
         */
        fun fromYaml(yamlInput: String): List<Karte> {

            // Struktur wird mithilfe der SnakeYaml-Library geparset
            val data = (Yaml().load(yamlInput) as List<Map<String, Any>>)

            return data.map { fromYaml(it) }
        }
    }
}
