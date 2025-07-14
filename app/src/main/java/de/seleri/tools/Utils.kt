package de.seleri.tools

import de.seleri.spielelemente.DATENBANK_PFAD
import de.seleri.spielelemente.Datenbanksystem
import java.nio.file.Paths

/**
 * Erstellt ein neues `Datenbanksystem` auf Basis der Datei im Ressourcenverzeichnis.
 * **Funktioniert nur zur Entwicklungszeit!**
 *
 * @return Instanz des Datenbanksystems mit geladenen Daten
 */
fun Datenbanksystem.Companion.generieren(): Datenbanksystem {
    val dateipfad = Paths.get(DATENBANK_PFAD)
    return Datenbanksystem(dateipfad.toFile())
}
