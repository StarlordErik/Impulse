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
        output.append(localizationsToYaml(TEXT))
        output.append(attributToYamlZeile(2, GESEHEN, gesehen))
        output.append(attributToYamlZeile(2, GELOESCHT, geloescht))
        return output.toString()
    }

    companion object {
        fun fromEingabe(id: Int, sprache: Sprachen, text: String): Karte {
            val (id, localizations) = eingabeToLokalisierbaresElement(id, sprache, text)
            return Karte(id, localizations, false, false)
        }

        fun fromYaml(data: Map<String, Any>): Karte {
            val (id, localizations) = yamlToLokalisierbaresElement(data)
            return Karte(
                id, localizations, data[GESEHEN] as Boolean, data[GELOESCHT] as Boolean
            )
        }

        fun fromYaml(yamlInput: String): List<Karte> {
            val data = (Yaml().load(yamlInput) as List<Map<String, Any>>)
            return data.map { fromYaml(it) }
        }
    }
}



