package de.seleri.tools

import de.seleri.backend.DATENBANK_PFAD
import java.io.File

/**
 * ersetzt alle Vorkommen von "" durch null in der Datenbank
 */
@Suppress("LongMethod", "MaxLineLength")
fun main() {
  val file = File(DATENBANK_PFAD)

  // Lese den kompletten Dateiinhalt als String
  val content = file.readText()

  // Ersetze alle Vorkommen von "" durch null
  val updatedContent = content.replace(": \"\"", ":")

  // Schreibe den neuen Inhalt zurück in die Datei
  file.writeText(updatedContent)
}
