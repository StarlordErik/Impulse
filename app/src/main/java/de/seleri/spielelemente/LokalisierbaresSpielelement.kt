package de.seleri.spielelemente

import kotlin.collections.first
import kotlin.collections.set

const val ID: String = "ID"

/**
 * Abstrakte Basisklasse für alle Spielelemente mit ID und den localized Kartentexten/Namen
 *
 * @property id eindeutige ID des Elements
 * @property localizations Map mit den Übersetzungen des Kartentextes/Namens für verschiedene Sprachen
 */
abstract class LokalisierbaresSpielelement(
    open val id: Int, open val localizations: MutableMap<Sprachen, String>
) {

    /**
     * Konvertiert die ID ins YAML-Format und beginnt damit den YAML-Datensatz eines Elements.
     *
     * @return nur die ID als YAML-String
     */
    open fun toYaml(): String {
        val output = StringBuilder()
        output.append(attributToYamlZeile(1, "- $ID", id))
        return output.toString()
    }

    /**
     * Konvertiert die localizations ins YAML-Format.
     *
     * @param bezeichnung Kartentext oder Name einer Sammlung (Kategorie, Spiel)
     * @return "Bezeichnung: localizedTexts" als YAML-String
     */
    protected fun localizationsToYaml(bezeichnung: String): String {
        return attributToYamlZeile(2, bezeichnung, localizations)
    }

    /**
     * Setzt die Übersetzung für eine Sprache, sofern diese nicht OG (Original) ist.
     *
     * @param sprache Sprache, in der die Übersetzung gespeichert werden soll
     * @param bezeichnung Übersetzung des Textes / der Name
     */
    fun setUebersetzung(sprache: Sprachen, bezeichnung: String) {
        if (sprache != Sprachen.OG) localizations[sprache] = bezeichnung
    }

    companion object {

        /**
         * Erstellt ein neues Spielelement anhand von Benutzereingaben oder Skriptdaten.
         *
         * @param id neue, noch nicht vergebene ID
         * @param sprache Sprache der Bezeichnung
         * @param bezeichnung Kartentext / Name
         * @return Tupel (id, localizations) aka abstraktes Objekt von LokalisierbaresElement
         */
        @JvmStatic // damit die Methode protected sein kann
        protected fun fromEingabe(
            id: Int, sprache: Sprachen, bezeichnung: String
        ): Pair<Int, MutableMap<Sprachen, String>> {


            // für alle Sprachen:
            // speichert die Bezeichnung sowohl in der entsprechenden Sprache als auch in OG, oder setzt ""
            val localizations =
                Sprachen.entries.associateWith { if (it == sprache || it == Sprachen.OG) bezeichnung else "" }
                    .toMutableMap()

            return id to localizations
        }

        /**
         * Erstellt ein Spielelement aus einem YAML-Datensatz.
         *
         * @param data YAML-Datensatz eines Elements
         * @return Tupel (id, localizations) aka abstraktes Objekt von LokalisierbaresElement
         */
        @JvmStatic // damit die Methode protected sein kann
        protected fun fromYaml(
            data: Map<String, Any>
        ): Pair<Int, MutableMap<Sprachen, String>> {

            require(ID in data && (TEXT in data || NAME in data)) {
                "Ungültige Elementstruktur."
            }

            val id = data[ID] as Int

            @Suppress("UNCHECKED_CAST") val yamlLocalizations = ((data[TEXT]
                ?: data[NAME]) as Map<String, String>).mapKeys { Sprachen.valueOf(it.key) }

            // für alle Sprachen: speichert die ausgelesene Übersetzung oder setzt ""
            val localizations =
                Sprachen.entries.associateWith { if (it in yamlLocalizations) yamlLocalizations[it]!! else "" }
                    .toMutableMap()

            return id to localizations
        }

        /**
         * Verarbeitet eine Liste von Element-Daten in einer YAML-Struktur.
         *
         * @param T jede Art von Element (Karte, Kategorie, Spiel)
         * @param element Element-Bezeichnung in der YAML-Datenstruktur
         * @param data YAML-Datenstruktur
         * @param converter rekursiver Aufruf für das einzelne Element
         * @return Eine Liste von [T].
         */
        @JvmStatic // damit die Methode protected sein kann
        protected fun <T: LokalisierbaresSpielelement> fromYamlListe(
            element: String, data: Map<String, Any>, converter: (Map<String, Any>) -> Collection<T>
        ): Set<T> {
            // extrahiert die Liste möglicher Element-Daten aus der YAML-Struktur
            val listeAnElementenImYamlformat = data[element] as List<*>

            // macht aus List<*> --> List<Map<String, Any>>
            val listeAnMapsVonElementenImYamlformat =
                listeAnElementenImYamlformat.filterIsInstance<Map<String, Any>>()

            // wandelt jede Element-Map in ein Element-Objekt um
            val elementListe = listeAnMapsVonElementenImYamlformat.map {
                converter(it) // zu Fall 2
                    .first() // es wird nur ein Objekt ausgegeben, aber als Liste
            }

            return elementListe.toSet()
        }

        /**
         * Findet ein Spielelement anhand einer Texteingabe.
         *
         * @param bezeichnung Kartentext oder Sammlungs-Name einer beliebigen Sprache
         * @param findeIn Collection der zu durchsuchenden Elemente
         * @return gefundenes Element oder null (um im nächsten Schritt das Element erstellen zu können)
         */
        internal fun <T: LokalisierbaresSpielelement> findeElement(
            bezeichnung: String, findeIn: Collection<T>
        ): T? {
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
        internal fun <T: LokalisierbaresSpielelement> findeElement(
            id: Int, findeIn: Collection<T>
        ): T = findeIn.find { it.id == id } ?: error("Element mit ID $id nicht gefunden")

        /**
         * Findet mehrere Spielelemente anhand einer Collection von IDs.
         *
         * @param ids Collection gesuchter IDs
         * @param findeIn Collection der zu durchsuchenden Elemente
         * @return Menge gefundener Elemente
         */
        internal fun <T: LokalisierbaresSpielelement> findeElemente(
            ids: Collection<Int>, findeIn: Collection<T>
        ): Set<T> {
            return ids.mapTo(mutableSetOf()) { id -> findeElement(id, findeIn) }
        }
    }
}
