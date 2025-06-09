package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Kategorie(
    override val id: Int,
    override val localizations: Localizations,
    override val urspruenglicheElemente: List<Karte>,
    override var weitereElemente: List<Karte>
) : SammlungAnSpielelementen<Karte>(id, localizations, urspruenglicheElemente, weitereElemente){

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        output.insert(output.lastIndexOf(URSPRUENGLICHE) + URSPRUENGLICHE.length, KARTEN)
        output.insert(output.lastIndexOf(WEITERE) + WEITERE.length, KARTEN)
        return output.toString()
    }

    fun getAlleKarten(): List<Karte> = getAlleElemente()

    fun kartenHinzufuegen(karten: List<Karte>) {
        weitereElemente = weitereElemente + karten
    }
}

fun eingabeToKategorie(
    id: Int, sprache: Sprachen, name: String, urspruenglicheKarten: List<Karte>
): Kategorie = eingabeToSammlung(id, sprache, name, urspruenglicheKarten, ::Kategorie)

fun yamlToKategorien(yamlInput: String, moeglicheKarten: List<Karte>): List<Kategorie> {
    val daten = (Yaml().load(yamlInput) as List<Map<String, Any>>)
    return daten.map { yamlToKategorie(it, moeglicheKarten) }
}

@Suppress("UNCHECKED_CAST")
fun yamlToKategorie(data: Map<String, Any>, moeglicheKarten: List<Karte>): Kategorie {
    val id = data[ID] as Int
    val localizations = yamlToLocalization(data)
    val urspruenglicheIds = data["$URSPRUENGLICHE$KARTEN$IDS"] as List<Int>
    val weitereIds = data["$WEITERE$KARTEN$IDS"] as List<Int>

    return Kategorie(
        id,
        localizations,
        findeElemente(urspruenglicheIds, moeglicheKarten),
        findeElemente(weitereIds, moeglicheKarten)
    )
}
