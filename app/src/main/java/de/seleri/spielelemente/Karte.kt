package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Karte(
    override val id: Int, override val localizations: Localizations
) : LokalisierbaresSpielelement(id, localizations)

fun eingabeToKarte(id: Int, sprache: Sprachen, text: String): Karte {
    return Karte(id, eingabeToLocalizations(sprache, text))
}

fun yamlToKarten(yamlInput: String): List<Karte> {
    val data = (Yaml().load(yamlInput) as List<Map<String, Any>>)
    return data.map { yamlToKarte(it) }
}

@Suppress("UNCHECKED_CAST")
fun yamlToKarte(data: Map<String, Any>): Karte{
    val id = data[ID] as Int
    val localizations = yamlToLocalization(data)
    return Karte(id, localizations)
}
