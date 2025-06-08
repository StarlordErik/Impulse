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
        output.append("$ID_$id")
        output.append(NAME__)
        output.append(localizations.toYaml())
        sammlungToYaml(output, "$TAB_URSPRUENGLICHE$KATEGORIEN", urspruenglicheElemente)
        sammlungToYaml(output, "$TAB_WEITERE$KATEGORIEN", weitereElemente)
        return output.toString()
    }

    fun getAlleKarten(): List<Karte> = getAlleElemente().flatMap { it.getAlleElemente() }.distinct()
}

fun eingabeToSpiel(
    id: Int, sprache: Sprachen, name: String, urspruenglicheKategorien: List<Kategorie>
): Spiel = eingabeToSammlung(id, sprache, name, urspruenglicheKategorien, ::Spiel)

@Suppress("UNCHECKED_CAST")
fun yamlToSpiel(yamlInput: String, moeglicheKategorien: List<Kategorie>): Spiel {
    val data = (Yaml().load(yamlInput) as List<Map<String, Any>>)[0]
    val id = data[ID] as Int
    val namen = data[NAME] as Map<String, String>
    val localizations = Localizations(namen.mapKeys { Sprachen.valueOf(it.key) })

    val urspruenglicheIds = data["$URSPRUENGLICHE$KATEGORIEN$IDS"] as List<Int>
    val weitereIds = data["$WEITERE$KATEGORIEN$IDS"] as List<Int>

    return Spiel(
        id,
        localizations,
        findeElemente(urspruenglicheIds, moeglicheKategorien),
        findeElemente(weitereIds, moeglicheKategorien)
    )
}
