package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Kategorie(
    var id: Int = 0,
    var localizedNamen: Map<Sprachen, String> = emptyMap(),
    var urspruenglicheKarten: List<Karte> = emptyList(),
    var weitereKarten: List<Karte> = emptyList()
): ToYaml{
    override fun toYaml(): String {
        val output = StringBuilder()

        output.append("$ID_$id")

        output.append(NAME__)
        localizationsToYaml(output, localizedNamen)

        val karten = "Karten"
        kartenListenToYaml(output,"$URSPRUENGLICHE$karten", urspruenglicheKarten)
        kartenListenToYaml(output,"$WEITERE$karten", weitereKarten)

        return output.toString()
    }

    fun getKarten(): List<Karte>{
        val karten = mutableListOf<Karte>()
        karten.addAll(urspruenglicheKarten)
        karten.addAll(weitereKarten)
        return karten
    }
}

fun eingabeToKategorie(id: Int, sprache: Sprachen, name: String, urspruenglicheKarten: List<Karte>): Kategorie {
    val localizedKategorieNamen: MutableMap<Sprachen, String> = mutableMapOf()
    Sprachen.entries.forEach {
        if (it == sprache) {
            localizedKategorieNamen[it] = name
        } else {
            localizedKategorieNamen[it] = ""
        }
    }
    return Kategorie(
        id = id,
        localizedNamen = localizedKategorieNamen,
        urspruenglicheKarten = urspruenglicheKarten,
        weitereKarten = emptyList()
    )
}

@Suppress("UNCHECKED_CAST")
fun yamlToKategorie(yamlInput: String, moeglicheKarten: List<Karte>): Kategorie {

    // Yaml().load() gibt eine Liste zurück, wenn der Input mit "-" beginnt.
    val kategorieDaten: Map<String, Any> = (Yaml().load(yamlInput) as List<Map<String, Any>>)[0]

    val id = kategorieDaten["ID"] as Int

    val namen = kategorieDaten["Name"] as Map<String, String>
    val localizedNamen = namen.mapKeys { Sprachen.valueOf(it.key) }

    val urspruenglicheKartenIDs = kategorieDaten["ursprüngliche_Karten-IDs"] as List<Int>
    val urspruenglicheKarten = mutableListOf<Karte>()
    urspruenglicheKartenIDs.forEach {
        urspruenglicheKarten.add(moeglicheKarten.find { k -> k.id == it }!!)
    }

    val weitereKartenIDs = kategorieDaten["weitere_Karten-IDs"] as List<Int>
    val weitereKarten = mutableListOf<Karte>()
    weitereKartenIDs.forEach {
        weitereKarten.add(moeglicheKarten.find { k -> k.id == it }!!)
    }

    return Kategorie(
        id = id,
        localizedNamen = localizedNamen,
        urspruenglicheKarten = urspruenglicheKarten,
        weitereKarten = weitereKarten
    )
}
