package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Karte(
    var id: Int = 0,
    var localizedTexte: Map<Sprachen, String> = emptyMap()
) : ToYaml {
    override fun toYaml(): String {
        val output = StringBuilder()

        output.append("$ID_$id")

        output.append(TEXT__)
        localizationsToYaml(output, localizedTexte)

        return output.toString()
    }
}

@Suppress("UNCHECKED_CAST")
fun yamlToKarte(yamlInput: String): Karte {

    // Yaml().load() gibt eine Liste zurück, wenn der Input mit "-" beginnt.
    val kartenDaten: Map<String, Any> = (Yaml().load(yamlInput) as List<Map<String, Any>>)[0]

    val id = kartenDaten["ID"] as Int

    val text = kartenDaten["Text"] as Map<String, String>
    val localizedTexte = text.mapKeys { Sprachen.valueOf(it.key) }

    return Karte(
        id = id,
        localizedTexte = localizedTexte
    )
}

fun eingabeToKarte(id: Int, sprache: Sprachen, text: String): Karte {
    val localizedKartenTexte: MutableMap<Sprachen, String> = mutableMapOf()
    Sprachen.entries.forEach {
        if (it == sprache) {
            localizedKartenTexte[it] = text
        } else {
            localizedKartenTexte[it] = ""
        }
    }
    return Karte(
        id = id,
        localizedTexte = localizedKartenTexte
    )
}
