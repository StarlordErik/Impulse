package de.seleri.backend

const val KATEGORIEN: String = "Kategorien"

/**
 * Kategorie mit Namen und dazugehärigen Karten
 *
 * @property id eindeutige ID der Karte
 * @property localizations Map mit den Übersetzungen des Kategorienamens für verschiedene Sprachen
 * @property originaleElemente zwei Mengen mit den originalen Karten und denen, die vom Nutzer entfernt wurden
 * @property hinzugefuegteElemente Menge der vom Nutzer hinzugefügten Karten zur Kategorie
 */
class Kategorie(
    override var id: Int,
    override val localizations: MutableMap<Sprachen, String?>,
    override val originaleElemente: Map<String, MutableSet<Karte>>,
    override val hinzugefuegteElemente: MutableSet<Karte>
): SammlungAnSpielelementen<Karte>(id, localizations, originaleElemente, hinzugefuegteElemente) {

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
    override fun getKarten(): Set<Karte> = getAlleElemente()

    /**
     * Gibt alle Karten der Kategorie zurück ohne die "davon entfernten" Karten.
     *
     * @return (originale Karten - davon entfernten Karten) + hinzugefügte Karten
     */
    override fun getAktuelleKarten(): Set<Karte> = getAlleAktuellenElemente()

    /**
     * Gibt alle noch nicht gesehenen Karten der Kategorie zurück.
     *
     * @return noch nicht gesehene Karten
     */
    override fun getUngeseheneKarten(): Set<Karte> {
        return geseheneKartenRausfiltern(getAktuelleKarten())
    }

    /**
     * Setzt alle Karten der Kategorie auf "ungesehen".
     */
    override fun setKartenUngesehen() {
        setKartenUngesehen(getAktuelleKarten())
    }

    /**
     * Fügt neue Karten zur Kategorie hinzu.
     * Dafür werden sie der der Menge der hinzugefügten Karten hinzugefügt und für den Fall,
     * dass sie schon in den originalen Karten enthalten waren und entfernt wurden, werden sie rehabilitiert.
     *
     * @param karten Karten, die zur Kategorie hinzugefügt werden sollen
     */
    fun kartenHinzufuegen(karten: Collection<Karte>) = elementeHinzufuegen(karten)

    /**
     * Entfernt eine Karte aus der Kategorie.
     * Dafür wird sie der Menge der davon entfernten Karten hinzugefügt
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
         * @param originaleKarten Collection der original enthaltenen Karten
         * @return neue Kategorie ohne entfernte oder hinzugefügte Karten
         */
        fun fromEingabe(
            id: Int, sprache: Sprachen, name: String, originaleKarten: Collection<Karte>
        ): Kategorie = fromEingabe(id, sprache, name, originaleKarten, ::Kategorie)

        /**
         * Erstellt eine Menge von Kategorien aus einer von SnakeYaml deserialisierten YAML-Datei.
         *
         * Wieso gibt es nur fromYaml und nicht fromYamlListe? Beides würde nur
         * den Parameter Map<String, Any> bekommen; Man müsste also definitiv die Daten kennen,
         * die man reintut - so differenziert das die Funktion ganz von allein.
         *
         * @param yamlDaten YAML-Daten einer Kategorie oder mehrerer Kategorien
         * @param moeglicheKarten Collection aller Karten, aus denen die Kategorie bestehen **könnte** -
         * im Zweifel einfach alle möglichen Karten
         * @return Menge von Kategorien mit ausgelesenen Attributwerten
         */
        fun fromYaml(yamlDaten: Map<String, Any>, moeglicheKarten: Collection<Karte>): Set<Kategorie> {
            return when {

                // Fall 1: mehrere Kategorien
                KATEGORIEN in yamlDaten -> {
                    fromYamlListe(KATEGORIEN, yamlDaten) { fromYaml(it, moeglicheKarten) }
                }

                // Fall 2: einzelne Kategorie
                "$ORIGINALE$KARTEN" in yamlDaten && "$HINZUGEFUEGTE$KARTEN$BINDESTRICH_IDS" in yamlDaten -> {
                    setOf(fromYaml(yamlDaten, moeglicheKarten, ::Kategorie))
                }

                // Fall 3: ungültige Struktur
                else -> throw IllegalArgumentException("Ungültige Kategoriestruktur.")
            }
        }
    }
}
