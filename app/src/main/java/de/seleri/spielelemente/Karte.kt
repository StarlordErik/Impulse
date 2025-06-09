package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Karte(
    override val id: Int, override val localizations: MutableMap<Sprachen, String>
) : LokalisierbaresSpielelement(id, localizations) {

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        stringZwischenfuegen(output, "$id", TEXT__, true)
        return output.toString()
    }
}

fun yamlToKarten(yamlInput: String): List<Karte> {
    val data = (Yaml().load(yamlInput) as List<Map<String, Any>>)
    return data.map { yamlToKarte(it) }
}

fun yamlToKarte(data: Map<String, Any>): Karte {
    return yamlToLokalisierbaresElement(data, ::Karte)
}

fun eingabeToKarte(id: Int, sprache: Sprachen, text: String): Karte {
    return eingabeToLokalisierbaresElement(id, sprache, text, ::Karte)
}
