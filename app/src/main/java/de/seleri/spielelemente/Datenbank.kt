package de.seleri.spielelemente

import android.content.Context
import de.seleri.impulse.R
import java.io.File
import org.yaml.snakeyaml.Yaml

/*
Für eine zentrale Initialisierung in einer Application-Klasse speichern:

class MyApp : Application() {
    lateinit var datenbank: Datenbank

    override fun onCreate() {
        super.onCreate()
        datenbank = Datenbank(this)
    }
}

Dann in AndroidManifest.xml:
<application
    android:name=".MyApp"
    ... >

Und in Activities:
val datenbank = (application as MyApp).datenbank

 */
class Datenbank(private val context: Context) {

    val karten: MutableList<Karte>
    val kategorien: MutableList<Kategorie>
    val spiele: MutableList<Spiel>

    init {
        // val input = File(dateiname).readText()
        val input = context.resources.openRawResource(R.raw.datenbank)
        val yaml = Yaml().load<Map<String, Any>>(input)

        @Suppress("UNCHECKED_CAST") val kartenYaml = yaml[KARTEN] as List<Map<String, Any>>
        karten = kartenYaml.map { yamlToKarte(it) }.toMutableList()

        @Suppress("UNCHECKED_CAST") val kategorienYaml = yaml[KATEGORIEN] as List<Map<String, Any>>
        kategorien = kategorienYaml.map { yamlToKategorie(it, karten) }.toMutableList()

        @Suppress("UNCHECKED_CAST") val spieleYaml = yaml[SPIELE] as List<Map<String, Any>>
        spiele = spieleYaml.map { yamlToSpiel(it, kategorien) }.toMutableList()
    }


    fun neueKarten(kartentexte: List<String>, sprache: Sprachen) {
        kartentexte.forEach { text ->
            val neueId = (karten.maxOfOrNull { it.id } ?: 0) + 1
            val neueKarte = eingabeToKarte(neueId, sprache, text)
            karten.add(neueKarte)
        }
        speichereYaml()
    }

    fun neueKategorie(name: String, karten: List<Karte>, sprache: Sprachen) {
        val neueId = (kategorien.maxOfOrNull { it.id } ?: 0) + 1
        kategorien.add(eingabeToKategorie(neueId, sprache, name, karten))
        speichereYaml()
    }

    fun kartenZuKategorieHinzufuegen(kategorie: Kategorie, kartenIDs: List<Int>) {
        kategorie.kartenHinzufuegen(findeElemente(kartenIDs, karten))
        speichereYaml()
    }


    fun neuesSpiel(name: String, kategorien: List<Kategorie>, sprache: Sprachen) {
        val neueId = (spiele.maxOfOrNull { it.id } ?: 0) + 1
        spiele.add(eingabeToSpiel(neueId, sprache, name, kategorien))
        speichereYaml()
    }

    fun kategorienZuSpielHinzufuegen(spiel: Spiel, kategorienIDs: List<Int>) {
        spiel.kategorienHinzufuegen(findeElemente(kategorienIDs, kategorien))
        speichereYaml()
    }

    private fun speichereYaml() {
        val builder = StringBuilder()

        builder.append("$KARTEN:\n")
        for (karte in karten) {
            builder.append(karte.toYaml())
        }

        builder.append("\n$KATEGORIEN:\n")
        for (kategorie in kategorien) {
            builder.append(kategorie.toYaml())
        }

        builder.append("\n$SPIELE:\n")
        for (spiel in spiele) {
            builder.append(spiel.toYaml())
        }

        //TODO File(dateiname).writeText(builder.toString())
    }
}
