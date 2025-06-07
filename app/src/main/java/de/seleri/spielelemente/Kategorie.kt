package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml
import java.io.File

class Kategorie(
    var id: Int = 0,
    var localizedNamen: Map<Sprachen, String> = emptyMap(),
    var urspruenglicheKartenIDs: List<Int> = emptyList(),
    var weitereKartenIDs: List<Int> = emptyList()
): ToYaml{
    override fun objToYaml(): String {
        val output = StringBuilder()

        output.append("$ID_$id")

        output.append(NAME__)
        localizationsToYaml(output, localizedNamen)

        val karten = "Karten"
        listenToYaml(output,"$URSPRUENGLICHE$karten", urspruenglicheKartenIDs)
        listenToYaml(output,"$WEITERE$karten", weitereKartenIDs)

        return output.toString()
    }

    @Suppress("UNCHECKED_CAST")
    override fun yamlToObj(yamlInput: String): Kategorie {

        // Yaml().load() gibt eine Liste zurück, wenn der Input mit "-" beginnt.
        val kategorieDaten: Map<String, Any> = (Yaml().load(yamlInput) as List<Map<String, Any>>)[0]

        this.id = kategorieDaten["ID"] as Int

        val localizedNamen = kategorieDaten["Name"] as Map<String, String>
        this.localizedNamen = localizedNamen.mapKeys { Sprachen.valueOf(it.key) }

        this.urspruenglicheKartenIDs = kategorieDaten["ursprüngliche_Karten-IDs"] as List<Int>
        this.weitereKartenIDs = kategorieDaten["weitere_Karten-IDs"] as List<Int>
        
        return this
    }
}

fun main() {
    val englischeTestKategorien = listOf(
        "Level 1: Perception",
        "Level 2: Connection",
        "!\"§$%&/()=?\n\t ,.-;:_#+'*\\{[]}´`~^°@€"
    )

    val kategorienListe = englischeTestKategorien.mapIndexed { index, name ->
        val localizedKategoriennamen: MutableMap<Sprachen, String> = mutableMapOf()
        for (sprache in Sprachen.entries) {
            if (sprache == Sprachen.EN) {
                localizedKategoriennamen[sprache] = name
            } else {
                localizedKategoriennamen[sprache] = ""
            }
        }
        Kategorie(
            id = index + 1,
            localizedNamen = localizedKategoriennamen,
            urspruenglicheKartenIDs = listOf(1,2),
            weitereKartenIDs = listOf()
        )
    }

    println("\nEin Kategoriename mit Leerzeile:")
    println(kategorienListe[2].localizedNamen[Sprachen.EN])

    println("\nVom gegebenen Namen in die Yaml gespeichert:")
    val stringList = kategorienListe.map { it.objToYaml() }
    stringList.forEach {
        print(it)
    }

    val kategorienListe2 = mutableListOf<Kategorie>()
    stringList.forEach {
        val k = Kategorie()
        k.yamlToObj(it)
        kategorienListe2.add(k)
    }

    println("\nVon der eingelesenen Yaml in die Yaml wieder druckbar:")
    val stringList2 = kategorienListe2.map { it.objToYaml() }
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

    val alleKategorienAlsString = StringBuilder()
    kategorienListe.forEach {
        alleKategorienAlsString.append(it.objToYaml())
    }
    val outputFile = File("app/src/main/res/raw/tmp_karten_datenbank.yml")
    outputFile.parentFile?.mkdirs()
    outputFile.writeText(alleKategorienAlsString.toString())

    println("\ntmp_karten_datenbank.yml erfolgreich geschrieben:\n${outputFile.absolutePath}")
}

