package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Karte(
    override val id: Int, override val localizations: Localizations
) : LokalisierbaresSpielelement(id, localizations), ToYaml {

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append("$ID_$id")
        output.append(TEXT__)
        output.append(localizations.toYaml())
        return output.toString()
    }
}

fun eingabeToKarte(id: Int, sprache: Sprachen, text: String): Karte {
    return Karte(id, eingabeToLocalizations(sprache, text))
}

@Suppress("UNCHECKED_CAST")
fun yamlToKarte(yamlInput: String): Karte {
    val data = (Yaml().load(yamlInput) as List<Map<String, Any>>)[0]
    val id = data[ID] as Int
    val text = data[TEXT] as Map<String, String>
    val localizations = Localizations(text.mapKeys { Sprachen.valueOf(it.key) })
    return Karte(id, localizations)
}
