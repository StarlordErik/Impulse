package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Kategorie(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    override val urspruenglicheElemente: List<Karte>,
    override var weitereElemente: List<Karte>
) : SammlungAnSpielelementen<Karte>(id, localizations, urspruenglicheElemente, weitereElemente) {

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        stringZweiMalZwischenfuegen(output, URSPRUENGLICHE, WEITERE, KARTEN)
        return output.toString()
    }

    fun getAlleKarten(): List<Karte> = getAlleElemente()

    fun kartenHinzufuegen(karten: List<Karte>) {
        weitereElemente = weitereElemente + karten
    }
}

fun yamlToKategorien(yamlInput: String, moeglicheKarten: List<Karte>): List<Kategorie> {
    val daten = (Yaml().load(yamlInput) as List<Map<String, Any>>)
    return daten.map { yamlToKategorie(it, moeglicheKarten) }
}

fun yamlToKategorie(data: Map<String, Any>, moeglicheKarten: List<Karte>): Kategorie {
    return yamlToSammlung(data, moeglicheKarten, ::Kategorie)
}

fun eingabeToKategorie(
    id: Int, sprache: Sprachen, name: String, urspruenglicheKarten: List<Karte>
): Kategorie = eingabeToSammlung(id, sprache, name, urspruenglicheKarten, ::Kategorie)
