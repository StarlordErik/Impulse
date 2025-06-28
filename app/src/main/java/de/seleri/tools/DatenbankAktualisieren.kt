package de.seleri.tools

import de.seleri.spielelemente.Datenbanksystem

fun main(){
    val dbs = Datenbanksystem.generieren()

    dbs.aktualisieren()
}