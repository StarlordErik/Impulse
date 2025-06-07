package de.seleri.spielelemente
/*
import org.yaml.snakeyaml.Yaml
import java.io.File

class Karte(
    var id: Int = 0,
    var localizedKartentexte: Map<Sprachen, String> = emptyMap()
) : ToYaml {
    override fun objToYaml(): String {
        val output = StringBuilder()

        // ID
        output.append("${EIN_TAB}- id: ${id}\n")

        // Kartentext
        output.append("${ZWEI_TAB}text:\n")

        // Lokalisierte Texte
        localizedKartentexte.forEach { (sprache, lokalisierterText) ->
            val lowercaseSprache = sprache.toString().lowercase()
            val escapedLocalizedText = escapeYamlString(lokalisierterText)
            output.append("${DREI_TAB}${lowercaseSprache}: ")
            output.append("\"${escapedLocalizedText}\"\n")
        }
        return output.toString()
    }

    override fun yamlToObj(yamlInput: String): Karte {

        // Yaml().load() gibt eine Liste zurück, wenn der Input mit "-" beginnt.
        val kartenDaten: Map<String, Any> = (Yaml().load(yamlInput) as List<Map<String, Any>>)[0]

        this.id = kartenDaten["id"] as Int

        // Der as?-Operator wird für einen safen cast benötigt - der Rest des Codeblocks folgte daraus.
        val kartentexte = (kartenDaten["text"] as? Map<*, *>)?.mapNotNull {
            val sprache = it.key as? String
            val lokalisierterText = it.value as? String
            if (sprache != null && lokalisierterText != null) sprache to lokalisierterText else null
        }?.toMap() ?: throw IllegalArgumentException(
            "Jemand hat in der YAML-Datei gepfuscht! " +
                    "Der Text von Karten-ID $id kann nicht vernünfitg eingelesen werden!"
        )
        this.localizedKartentexte = kartentexte.mapKeys { Sprachen.valueOf(it.key.uppercase()) }

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
        val localizedKartentexte: MutableMap<Sprachen, String> = mutableMapOf()
        for (sprache in Sprachen.entries) {
            if (sprache == Sprachen.EN) {
                localizedKartentexte[sprache] = text
            } else {
                localizedKartentexte[sprache] = ""
            }
        }
        Karte(
            id = index + 1,
            localizedKartentexte = localizedKartentexte
        )
    }

    println("\nEin Kartentext mit Leerzeile:")
    println(cardsList[2].localizedKartentexte[Sprachen.EN])

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
*/
