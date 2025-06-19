package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

/**
 * Spiel mit Namen und dazugehörigen Kategorien
 *
 * @property id eindeutige ID des Spiels
 * @property localizations Map mit den Übersetzungen des Spielnamens für verschiedene Sprachen
 * @property originaleElemente zwei Listen mit IDs der originalen Kategorien und derer, die vom Nutzer entfernt wurden
 * @property hinzugefuegteElemente Liste der vom Nutzer hinzugefügten Kategorien zum Spiel
 */
data class Spiel(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    override val originaleElemente: MutableMap<String, List<Kategorie>>,
    override var hinzugefuegteElemente: List<Kategorie>
) : SammlungAnSpielelementen<Kategorie>(
    id, localizations, originaleElemente, hinzugefuegteElemente
) {

    /**
     * Konvertiert das Spiel ins YAML-Format.
     *
     * @return String mit allen Eigenschaften des Spiels im YAML-Format
     */
    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        output.append(originaleUndHinzugefuegteElementeToYaml(KATEGORIEN))
        return output.toString()
    }

    /**
     * Gibt alle Kategorien des Spiels zurück und ignoriert die davon entfernten.
     *
     * @return originale Kategorien + hinzugefügte Kategorien
     */
    fun getAlleKategorien(): List<Kategorie> = getAlleElemente()

    /**
     * Gibt alle Kategorien des Spiels zurück ohne die "davon entfernten" Kategorien.
     *
     * @return (originale Kategorien - davon entfernten Kategorien) + hinzugefügte Kategorien
     */
    fun getAlleAktuellenKategorien(): List<Kategorie> = getAlleAktuellenElemente()

    /**
     * Gibt alle Karten aller Kategorien des Spiels zurück und ignoriert die davon entfernten.
     *
     * @return alleKarten(originale Kategorien) + alleKarten(hinzugefügte Kategorien)
     */
    fun getAlleKarten(): List<Karte> = getAlleKategorien().flatMap { it.getAlleKarten() }.distinct()

    /**
     * Gibt alle Karten aller Kategorien des Spiels zurück ohne die "davon entfernten" Kategorien.
     *
     * @return aktuelleKarten(originale Kategorien - davon entfernten Kategorien)
     * + aktuelleKarten(hinzugefügte Kategorien)
     */
    fun getAlleAktuellenKarten(): List<Karte> =
        getAlleAktuellenKategorien().flatMap { it.getAlleAktuellenKarten() }.distinct()

    /**
     * Fügt neue Kategorien zum Spiel hinzu.
     * Dafür werden sie der der Liste der hinzugefügten Kategorien einfach hinzugefügt und für den Fall,
     * dass sie schon in den originalen Kategorien enthalten waren und entfernt wurden, werden sie rehabilitiert.
     *
     * @param kategorien Kategorien, die zum Spiel hinzugefügt werden sollen
     */
    fun kategorienHinzufuegen(kategorien: List<Kategorie>) = elementeHinzufuegen(kategorien)

    /**
     * Entfernt Kategorien aus dem Spiel.
     * Dafür werden sie der Liste der davon entfernten Kategorien einfach hinzugefügt
     * und/oder aus den hinzugefügten Kategorien gelöscht.
     *
     * @param kategorien Kategorien, die aus dem Spiel entfernt werden sollen
     */
    fun kategorienEntfernen(kategorien: List<Kategorie>) = elementeEntfernen(kategorien)

    companion object {

        /**
         * Erstellt ein Spiel anhand von Benutzereingaben oder Skriptdaten.
         *
         * @param id neue, noch nicht vergebene ID
         * @param sprache Sprache des Spielnamens
         * @param name Name
         * @param originaleKategorien Liste der ursprünglichen Kategorien
         * @return neues Spiel: ohne entfernte oder hinzugefügte Kategorien
         */
        fun fromEingabe(
            id: Int, sprache: Sprachen, name: String, originaleKategorien: List<Kategorie>
        ): Spiel = fromEingabe(id, sprache, name, originaleKategorien, ::Spiel)

        /**
         * Erstellt **ein** Spiel aus einem YAML-Datensatz.
         *
         * @param data YAML-Datensatz **eines** Spiels
         * @param moeglicheKategorien Liste aller Kategorien, aus denen das Spiel bestehen **könnte** -
         * im Zweifel einfach alle möglichen Kategorien
         * @return neues Spiel mit ausgelesenen Attributswerten
         */
        fun fromYaml(data: Map<String, Any>, moeglicheKategorien: List<Kategorie>): Spiel {
            return fromYaml(data, moeglicheKategorien, ::Spiel)
        }

        /**
         * Erstellt **eine Liste mehrerer** Spiele aus einem Spiel-Abschnitt von YAML-Datensätzen.
         *
         * @param yamlInput Spiel-Abschnitt von YAML-Datensätzen **mehrerer** Spiele
         * @return Liste an neuen Spielen mit ausgelesenen Attributswerten
         */
        fun fromYaml(yamlInput: String, moeglicheKategorien: List<Kategorie>): List<Spiel> {
            val daten = (Yaml().load(yamlInput) as List<Map<String, Any>>)
            return daten.map { fromYaml(it, moeglicheKategorien) }
        }
    }
}
