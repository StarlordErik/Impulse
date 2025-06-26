package de.seleri.spielelemente

const val SPIELE: String = "Spiele"

/**
 * Spiel mit Namen und dazugehörigen Kategorien
 *
 * @property id eindeutige ID des Spiels
 * @property localizations Map mit den Übersetzungen des Spielnamens für verschiedene Sprachen
 * @property originaleElemente zwei Mengen mit den originalen Kategorien und denen, die vom Nutzer entfernt wurden
 * @property hinzugefuegteElemente Menge der vom Nutzer hinzugefügten Kategorien zum Spiel
 */
data class Spiel(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    override val originaleElemente: Map<String, MutableSet<Kategorie>>,
    override val hinzugefuegteElemente: MutableSet<Kategorie>
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
    fun getKategorien(): Set<Kategorie> = getAlleElemente()

    /**
     * Gibt alle Kategorien des Spiels zurück ohne die "davon entfernten" Kategorien.
     *
     * @return (originale Kategorien - davon entfernten Kategorien) + hinzugefügte Kategorien
     */
    fun getAktuelleKategorien(): Set<Kategorie> = getAlleAktuellenElemente()

    /**
     * Gibt alle Karten aller Kategorien des Spiels zurück und ignoriert die davon entfernten.
     *
     * @return alleKarten(originale Kategorien) + alleKarten(hinzugefügte Kategorien)
     */
    override fun getKarten(): Set<Karte> = getKategorien().flatMap { it.getKarten() }.toSet()

    /**
     * Gibt alle Karten aller Kategorien des Spiels zurück ohne die "davon entfernten" Kategorien.
     *
     * @return aktuelleKarten(originale Kategorien - davon entfernten Kategorien) +
     * aktuelleKarten(hinzugefügte Kategorien)
     */
    override fun getAktuelleKarten(): Set<Karte> =
        getAktuelleKategorien().flatMap { it.getAktuelleKarten() }.toSet()

    /**
     * Gibt alle noch nicht gesehenen Karten des Spiels zurück.
     *
     * @return noch nicht gesehene Karten
     */
    override fun getUngeseheneKarten(): Set<Karte> {
        return geseheneKartenRausfiltern(getAktuelleKarten())
    }

    /**
     * Setzt alle Karten des Spiels auf "ungesehen".
     */
    override fun setKartenUngesehen() {
        setKartenUngesehen(getAktuelleKarten())
    }

    /**
     * Fügt neue Kategorien zum Spiel hinzu.
     * Dafür werden sie der der Menge der hinzugefügten Kategorien hinzugefügt und für den Fall,
     * dass sie schon in den originalen Kategorien enthalten waren und entfernt wurden, werden sie rehabilitiert.
     *
     * @param kategorien Kategorien, die zum Spiel hinzugefügt werden sollen
     */
    fun kategorienHinzufuegen(kategorien: Collection<Kategorie>) = elementeHinzufuegen(kategorien)

    /**
     * Entfernt eine Kategorie aus dem Spiel.
     * Dafür wird sie der Menge der davon entfernten Kategorien hinzugefügt
     * und/oder aus den hinzugefügten Kategorien gelöscht.
     *
     * @param zuEntfernendeKategorie Kategorie, die aus dem Spiel entfernt werden soll
     */
    fun kategorieEntfernen(zuEntfernendeKategorie: Kategorie) = elementEntfernen(zuEntfernendeKategorie)

    companion object {

        /**
         * Erstellt ein Spiel anhand von Benutzereingaben oder Skriptdaten.
         *
         * @param id neue, noch nicht vergebene ID
         * @param sprache Sprache des Spielnamens
         * @param name Name
         * @param originaleKategorien Collection der ursprünglichen Kategorien
         * @return neues Spiel ohne entfernte oder hinzugefügte Kategorien
         */
        fun fromEingabe(
            id: Int, sprache: Sprachen, name: String, originaleKategorien: Collection<Kategorie>
        ): Spiel = fromEingabe(id, sprache, name, originaleKategorien, ::Spiel)

        /**
         * Erstellt eine Menge von Spielen aus einer von SnakeYaml deserialisierten YAML-Datei.
         *
         * Wieso gibt es nur fromYaml und nicht fromYamlListe? Beides würde nur
         * den Parameter Map<String, Any> bekommen; Man müsste also definitiv die Daten kennen,
         * die man reintut - so differenziert das die Funktion ganz von allein.
         *
         * @param data YAML-Daten eines oder mehrerer Spiele
         * @param moeglicheKategorien Collection aller Kategorien, aus denen das Spiel bestehen **könnte** -
         * im Zweifel einfach alle möglichen Kategorien
         * @return Menge von Spielen mit ausgelesenen Attributwerten
         */
        fun fromYaml(data: Map<String, Any>, moeglicheKategorien: Collection<Kategorie>): Set<Spiel> {
            return when {

                // Fall 1: mehrere Spiele
                SPIELE in data -> {
                    fromYamlListe(SPIELE, data) { fromYaml(it, moeglicheKategorien) }
                }

                // Fall 2: einzelnes Spiel
                "$ORIGINALE$KATEGORIEN" in data && "$HINZUGEFUEGTE$KATEGORIEN$BINDESTRICH_IDS" in data -> {
                    setOf(fromYaml(data, moeglicheKategorien, ::Spiel))
                }

                // Fall 3: ungültige Struktur
                else -> throw IllegalArgumentException("Ungültige Spielstruktur.")
            }
        }
    }
}
