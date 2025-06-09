package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Spiel(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    override val urspruenglicheElemente: List<Kategorie>,
    override var weitereElemente: List<Kategorie>
) : SammlungAnSpielelementen<Kategorie>(id, localizations, urspruenglicheElemente, weitereElemente){

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        stringZweiMalZwischenfuegen(output, URSPRUENGLICHE, WEITERE, KATEGORIEN)
        return output.toString()
    }

    fun getAlleKategorien(): List<Kategorie> = getAlleElemente()
    fun getAlleKarten(): List<Karte> = getAlleKategorien().flatMap { it.getAlleKarten() }.distinct()

    fun kategorienHinzufuegen(kategorien: List<Kategorie>) {
        weitereElemente = weitereElemente + kategorien
    }
}

fun yamlToSpiele(yamlInput: String, moeglicheKategorien: List<Kategorie>): List<Spiel> {
    val daten = (Yaml().load(yamlInput) as List<Map<String, Any>>)
    return daten.map { yamlToSpiel(it, moeglicheKategorien) }
}

fun yamlToSpiel(data: Map<String, Any>, moeglicheKategorien: List<Kategorie>): Spiel {
    return yamlToSammlung(data, moeglicheKategorien, ::Spiel)
}

fun eingabeToSpiel(
    id: Int, sprache: Sprachen, name: String, urspruenglicheKategorien: List<Kategorie>
): Spiel = eingabeToSammlung(id, sprache, name, urspruenglicheKategorien, ::Spiel)
