package de.seleri.skripte
/*
import de.seleri.spielelemente.Karte
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

    val datenbank = datenbankAusYamlGenerieren()
    val karten : MutableList<Karte> = datenbank["Karten"] as MutableList<Karte>

    if (kartenIDs.contains(0)){
        // neue Karten anlegen, bzw. erst nach Übereinstimmungen suchen
        kartenTexte.forEach { text:String ->
            karten.forEach {
                if (it.localizedTexte[sprache] == text){
                    kartenIDs.add(it.id)
                    break
                }
            }
        }
        kartenIDs = kartenIDs.drop(1)
    } else{
        // Karten-IDs nutzen, um Karten zu finden
    }


}

fun datenbankAusYamlGenerieren(): MutableMap<String, List<Any>> {
    return mutableMapOf<String, List<Any>>()
}

 */