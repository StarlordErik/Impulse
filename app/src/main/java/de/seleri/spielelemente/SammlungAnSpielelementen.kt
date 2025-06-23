package de.seleri.spielelemente

/**
 * Abstrakte Basisklasse für alle Spielelemente, die eine Sammlung von anderen Elementen beeinhalten
 *
 * @property id eindeutige ID des Elements
 * @property localizations Map mit den Übersetzungen des Namens für verschiedene Sprachen
 * @property originaleElemente zwei Listen mit IDs der originalen Elementen und derer, die vom Nutzer entfernt wurden
 * @property hinzugefuegteElemente Liste der vom Nutzer hinzugefügten Elemente zur Sammlung
 */
abstract class SammlungAnSpielelementen<T: LokalisierbaresSpielelement>(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    open val originaleElemente: MutableMap<String, List<T>>,
    open var hinzugefuegteElemente: List<T>
): LokalisierbaresSpielelement(id, localizations) {

    /**
     * Konvertiert die ID mit Namen ins YAML-Format und beginnt damit den YAML-Datensatz einer Sammlung.
     *
     * @return nur ID und Name als YAML-String
     */
    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        output.append(localizationsToYaml(NAME))
        return output.toString()
    }

    /**
     * Konvertiert die originalen, davon entfernten und hinzugefügten Elemente ins YAML-Format.
     *
     * @param elemente Bezeichnung der Elemente (/Bestandteile) der Sammlung
     * @return "originale-T: IDS: [.,.,.] davon_entfernt: [.,.,.] \n hinzugefuegte-T: [.,.,.]" als YAML-String
     */
    protected fun originaleUndHinzugefuegteElementeToYaml(elemente: String): String {
        val output = StringBuilder()

        output.append(attributToYamlZeile(2, "$ORIGINALE$elemente", originaleElemente))
        output.append(
            attributToYamlZeile(
                2, "$HINZUGEFUEGTE$elemente$BINDESTRICH_IDS", hinzugefuegteElemente
            )
        )
        return output.toString()
    }

    /**
     * Gibt alle Elemente der Sammlung zurück und ignoriert die davon entfernten.
     *
     * @return originale Elemente + hinzugefügte Elemente
     */
    protected fun getAlleElemente(): List<T> {
        val alleElemente = mutableListOf<T>()
        alleElemente.addAll(originaleElemente[IDS]!!)
        alleElemente.addAll(hinzugefuegteElemente)
        return alleElemente
    }

    /**
     * Gibt alle Karten der Sammlung zurück und ignoriert die davon entfernten.
     *
     * @return originale Karten + hinzugefügte Kartem
     */
    abstract fun getAlleKarten() : List<Karte>

    /**
     * Gibt alle Elemente der Sammlung zurück ohne die "davon entfernten" Elemente.
     *
     * @return (originale Elemente - davon entfernte Elemente) + hinzugefügte Elemente
     */
    protected fun getAlleAktuellenElemente(): List<T> {
        val aktuelleElemente = getAlleElemente().toMutableList()
        aktuelleElemente.removeAll(originaleElemente[DAVON_ENTFERNT]!!)
        return aktuelleElemente
    }

    /**
     * Gibt alle Karten der Sammlung zurück ohne die "davon entfernten" Karten.
     *
     * @return (originale Karten - davon entfernten Karten) + hinzugefügte Karten
     */
    abstract fun getAlleAktuellenKarten() : List<Karte>

    /**
     * Gibt alle Karten der Sammlung zurück, die noch nicht gesehen wurden.
     *
     * @param aktuelleKarten Liste aller aktuellen Karten der Sammlung
     * @return Liste an Karten, die noch nicht gesehen wurdenq
     */
    protected fun geseheneKartenRausfiltern(aktuelleKarten: List<Karte>): List<Karte> {
        return aktuelleKarten.filter { !it.gesehen }
    }

    /**
     * Gibt alle noch nicht gesehenen Karten der Sammlung zurück.
     *
     * @return noch nicht gesehene Karten
     */
    abstract fun getAlleUngesehenenKarten(): List<Karte>

    /**
     * Setzt alle Karten der Sammlung auf "ungesehen".
     *
     * @param aktuelleKarten Liste aller aktuellen Karten der Sammlung
     */
    protected fun setKartenUngesehen(aktuelleKarten: List<Karte>) {
        aktuelleKarten.forEach { it.gesehen = false }
    }

    /**
     * Setzt alle Karten der Sammlung auf "ungesehen".
     */
    abstract fun setKartenUngesehen()

    /**
     * Fügt neue Elemente zur Sammlung hinzu.
     * Dafür werden sie der der Liste der hinzugefügten Elemente einfach hinzugefügt und für den Fall,
     * dass es schon in den originalen Elementen enthalten war und entfernt wurde, wird es rehabilitiert.
     *
     * @param neueElemente Elemente, die zur Sammlung hinzugefügt werden sollen
     */
    protected fun elementeHinzufuegen(neueElemente: List<T>) {

        // rehabilitert die Elemente, die vorher entfernt wurden
        neueElemente.forEach { originaleElemente[DAVON_ENTFERNT]!!.minus(it) }

        val neuHinzugefuegt: MutableSet<T> =
            hinzugefuegteElemente.toMutableSet() // keine Duplikate!

        // fügt es nur hinzu, wenn es nicht auch Teil der originalen (und hinzugefügten) Elemente ist
        neuHinzugefuegt.addAll(neueElemente.filter { !getAlleElemente().contains(it) })

        hinzugefuegteElemente = neuHinzugefuegt.toList()
    }

    /**
     * Entfernt ein Element aus der Sammlung.
     * Dafür wird es der Liste der davon entfernten Elemente einfach hinzugefügt
     * und/oder aus den hinzugefügten Elementen gelöscht.
     *
     * @param zuEntfernendesElement Element, das aus der Sammlung entfernt werden soll
     */
    protected fun elementEntfernen(zuEntfernendesElement: T) {
        originaleElemente[DAVON_ENTFERNT] =
            originaleElemente[DAVON_ENTFERNT]!!.plus(zuEntfernendesElement)
        hinzugefuegteElemente = hinzugefuegteElemente.minus(zuEntfernendesElement)
    }

    companion object {
        /**
         * Erstellt eine Sammlung anhand von Benutzereingaben oder Skriptdaten.
         *
         * @param id neue, noch nicht vergebene ID
         * @param sprache Sprache des Namens
         * @param name Name
         * @param originaleElemente Liste der original enthaltenen Elemente
         * @param constructor Konstruktor der Sammlung vom Typ T
         * @return neue Sammlung vom Typ T ohne entfernte oder hinzugefügte Elemente
         */
        @JvmStatic // damit die Methode protected sein kann
        protected fun <T: SammlungAnSpielelementen<E>, E: LokalisierbaresSpielelement> fromEingabe(
            id: Int,
            sprache: Sprachen,
            name: String,
            originaleElemente: List<E>,
            constructor: (Int, MutableMap<Sprachen, String>, MutableMap<String, List<E>>, List<E>) -> T
        ): T {

            // lässt id, sprache und name in der Superklasse verarbeiten
            val (id, localizations) = fromEingabe(id, sprache, name)

            val originaleElementeMitNullEntfernten = mutableMapOf<String, List<E>>(
                IDS to originaleElemente, DAVON_ENTFERNT to emptyList()
            )
            return constructor(id, localizations, originaleElementeMitNullEntfernten, emptyList())
        }

        /**
         * Erstellt eine Sammlung aus einem YAML-Datensatz.
         *
         * @param data YAML-Datensatz einer Sammlung
         * @param moeglicheElemente Liste aller Elemente vom Typ T, aus denen die Sammlung bestehen **könnte** -
         * im Zweifel einfach alle möglichen Elemente
         * @param constructor Konstruktor der Sammlung vom Typ T
         * @return neue Sammlung vom Typ T mit ausgelesenen Attributswerten
         */
        @Suppress("UNCHECKED_CAST")
        @JvmStatic // damit die Methode protected sein kann
        protected fun <T: SammlungAnSpielelementen<E>, E: LokalisierbaresSpielelement> fromYaml(
            data: Map<String, Any>,
            moeglicheElemente: List<E>,
            constructor: (Int, MutableMap<Sprachen, String>, MutableMap<String, List<E>>, List<E>) -> T
        ): T {

            // lässt id und localizations in der Superklasse verarbeiten
            val (id, localizations) = fromYaml(data)


            val originaleElementeIDs = ((data["$ORIGINALE$KARTEN"]
                ?: data["$ORIGINALE$KATEGORIEN"]) as Map<String, List<Int>>)
            val hinzugefuegteIDs = (data["$HINZUGEFUEGTE$KARTEN$BINDESTRICH_IDS"]
                ?: data["$HINZUGEFUEGTE$KATEGORIEN$BINDESTRICH_IDS"]) as List<Int>

            // findet alle Elemente per ID aus der Liste aller möglichen Elemente
            val originaleElemente = findeElemente(originaleElementeIDs[IDS]!!, moeglicheElemente)
            val entfernteElemente =
                findeElemente(originaleElementeIDs[DAVON_ENTFERNT]!!, moeglicheElemente)
            val hinzugefuegteElemente = findeElemente(hinzugefuegteIDs, moeglicheElemente)

            val originaleUndDavonEntfernteElemente = mutableMapOf<String, List<E>>(
                IDS to originaleElemente, DAVON_ENTFERNT to entfernteElemente
            )

            return constructor(
                id, localizations, originaleUndDavonEntfernteElemente, hinzugefuegteElemente
            )
        }
    }
}
