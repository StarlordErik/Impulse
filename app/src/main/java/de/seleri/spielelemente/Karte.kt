package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Karte(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    var gesehen: Boolean,
    var geloescht: Boolean
) : LokalisierbaresSpielelement(id, localizations) {

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        stringZwischenfuegen(output, "$id", TEXT__, true)
        output.append("$GESEHEN_$gesehen")
        output.append("$GELOESCHT_$geloescht\n")
        return output.toString()
    }
}

fun yamlToKarten(yamlInput: String): List<Karte> {
    val data = (Yaml().load(yamlInput) as List<Map<String, Any>>)
    return data.map { yamlToKarte(it) }
}

fun yamlToKarte(data: Map<String, Any>): Karte {
    val dummy = yamlToLokalisierbaresElement(data, ::LokalisierbaresSpielelement)
    return Karte(
        dummy.id, dummy.localizations, data[GESEHEN] as Boolean, data[GELOESCHT] as Boolean
    )
}

fun eingabeToKarte(id: Int, sprache: Sprachen, text: String): Karte {
    val dummy = eingabeToLokalisierbaresElement(id, sprache, text, ::LokalisierbaresSpielelement)
    return Karte(dummy.id, dummy.localizations, false, false)
}