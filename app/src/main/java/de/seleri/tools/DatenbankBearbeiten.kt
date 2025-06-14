package de.seleri.tools

import de.seleri.spielelemente.Sprachen
import de.seleri.spielelemente.datenbanksystemGenerieren

@Suppress("LongMethod", "MaxLineLength")
fun main() {

    println("Hallo Welt!")

    // Hier die Daten eingeben:

    // val spielID : Int = 0 // 0 für ein neues Spiel
    val spielName = "Erzählt euch mehr - für Paare" // wird nur bei ID != 0 beachtet

    // val kategorieID: Int = 0 // 0 für eine neue Kategorie
    val kategorieName = "Reflexion" // wird nur bei ID != 0 beachtet

    val sprache = Sprachen.DE // Sprache der Karten
    /*
    var kartenIDs = listOf( // 0 für neue Karten
        0
    )
    */
    val kartenTexte = listOf( // wird nur bei ID != 0 beachtet
        "Welchen Aspekt unserer Beziehung möchtest du anderen Paaren gerne zur Nachahmung empfehlen?",
        "Was möchtest du in unserer Beziehung gerne anders machen?",
        "Wie habe ich mich, in deinen Augen, im Laufe unserer Beziehung verändert?",
        "Findest du unseren Umgang mit Geld ausgeglichen oder muss einer öfter in die Tasche greifen?",
        "Über welches Thema sprichst du nur ungern mit mir?",
        "Was tust du ganz aktiv für diese Beziehung?",
        "Was hast du getan, was sich als nicht gut für unsere Beziehung herausgestellt hat?",
        "Was in unserer Beziehung macht dir ein ungutes Bauchgefühl?",
        "Was glaubst du ist unsere größte gemeinsame Schwäche?",
        "Was unterscheidet unsere Beziehung von deinen vorherigen?",
        "Gibt es etwas in unserer gemeinamen Zeit, das du bereust?",
        "Beschreibe, wie es ist, mit mir in einer Beziehung zu sein.",
        "In welchen gemeinsamen Augenblicken bist du besonders glücklich?",
        "Was ist deine Lieblingsbeschäftigung für uns beide?",
        "Wieviele Tage ohne Sex sind zu viel für dich?",
        "Welche Erwartungen hast du an unsere Beziehung?",
        "Vertraust du mir immer deine innersten Gedanken an?",
        "Hast du sexuelle Fantasien, denen wir bisher keine Aufmerksamkeit geschenkt haben?",
        "Was glaubst du ist unsere größte gemeinsame Stärke?",
        "Worin bin ich meiner Mutter und worin meinem Vater ähnlich?",
        "Wie stellst du dir unser Leben in 5 Jahren vor?",
        "Wie möchtest du, dass ich dir meine Liebe zeige?",
        "Geben wir unserer Liebe im Alltag genug Ausdruck?",
        "Wie gefällt dir der Kontakt zu unseren Familien?",
        "Was macht uns als Paar kompatibel?",
        "Was wünschst du dir von mir für unsere Zukunft?",
        "Was wünschst du dir für unsere Kommunikation?",
        "Wie sieht deine Vorstellung von einem perfekten Date aus?",
        "Wieviel Raum brauchst du für dich allein?",
        "Was tue ich, das dich verletzt?\n\nWarum verletzt es dich?",
        "Gibt es etwas, das du von mir brauchst, aber derzeit nicht bekommst?",
        "Wie zeigst du mir deine Liebe?",
        "Welche Angewohnheiten von mir empfindest du als störend?\n\nAus welchen Gründen ist das so?",
        "In welcher Situation war ich dir peinlich?\n\nWarum?",
        "Wie hast du die Beziehung deiner Eltern wahrgenommen?\n\nHaben sie etwas besonders richtig oder falsch gemacht?",
        "Was sollte ich niemals zu dir sagen? - egal, wie wütend ich bin."
    )

    // oben die Daten eingeben


    val dbs = datenbanksystemGenerieren()

    val neueKarten = dbs.neueKarten(kartenTexte, sprache)
    println("Folgende KartenIDs wurden eingegeben: ${neueKarten.map { it.id }}")

    val neueKategorie = dbs.neueKategorie(kategorieName, neueKarten, sprache)
    println("Folgende KategorienID wurden eingegeben: ${neueKategorie.id}")
    println("Sie besitzt folgende Karten: ${neueKategorie.getAlleKarten().map { it.id }}")

    val neuesSpiel = dbs.neuesSpiel(spielName, listOf(neueKategorie), sprache)
    println("Folgende SpielID wurde eingegeben: ${neuesSpiel.id}")
    println("Sie besitzt folgende Kategorien: ${neuesSpiel.getAlleKategorien().map { it.id }}")

}


