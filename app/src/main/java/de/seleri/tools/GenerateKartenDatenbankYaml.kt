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
            "text" to mapOf(
                "de" to "",
                "en" to sentence
            ),
            "ursprungsKategorienID" to 1,
            "weitereKategorienIDs" to listOf(2, 3),
        )
    }

    val root: Map<String, Any> = mapOf("karten" to cardsList)

    val dumperOptions = DumperOptions().apply {
        defaultFlowStyle = DumperOptions.FlowStyle.BLOCK
        isPrettyFlow = true
        isExplicitStart = false
        isExplicitEnd = false
    }
    val yaml = Yaml(dumperOptions)

    // Dump to string first
    val rawYaml = yaml.dump(root)

    // Post-process to adjust formatting to match "Datei 2"
    val formattedYaml = rawYaml
        .replace(Regex("weitereKategorienIDs:\\s*\\n\\s*- 2\\n\\s*- 3"), "weitereKategorienIDs: [2, 3]") // inline list
        .replace("''", "\"\"") // leere Strings mit doppelten Anführungszeichen
        .replace(Regex("en: (.+)")) { matchResult ->
            val text = matchResult.groupValues[1]
            if (text.startsWith("\"")) matchResult.value else "en: \"${text}\""
        }
        .replace(Regex("(?m)(^- id: \\d+\n.*?)^(?=- id:|\\z)", RegexOption.DOT_MATCHES_ALL)) {
            // Abschnitt eines Karten-Elements mit Leerzeile danach
            it.value.trimEnd() + "\n\n"
        }

    val outputFile = File("app/src/main/res/raw/tmp_karten_datenbank.yml")
    outputFile.parentFile?.mkdirs()
    outputFile.writeText(formattedYaml)

    println("tmp_karten_datenbank.yml erfolgreich geschrieben: ${outputFile.absolutePath}")
}
