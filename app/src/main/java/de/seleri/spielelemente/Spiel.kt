package de.seleri.spielelemente
/*
import org.yaml.snakeyaml.Yaml
import java.io.File

class Spiel(
    var id: Int = 0,
    var localizedNamen: Map<Sprachen, String> = emptyMap(),
    var urspruenglicheKategorienIDs: List<Int> = emptyList(),
    var weitereKategorienIDs: List<Int> = emptyList()
) : ToYaml {
    override fun anyToYaml(): String {
        val output = StringBuilder()

        output.append("$ID_$id")

        output.append(NAME__)
        localizationsToYaml(output, localizedNamen)

        val kategorien = "Kategorien"
        listenToYaml(output, "$URSPRUENGLICHE$kategorien", urspruenglicheKategorienIDs)
        listenToYaml(output, "$WEITERE$kategorien", weitereKategorienIDs)

        return output.toString()
    }

    @Suppress("UNCHECKED_CAST")
    override fun yamlToEinAny(yamlInput: String): Spiel {

        // Yaml().load() gibt eine Liste zurück, wenn der Input mit "-" beginnt.
        val spielDaten: Map<String, Any> = (Yaml().load(yamlInput) as List<Map<String, Any>>)[0]

        this.id = spielDaten["ID"] as Int

        val localizedNamen = spielDaten["Name"] as Map<String, String>
        this.localizedNamen = localizedNamen.mapKeys { Sprachen.valueOf(it.key) }

        this.urspruenglicheKategorienIDs = spielDaten["ursprüngliche_Kategorien-IDs"] as List<Int>
        this.weitereKategorienIDs = spielDaten["weitere_Kategorien-IDs"] as List<Int>

        return this
    }
}

fun main() {
    val englischeTestSpiele = listOf(
        "We're not really strangers",
        "Fun Facts",
        "!\"§$%&/()=?\n\t ,.-;:_#+'*\\{[]}´`~^°@€"
    )

    val spieleListe = englischeTestSpiele.mapIndexed { index, name ->
        val localizedSpielNamen: MutableMap<Sprachen, String> = mutableMapOf()
        for (sprache in Sprachen.entries) {
            if (sprache == Sprachen.EN) {
                localizedSpielNamen[sprache] = name
            } else {
                localizedSpielNamen[sprache] = ""
            }
        }
        Spiel(
            id = index + 1,
            localizedNamen = localizedSpielNamen,
            urspruenglicheKategorienIDs = listOf(1, 2),
            weitereKategorienIDs = emptyList()
        )
    }

    println("\nEin Spielname mit Leerzeile:")
    println(spieleListe[2].localizedNamen[Sprachen.EN])

    println("\nVom gegebenen Namen in die Yaml gespeichert:")
    val stringList = spieleListe.map { it.anyToYaml() }
    stringList.forEach {
        print(it)
    }

    val spieleListe2 = mutableListOf<Spiel>()
    stringList.forEach {
        val s = Spiel()
        s.yamlToEinAny(it)
        spieleListe2.add(s)
    }

    println("\nVon der eingelesenen Yaml in die Yaml wieder druckbar:")
    val stringList2 = spieleListe2.map { it.anyToYaml() }
    stringList2.forEach {
        print(it)
    }

    println("\nTest: Sind das identische Strings? Antwort: ")
    var identisch = true
    for (i in stringList.indices) {
        if (stringList[i] != stringList2[i]) {
            identisch = false
            break
        }
    }
    println(identisch)

    val alleSpieleAlsString = StringBuilder()
    spieleListe.forEach {
        alleSpieleAlsString.append(it.anyToYaml())
    }
    val outputFile = File("app/src/main/res/raw/tmp_karten_datenbank.yml")
    outputFile.parentFile?.mkdirs()
    outputFile.writeText(alleSpieleAlsString.toString())

    println("\ntmp_karten_datenbank.yml erfolgreich geschrieben:\n${outputFile.absolutePath}")
}
*/
