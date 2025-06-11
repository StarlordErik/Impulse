package de.seleri.spielelemente

import android.content.Context
import de.seleri.impulse.R
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.nio.file.Paths
import kotlin.collections.set

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
class Datenbanksystem(private val datenbank: File) {

    val karten: MutableList<Karte>
    val kategorien: MutableList<Kategorie>
    val spiele: MutableList<Spiel>

    init {
        val input = datenbank.inputStream()
        val yaml = Yaml().load<Map<String, Any>>(input)

        @Suppress("UNCHECKED_CAST") val kartenYaml = yaml[KARTEN] as List<Map<String, Any>>
        karten = kartenYaml.map { yamlToKarte(it) }.toMutableList()

        @Suppress("UNCHECKED_CAST") val kategorienYaml = yaml[KATEGORIEN] as List<Map<String, Any>>
        kategorien = kategorienYaml.map { yamlToKategorie(it, karten) }.toMutableList()

        @Suppress("UNCHECKED_CAST") val spieleYaml = yaml[SPIELE] as List<Map<String, Any>>
        spiele = spieleYaml.map { yamlToSpiel(it, kategorien) }.toMutableList()
    }


    fun neueKarten(kartentexte: List<String>, sprache: Sprachen): List<Karte> {
        val neueKarten = mutableListOf<Karte>()
        var neueId = neueID(karten)

        kartentexte.forEach {
            var neueKarte = findeElement(it, karten)
            if (neueKarte == null) {
                neueKarte = eingabeToKarte(neueId, sprache, it)
            } else {
                neueKarte.localizations[Sprachen.OG] = it
                neueKarte.setUebersetzung(sprache, it)
                neueId -= 1
            }
            neueId += 1
            neueKarten.add(neueKarte)
        }

        val actuallyNeueKarten = neueKarten.filter { it !in karten }
        karten.addAll(actuallyNeueKarten)

        speichereYaml()
        return neueKarten.toList()

    }

    fun neueKategorie(name: String, karten: List<Karte>, sprache: Sprachen): Kategorie {
        val neueKategorie = alteSammlungFindenOderNeueErstellen(name, karten, sprache, kategorien)!!

        if (neueKategorie !in kategorien) {
            kategorien.add(neueKategorie)
        }
        speichereYaml()
        return neueKategorie
    }

    fun neuesSpiel(name: String, kategorien: List<Kategorie>, sprache: Sprachen): Spiel {
        val neuesSpiel = alteSammlungFindenOderNeueErstellen(name, kategorien, sprache, spiele)!!

        if (neuesSpiel !in spiele) {
            spiele.add(neuesSpiel)

        }
        speichereYaml()
        return neuesSpiel
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : SammlungAnSpielelementen<E>, E : LokalisierbaresSpielelement> alteSammlungFindenOderNeueErstellen(
        name: String, elemente: List<E>, sprache: Sprachen, findenIn: List<T>
    ): T? {
        var neueSammlung = findeElement(name, findenIn)

        if (neueSammlung == null) {
            val neueID = neueID(findenIn)
            when (findenIn[0]) {
                is Kategorie -> neueSammlung =
                    eingabeToKategorie(neueID, sprache, name, elemente as List<Karte>) as T

                is Spiel -> neueSammlung =
                    eingabeToSpiel(neueID, sprache, name, elemente as List<Kategorie>) as T
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

    private fun neueID(hoeherAlsIn: List<LokalisierbaresSpielelement>): Int {
        return (hoeherAlsIn.maxOfOrNull { it.id } ?: 0) + 1
    }

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
}

const val DATENBANK_NAME = "datenbank"
const val DATENBANK_DATEIFORMAT = ".yml"
const val DATENBANK_DATEI = "$DATENBANK_NAME$DATENBANK_DATEIFORMAT"
const val DATENBANK_PFAD = "app/src/main/res/raw/$DATENBANK_DATEI"

fun datenbanksystemGenerieren(context: Context): Datenbanksystem {
    val datei = File(context.filesDir, DATENBANK_DATEI)
    if (!datei.exists()) {
        val datenkbankInResRaw = context.resources.openRawResource(R.raw.datenbank)
        datei.writeBytes(datenkbankInResRaw.readBytes())
    }
    return Datenbanksystem(datei)
}

fun datenbanksystemGenerieren(): Datenbanksystem {
    val dateipfad = Paths.get(DATENBANK_PFAD)
    return Datenbanksystem(dateipfad.toFile())
}
