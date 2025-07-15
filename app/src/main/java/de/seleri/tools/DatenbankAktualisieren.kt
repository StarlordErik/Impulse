package de.seleri.tools

import de.seleri.backend.Datenbanksystem

fun main() {
  val dbs = Datenbanksystem.generieren()

  dbs.aktualisieren()
}
