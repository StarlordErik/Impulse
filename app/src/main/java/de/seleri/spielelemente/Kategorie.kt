package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

/**
 * Kategorie mit Namen und dazugehärigen Karten
 *
 * @property id eindeutige ID der Karte
 * @property localizations Map mit den Übersetzungen des Kategorienamens für verschiedene Sprachen
 * @property originaleElemente zwei Listen mit IDs der originalen Karten und derer, die vom Nutzer entfernt wurden
 * @property hinzugefuegteElemente Liste der vom Nutzer hinzugefügten Karten zur Kategorie
 */
data class Kategorie(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    override val originaleElemente: MutableMap<String, List<Karte>>,
    override var hinzugefuegteElemente: List<Karte>
) : SammlungAnSpielelementen<Karte>(id, localizations, originaleElemente, hinzugefuegteElemente) {

    /**
     * Konvertiert die Kategorie ins YAML-Format.
     *
     * @return String mit allen Eigenschaften der Kategorie im YAML-Format
     */
    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        output.append(originaleUndHinzugefuegteElementeToYaml(KARTEN))
        return output.toString()
    }

    /**
     * Gibt alle Karten der Kategorie zurück und ignoriert die davon entfernten.
     *
     * @return originale Karten + hinzugefügte Kartem
     */
    fun getAlleKarten(): List<Karte> = getAlleElemente()

    /**
     * Gibt alle Karten der Kategorie zurück ohne die "davon entfernten" Karten.
     *
     * @return (originale Karten - davon entfernten Karten) + hinzugefügte Karten
     */
    fun getAlleAktuellenKarten(): List<Karte> = getAlleAktuellenElemente()

    /**
     * Fügt neue Karten zur Kategorie hinzu.
     * Dafür werden sie der der Liste der hinzugefügten Karten einfach hinzugefügt und für den Fall,
     * dass sie schon in den originalen Karten enthalten waren und entfernt wurden, werden sie rehabilitiert.
     *
     * @param karten Karten, die zur Kategorie hinzugefügt werden sollen
     */
    fun kartenHinzufuegen(karten: List<Karte>) = elementeHinzufuegen(karten)

    /**
     * Entfernt Karten aus der Kategorie.
     * Dafür werden sie der Liste der davon entfernten Karten einfach hinzugefügt
     * und/oder aus den hinzugefügten Karten gelöscht.
     *
     * @param karten Karten, die aus der Kategorie entfernt werden sollen
     */
    fun kartenEntfernen(karten: List<Karte>) = elementeEntfernen(karten)

    companion object {

        /**
         * Erstellt eine Kategorie anhand von Benutzereingaben oder Skriptdaten.
         *
         * @param id neue, noch nicht vergebene ID
         * @param sprache Sprache des Namens
         * @param name Name
         * @param originaleKarten Liste der original enthaltenen Karten
         * @return neue Kategorie ohne entfernte oder hinzugefügte Karten
         */
        fun fromEingabe(
            id: Int, sprache: Sprachen, name: String, originaleKarten: List<Karte>
        ): Kategorie = fromEingabe(id, sprache, name, originaleKarten, ::Kategorie)

        /**
         * Erstellt **eine** Kategorie aus einem YAML-Datensatz.
         *
         * @param data YAML-Datensatz **einer** Kategorie
         * @param moeglicheKarten Liste aller Karten, aus denen die Kategorie bestehen **könnte** -
         * im Zweifel einfach alle möglichen Karten
         * @return neue Kategorie mit ausgelesenen Attributswerten
         */
        fun fromYaml(data: Map<String, Any>, moeglicheKarten: List<Karte>): Kategorie {
            return fromYaml(data, moeglicheKarten, ::Kategorie)
        }

        /**
         * Erstellt **eine Liste mehrerer** Kategorien aus einem Kategorien-Abschnitt von YAML-Datensätzen.
         *
         * @param yamlInput Kategorien-Abschnitt von YAML-Datensätzen **mehrerer** Kategorien
         * @return Liste an neuen Kategorien mit ausgelesenen Attributswerten
         */
        fun fromYaml(yamlInput: String, moeglicheKarten: List<Karte>): List<Kategorie> {
            val daten = (Yaml().load(yamlInput) as List<Map<String, Any>>)
            return daten.map { fromYaml(it, moeglicheKarten) }
        }
    }
}
