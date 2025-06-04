package de.seleri.tools

import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.Yaml
import java.io.File

fun main() {
    val englishSentences = listOf(
        "Do I look kind? Explain.",
        "What is my body language telling you right now?"
    )


    val cardsList: List<Map<String, Any>> = englishSentences.mapIndexed { index, sentence ->
        mapOf(
            "id" to (index + 1),
            "genreIDs" to listOf(1, 2),
            "text" to mapOf(
                "de" to "",
                "en" to sentence
            )
        )
    }

    val root: Map<String, Any> = mapOf("karten" to cardsList)

    val dumperOptions = DumperOptions().apply {
        defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
        isPrettyFlow = true
    }
    val yaml = Yaml(dumperOptions)

    val outputFile = File("app/src/main/res/raw/tmp_karten_datenbank.yml")

    outputFile.parentFile?.mkdirs() // sicherstellen, dass der Ordner existiert
    outputFile.writer(Charsets.UTF_8).use { writer ->
        yaml.dump(root, writer)
    }

    println("tmp_karten_datenbank.yml erfolgreich geschrieben: ${outputFile.absolutePath}")
}
