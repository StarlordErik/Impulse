package de.seleri.skripte

import de.seleri.spielelemente.Sprachen

@Suppress("UNCHECKED_CAST")
fun main(){

    // Hier die Daten eingeben:

    val spielID : Int = 0 // 0 für ein neues Spiel
    val spielName : String = "" // wird nur bei ID != 0 beachtet

    val kategorieID: Int = 0 // 0 für eine neue Kategorie
    val kategorieName = "" // wird nur bei ID != 0 beachtet

    var kartenIDs = listOf( // 0 für neue Karten
        0
    )
    val kartenTexte = listOf( // wird nur bei ID != 0 beachtet
        "",
        ""
    )
    val sprache = Sprachen.DE // Sprache der Karten

    // oben die Daten eingeben
/*
    val datenbank = datenbankAusYamlGenerieren()

    // printe alles aus der Datenbank
    println("$KARTEN:")
    val karten = datenbank["Karten"] ?: emptyList()
    karten.forEach { karte ->
        print((karte as Karte).toYaml())
    }
    println("\n$KATEGORIEN:")
    val kategorien = datenbank["Kategorie"] ?: emptyList()
    kategorien.forEach { kategorie ->
        print((kategorie as Kategorie).toYaml())
    }
    println("\n$SPIELE:")
    val spiele = datenbank["Spiel"] ?: emptyList()
    spiele.forEach { spiel ->
        print((spiel as Spiel).toYaml())
    }
*/






}
/*
fun datenbankAusYamlGenerieren(): MutableMap<String, List<Any>> {
    val yaml = Yaml()
    val file = File("app/src/main/res/raw/karten_datenbank.yml")
    val inputStream = file.inputStream()
    val data = yaml.load<Map<String, Any>>(inputStream)

    val karten = (data[KARTEN] as List<Map<String, Any>>).map {
        val yamlString = yaml.dump(listOf(it)) // yamlToKarte erwartet eine Liste mit einem Map
        yamlToKarte(yamlString)
    }

    val kategorien = (data[KATEGORIEN] as List<Map<String, Any>>).map {
        val yamlString = yaml.dump(listOf(it))
        yamlToKategorie(yamlString, karten)
    }
    val spiele = (data[SPIELE] as List<Map<String, Any>>).map {
        val yamlString = yaml.dump(listOf(it))
        yamlToSpiel(yamlString, kategorien)
    }


    return mutableMapOf(
        "Karten" to karten,
        "Kategorie" to kategorien,
        "Spiel" to spiele
    )
}

*/
