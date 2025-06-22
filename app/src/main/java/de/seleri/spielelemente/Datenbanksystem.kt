package de.seleri.spielelemente

import android.content.Context
import de.seleri.impulse.R
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.nio.file.Paths
import kotlin.collections.set

const val DATENBANK_NAME = "datenbank"
const val DATENBANK_DATEIFORMAT = ".yml"
const val DATENBANK_DATEI = "$DATENBANK_NAME$DATENBANK_DATEIFORMAT"
const val DATENBANK_PFAD = "app/src/main/res/raw/$DATENBANK_DATEI"

/**
 * Das `Datenbanksystem` verwaltet eine YAML-basierte Datenbank für `Karte`n, `Kategorie`n und `Spiel`e.
 * Diese Datenbank wird beim Initialisieren geparst und im Speicher als Objekte dargestellt.
 * Änderungen an den Objekten werden in die YAML-Datei zurückgeschrieben.
 *
 * @param datenbank Die YAML-Datei, die als Datenbank dient.
 * @property karten Liste aller ausgelesenen Karten.
 * @property kategorien Liste aller ausgelesenen Kategorien.
 * @property spiele Liste aller ausgelesenen Spiele.
 * @constructor Initialisiert das Datenbanksystem mit der gegebenen YAML-Datei,
 * indem es die Inhalte einliest und sie in Objekte deserialisiert.
 */
class Datenbanksystem(private val datenbank: File) {

    val karten: MutableList<Karte>
    val kategorien: MutableList<Kategorie>
    val spiele: MutableList<Spiel>

    init {
        val input = datenbank.inputStream()
        val yaml = Yaml().load<Map<String, Any>>(input)

        @Suppress("UNCHECKED_CAST") val kartenYaml = yaml[KARTEN] as List<Map<String, Any>>
        karten = kartenYaml.map { Karte.fromYaml(it) }.toMutableList()

        @Suppress("UNCHECKED_CAST") val kategorienYaml = yaml[KATEGORIEN] as List<Map<String, Any>>
        kategorien = kategorienYaml.map { Kategorie.fromYaml(it, karten) }.toMutableList()

        @Suppress("UNCHECKED_CAST") val spieleYaml = yaml[SPIELE] as List<Map<String, Any>>
        spiele = spieleYaml.map { Spiel.fromYaml(it, kategorien) }.toMutableList()
    }

    /**
     * Speichert den aktuellen Zustand der Datenbankobjekte (`karten`, `kategorien`, `spiele`)
     * als YAML-Datei in das Dateisystem.
     *
     * Die Daten werden serialisiert und in der gleichen Reihenfolge
     * wie beim Laden (Karten -> Kategorien -> Spiele) zurückgeschrieben.
     */
    private fun speichereYaml() {
        val builder = StringBuilder()

        builder.append(attributToYamlZeile(0, KARTEN, null))
        for (karte in karten) {
            builder.append(karte.toYaml())
        }

        builder.append("\n")

        builder.append(attributToYamlZeile(0, KATEGORIEN, null))
        for (kategorie in kategorien) {
            builder.append(kategorie.toYaml())
        }

        builder.append("\n")

        builder.append(attributToYamlZeile(0, SPIELE, null))
        for (spiel in spiele) {
            builder.append(spiel.toYaml())
        }

        datenbank.writeText(builder.toString())
    }

    /**
     * Gibt einen zufälligen Kartentext aus einer Sammlung (`Kategorie` / `Spiel`) zurück.
     * Wenn alle Karten bereits gesehen wurden, wird der Status `gesehen` von allen zurückgesetzt.
     *
     * @param sammlung Sammlung, aus der eine Karte gewählt werden soll
     * @return Kartentext in der Originalsprache
     */
    fun getRandomKartentext(sammlung: SammlungAnSpielelementen<*>): String {
        val karten = when (sammlung) {
            is Kategorie -> sammlung.getAlleUngesehenenKarten()
            is Spiel -> sammlung.getAlleUngesehenenKarten()
            else -> error("Unbekannter Sammlungstyp: ${sammlung::class.simpleName}")
        }

        // Wenn es keine ungesehenen Karten gibt, dann sind wohl schon alle durch und
        // man kann die Sammlung von vorne durchgehen.
        if (karten.isEmpty()) {
            when (sammlung) {
                is Kategorie -> sammlung.setKartenUngesehen()
                is Spiel -> sammlung.setKartenUngesehen()
            }
            return getRandomKartentext(sammlung)
        }

        val randomKarte = karten.random()
        randomKarte.gesehen = true // wird die Karte ausgegeben, ist sie ebenfalls gesehen
        speichereYaml()

        return randomKarte.localizations[Sprachen.OG]!!
    }

    /**
     * Ermittelt eine neue, eindeutige ID, die um 1 höher ist als jede ID in der gegebenen Liste.
     * Lücken werden also nicht aufgefüllt!
     *
     * @param hoeherAlsIn Liste von Spielelementen, deren höchste ID bestimmt wird
     * @return neue, eindeutige ID
     */
    private fun neueID(hoeherAlsIn: List<LokalisierbaresSpielelement>): Int {
        return (hoeherAlsIn.maxOfOrNull { it.id } ?: 0) + 1
    }

    /**
     * Erstellt neue `Karte`n aus den gegebenen Kartentexten oder findet bestehende anhand der Namen.
     *
     * @param kartentexte Liste an (wahrscheinlich) neuen Kartentexten
     * @param sprache Sprache der Texte
     * @return Liste der neu erstellten oder gefundenen Karten
     */
    fun neueKarten(kartentexte: List<String>, sprache: Sprachen): List<Karte> {
        val neueKarten = mutableListOf<Karte>()
        var neueId = neueID(karten) - 1 // das ist die höchste ID

        kartentexte.forEach {
            neueId += 1 // für jeden Schleifendurchlauf ist die ID höher
            var neueKarte = findeElement(it, karten)
            if (neueKarte == null) {
                neueKarte = Karte.fromEingabe(neueId, sprache, it)
            } else {
                neueId -= 1 // neutralisiert die ID-Erhöhung der Schleife
            }
            neueKarten.add(neueKarte)
        }

        // nur die actually neuen Karten zum DBS hinzufügen
        val actuallyNeueKarten = neueKarten.filter { it !in karten }
        karten.addAll(actuallyNeueKarten)

        speichereYaml()
        return neueKarten.toList()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : SammlungAnSpielelementen<E>, E : LokalisierbaresSpielelement> alteSammlungFindenOderNeueErstellen(
        name: String, elemente: List<E>, sprache: Sprachen, findenIn: List<T>
    ): T? {
        var neueSammlung = findeElement(name, findenIn)

        if (neueSammlung == null) {
            val neueID = neueID(findenIn)
            when (findenIn[0]) {
                is Kategorie -> neueSammlung =
                    Kategorie.fromEingabe(neueID, sprache, name, elemente as List<Karte>) as T

                is Spiel -> neueSammlung =
                    Spiel.fromEingabe(neueID, sprache, name, elemente as List<Kategorie>) as T
            }
        } else {
            neueSammlung.localizations[Sprachen.OG] = name
            neueSammlung.setUebersetzung(sprache, name)

            elemente.forEach {
                if (!neueSammlung.originaleElemente[IDS]!!.contains(it)) neueSammlung.originaleElemente[IDS] =
                    neueSammlung.originaleElemente[IDS]!!.plus(it)
            }

            neueSammlung.originaleElemente[DAVON_ENTFERNT]!!.forEach {
                if (elemente.contains(it)) neueSammlung.originaleElemente[DAVON_ENTFERNT] =
                    neueSammlung.originaleElemente[DAVON_ENTFERNT]!!.minus(it)
            }
        }
        return neueSammlung
    }

    /**
     * Erstellt eine neue `Kategorie` oder findet eine bestehende anhand des Namens.
     *
     * @param name Name der Kategorie
     * @param karten Liste an Karten, die in die Kategorie aufgenommen werden sollen
     * @param sprache Sprache des Namens
     * @return die erstellte oder gefundene Kategorie
     */
    fun neueKategorie(name: String, karten: List<Karte>, sprache: Sprachen): Kategorie {
        val neueKategorie = alteSammlungFindenOderNeueErstellen(name, karten, sprache, kategorien)!!

        if (neueKategorie !in kategorien) {
            kategorien.add(neueKategorie)
        }
        speichereYaml()
        return neueKategorie
    }

    /**
     * Erstellt ein neues `Spiel` oder findet ein bestehendes anhand des Namens.
     *
     * @param name Name des Spiels
     * @param kategorien Liste an Kategorien, die in die Kategorie aufgenommen werden sollen
     * @param sprache Sprache des Namens
     * @return das erstellte oder gefundene Spiel
     */
    fun neuesSpiel(name: String, kategorien: List<Kategorie>, sprache: Sprachen): Spiel {
        val neuesSpiel = alteSammlungFindenOderNeueErstellen(name, kategorien, sprache, spiele)!!

        if (neuesSpiel !in spiele) {
            spiele.add(neuesSpiel)

        }
        speichereYaml()
        return neuesSpiel
    }

    /**
     * Markiert eine Karte als gelöscht und entfernt sie aus allen Kategorien.
     *
     * @param zuLoeschendeKarte Karte, die gelöscht werden soll
     */
    fun karteLoeschen(zuLoeschendeKarte: Karte) {
        zuLoeschendeKarte.geloescht = true
        val kategorienMitZuLoeschenderKarte = kategorien.filter {
            it.getAlleAktuellenKarten().contains(zuLoeschendeKarte)
        }
        kategorienMitZuLoeschenderKarte.forEach {
            it.karteEntfernen(zuLoeschendeKarte)
        }
        speichereYaml()
    }

    /**
     * Fügt Elemente (`Karte`n / `Kategorie`n) zu einer Sammlung (`Kategorie` oder `Spiel`) hinzu.
     *
     * @param spielOderKategorie Sammlung, der Elemente hinzugefügt werden sollen
     * @param neueElemente neue Elemente (IDs oder Objekte)
     */
    fun <T : Any, E : SammlungAnSpielelementen<F>, F : LokalisierbaresSpielelement> hinzufuegen(
        spielOderKategorie: E, neueElemente: List<T>
    ) {
        when (spielOderKategorie) {
            is Kategorie -> kartenZuKategorieHinzufuegen(spielOderKategorie, neueElemente)
            is Spiel -> kategorienZuSpielHinzufuegen(spielOderKategorie, neueElemente)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> kartenZuKategorieHinzufuegen(kategorie: Kategorie, neueKarten: List<T>) {
        when (neueKarten[0]) {
            is Karte -> kategorie.kartenHinzufuegen(neueKarten as List<Karte>)
            is Int -> kategorie.kartenHinzufuegen(findeElemente(neueKarten as List<Int>, karten))
        }
        speichereYaml()
    }

    fun karteAusKategorieEntfernen(zuEntfernendeKarte: Karte, ausKategorie: Kategorie) {
        ausKategorie.karteEntfernen(zuEntfernendeKarte)
        speichereYaml()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> kategorienZuSpielHinzufuegen(spiel: Spiel, kategorienIDs: List<T>) {
        when (kategorienIDs[0]) {
            is Kategorie -> spiel.kategorienHinzufuegen(kategorienIDs as List<Kategorie>)
            is Int -> spiel.kategorienHinzufuegen(
                findeElemente(
                    kategorienIDs as List<Int>, kategorien
                )
            )
        }
        speichereYaml()
    }

    fun kategorieAusSpielEntfernen(zuEntfernendeKategorie: Kategorie, ausSpiel: Spiel) {
        ausSpiel.kategorieEntfernen(zuEntfernendeKategorie)
        speichereYaml()
    }

    companion object {
        fun generieren(context: Context): Datenbanksystem {
            val datei = File(context.filesDir, DATENBANK_DATEI)
            if (!datei.exists()) {
                val datenkbankInResRaw = context.resources.openRawResource(R.raw.datenbank)
                datei.writeBytes(datenkbankInResRaw.readBytes())
            }
            return Datenbanksystem(datei)
        }

        fun generieren(): Datenbanksystem {
            val dateipfad = Paths.get(DATENBANK_PFAD)
            return Datenbanksystem(dateipfad.toFile())
        }
    }
}
