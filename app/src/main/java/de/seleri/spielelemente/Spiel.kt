package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Spiel(
    override val id: Int,
    override val localizations: Localizations,
    override val urspruenglicheElemente: List<Kategorie>,
    override var weitereElemente: List<Kategorie>
) : SammlungAnSpielelementen<Kategorie>(id, localizations, urspruenglicheElemente, weitereElemente){

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        output.insert(output.lastIndexOf(URSPRUENGLICHE) + URSPRUENGLICHE.length, KATEGORIEN)
        output.insert(output.lastIndexOf(WEITERE) + WEITERE.length, KATEGORIEN)
        return output.toString()
    }

    fun getAlleKategorien(): List<Kategorie> = getAlleElemente()
    fun getAlleKarten(): List<Karte> = getAlleKategorien().flatMap { it.getAlleKarten() }.distinct()

    fun kategorienHinzufuegen(kategorien: List<Kategorie>) {
        weitereElemente = weitereElemente + kategorien
    }
}

fun eingabeToSpiel(
    id: Int, sprache: Sprachen, name: String, urspruenglicheKategorien: List<Kategorie>
): Spiel = eingabeToSammlung(id, sprache, name, urspruenglicheKategorien, ::Spiel)

fun yamlToSpiele(yamlInput: String, moeglicheKategorien: List<Kategorie>): List<Spiel> {
    val daten = (Yaml().load(yamlInput) as List<Map<String, Any>>)
    return daten.map { yamlToSpiel(it, moeglicheKategorien) }
}

@Suppress("UNCHECKED_CAST")
fun yamlToSpiel(data: Map<String, Any>, moeglicheKategorien: List<Kategorie>): Spiel {
    val id = data[ID] as Int
    val localizations = yamlToLocalization(data)
    val urspruenglicheIds = data["$URSPRUENGLICHE$KATEGORIEN$IDS"] as List<Int>
    val weitereIds = data["$WEITERE$KATEGORIEN$IDS"] as List<Int>

    return Spiel(
        id,
        localizations,
        findeElemente(urspruenglicheIds, moeglicheKategorien),
        findeElemente(weitereIds, moeglicheKategorien)
    )
}
