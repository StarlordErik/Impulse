package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Spiel(
    val id: Int,
    val localizedNamen: Map<Sprachen, String>,
    val urspruenglicheKategorien: List<Kategorie>,
    var weitereKategorien: List<Kategorie>
) : ToYaml {
    override fun toYaml(): String {
        val output = StringBuilder()

        output.append("$ID_$id")

        output.append(NAME__)
        localizationsToYaml(output, localizedNamen)

        val kategorien = "Kategorien"
        kategorieListenToYaml(output, "$URSPRUENGLICHE$kategorien", urspruenglicheKategorien)
        kategorieListenToYaml(output, "$WEITERE$kategorien", weitereKategorien)

        return output.toString()
    }

    fun getAlleKategorien(): List<Kategorie> {
        val kategorien = mutableListOf<Kategorie>()
        kategorien.addAll(urspruenglicheKategorien)
        kategorien.addAll(weitereKategorien)
        return kategorien
    }

    fun getAlleKarten(): List<Karte> {
        return getAlleKategorien()
            .flatMap { it.getAlleKarten() }
            .distinct()
    }
}

fun eingabeToSpiel(id: Int, sprache: Sprachen, name: String, urspruenglicheKategorien: List<Kategorie>): Spiel {
    val localizedSpielNamen = Sprachen.entries.associateWith {
        if (it == sprache) name else ""
    }

    return Spiel(
        id = id,
        localizedNamen = localizedSpielNamen,
        urspruenglicheKategorien = urspruenglicheKategorien,
        weitereKategorien = emptyList()
    )
}

@Suppress("UNCHECKED_CAST")
fun yamlToSpiel(yamlInput: String, moeglicheKategorien: List<Kategorie>): Spiel {

    // Yaml().load() gibt eine Liste zurück, wenn der Input mit "-" beginnt.
    val spielDaten: Map<String, Any> = (Yaml().load(yamlInput) as List<Map<String, Any>>)[0]

    val id = spielDaten["ID"] as Int

    val namen = spielDaten["Name"] as Map<String, String>
    val localizedNamen = namen.mapKeys { Sprachen.valueOf(it.key) }

    val urspruenglicheKategorienIDs = spielDaten["ursprüngliche_Kategorien-IDs"] as List<Int>
    val urspruenglicheKategorien = urspruenglicheKategorienIDs.map { zuFindendeId ->
        moeglicheKategorien.find { it.id == zuFindendeId } ?: error("Kategorie mit ID $zuFindendeId nicht gefunden")
    }

    val weitereKategorienIDs = spielDaten["weitere_Kategorien-IDs"] as List<Int>
    val weitereKategorien = weitereKategorienIDs.map { zuFindendeId ->
        moeglicheKategorien.find { it.id == zuFindendeId } ?: error("Kategorie mit ID $zuFindendeId nicht gefunden")
    }

    return Spiel(
        id = id,
        localizedNamen = localizedNamen,
        urspruenglicheKategorien = urspruenglicheKategorien,
        weitereKategorien = weitereKategorien
    )
}

