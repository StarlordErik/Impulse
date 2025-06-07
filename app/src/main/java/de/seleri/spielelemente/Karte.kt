package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml
import java.io.File

class Karte(
    var id: Int = 0,
    var localizedTexte: Map<Sprachen, String> = emptyMap()
) : ToYaml {
    override fun objToYaml(): String {
        val output = StringBuilder()

        output.append("$ID_$id")

        output.append(TEXT__)
        localizationsToYaml(output, localizedTexte)

        return output.toString()
    }

    @Suppress("UNCHECKED_CAST")
    override fun yamlToObj(yamlInput: String): Karte {

        // Yaml().load() gibt eine Liste zurück, wenn der Input mit "-" beginnt.
        val kartenDaten: Map<String, Any> = (Yaml().load(yamlInput) as List<Map<String, Any>>)[0]

        this.id = kartenDaten["ID"] as Int

        val localizedTexte = kartenDaten["Text"] as Map<String, String>
        this.localizedTexte = localizedTexte.mapKeys { Sprachen.valueOf(it.key) }

        return this
    }
}

fun main() {
    val englischeTestKartentexte = listOf(
        "Do I look kind? Explain.",
        "What is my body language telling you right now?",
        "!\"§$%&/()=?\n\t ,.-;:_#+'*\\{[]}´`~^°@€"
    )

    val cardsList = englischeTestKartentexte.mapIndexed { index, text ->
        val localizedKartenTexte: MutableMap<Sprachen, String> = mutableMapOf()
        for (sprache in Sprachen.entries) {
            if (sprache == Sprachen.EN) {
                localizedKartenTexte[sprache] = text
            } else {
                localizedKartenTexte[sprache] = ""
            }
        }
        Karte(
            id = index + 1,
            localizedTexte = localizedKartenTexte
        )
    }

    println("\nEin Kartentext mit Leerzeile:")
    println(cardsList[2].localizedTexte[Sprachen.EN])

    println("\nVom gegebenen Satz in die Yaml gespeichert:")
    val stringList = cardsList.map { it.objToYaml() }
    stringList.forEach {
        print(it)
    }

    val cardsList2 = mutableListOf<Karte>()
    stringList.forEach {
        val k = Karte()
        k.yamlToObj(it)
        cardsList2.add(k)
    }

    println("\nVon der eingelesenen Yaml in die Yaml wieder druckbar:")
    val stringList2 = cardsList2.map { it.objToYaml() }
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

    val alleKartenAlsString = StringBuilder()
    cardsList.forEach {
        alleKartenAlsString.append(it.objToYaml())
    }
    val outputFile = File("app/src/main/res/raw/tmp_karten_datenbank.yml")
    outputFile.parentFile?.mkdirs()
    outputFile.writeText(alleKartenAlsString.toString())

    println("\ntmp_karten_datenbank.yml erfolgreich geschrieben:\n${outputFile.absolutePath}")
}

