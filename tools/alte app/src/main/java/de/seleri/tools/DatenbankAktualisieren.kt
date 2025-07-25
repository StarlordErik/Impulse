package de.seleri.tools

import de.seleri.backend.Datenbanksystem

/**
 * Lässt das [Datenbanksystem] sich selbst aktualisieren
 * --> falls Werte manuell verändert wurden
 */
fun main() {
  val dbs = Datenbanksystem.generieren()

  dbs.aktualisieren()
}
