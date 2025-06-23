package de.seleri.spielelemente

const val KATEGORIEN: String = "Kategorien"

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
    override fun getAlleKarten(): List<Karte> = getAlleElemente()

    /**
     * Gibt alle Karten der Kategorie zurück ohne die "davon entfernten" Karten.
     *
     * @return (originale Karten - davon entfernten Karten) + hinzugefügte Karten
     */
    override fun getAlleAktuellenKarten(): List<Karte> = getAlleAktuellenElemente()

    /**
     * Gibt alle noch nicht gesehenen Karten der Kategorie zurück.
     *
     * @return noch nicht gesehene Karten
     */
    override fun getAlleUngesehenenKarten(): List<Karte> {
        return geseheneKartenRausfiltern(getAlleAktuellenKarten())
    }

    /**
     * Setzt alle Karten der Kategorie auf "ungesehen".
     */
    override fun setKartenUngesehen() {
        setKartenUngesehen(getAlleAktuellenKarten())
    }

    /**
     * Fügt neue Karten zur Kategorie hinzu.
     * Dafür werden sie der der Liste der hinzugefügten Karten einfach hinzugefügt und für den Fall,
     * dass sie schon in den originalen Karten enthalten waren und entfernt wurden, werden sie rehabilitiert.
     *
     * @param karten Karten, die zur Kategorie hinzugefügt werden sollen
     */
    fun kartenHinzufuegen(karten: List<Karte>) = elementeHinzufuegen(karten)
    /**
     * Entfernt eine Karte aus der Kategorie.
     * Dafür wird sie der Liste der davon entfernten Karten einfach hinzugefügt
     * und/oder aus den hinzugefügten Karten gelöscht.
     *
     * @param zuEntfernendeKarte Karte, die aus der Kategorie entfernt werden soll
     */
    fun karteEntfernen(zuEntfernendeKarte: Karte) = elementEntfernen(zuEntfernendeKarte)

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
         * Erstellt eine Liste von Kategorien aus einer von SnakeYaml deserialisierten YAML-Datei.
         *
         * @param data YAML-Daten einer Kategorie oder einer Liste von Kategorien
         * @param moeglicheKarten Liste aller Karten, aus denen die Kategorie bestehen **könnte** -
         * im Zweifel einfach alle möglichen Karten
         * @return Liste von Kategorien mit ausgelesenen Attributwerten
         */
        fun fromYaml(data: Map<String, Any>, moeglicheKarten: List<Karte>): List<Kategorie> {
            return when {

                // Fall 1: mehrere Kategorien
                KATEGORIEN in data -> {
                    fromYamlListe(KATEGORIEN, data) { fromYaml(it, moeglicheKarten) }
                }

                // Fall 2: einzelne Kategorie
                "$ORIGINALE$KARTEN" in data && "$HINZUGEFUEGTE$KARTEN$BINDESTRICH_IDS" in data -> {
                    listOf(fromYaml(data, moeglicheKarten, ::Kategorie))
                }

                // Fall 3: ungültige Struktur
                else -> throw IllegalArgumentException("Ungültige Kategoriestruktur.")
            }
        }
    }
}
