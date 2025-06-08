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
        output.append("$ID_$id")
        output.append(NAME__)
        output.append(localizations.toYaml())
        sammlungToYaml(output, "$TAB_URSPRUENGLICHE$KARTEN", urspruenglicheElemente)
        sammlungToYaml(output, "$TAB_WEITERE$KARTEN", weitereElemente)
        return output.toString()
    }
}

fun eingabeToKategorie(
    id: Int, sprache: Sprachen, name: String, urspruenglicheKarten: List<Karte>
): Kategorie = eingabeToSammlung(id, sprache, name, urspruenglicheKarten, ::Kategorie)

@Suppress("UNCHECKED_CAST")
fun yamlToKategorie(yamlInput: String, moeglicheKarten: List<Karte>): Kategorie {
    val data = (Yaml().load(yamlInput) as List<Map<String, Any>>)[0]
    val id = data[ID] as Int
    val namen = data[NAME] as Map<String, String>
    val localizations = Localizations(namen.mapKeys { Sprachen.valueOf(it.key) })

    val urspruenglicheIds = data["$URSPRUENGLICHE$KARTEN$IDS"] as List<Int>
    val weitereIds = data["$WEITERE$KARTEN$IDS"] as List<Int>

    return Kategorie(
        id,
        localizations,
        findeElemente(urspruenglicheIds, moeglicheKarten),
        findeElemente(weitereIds, moeglicheKarten)
    )
}
