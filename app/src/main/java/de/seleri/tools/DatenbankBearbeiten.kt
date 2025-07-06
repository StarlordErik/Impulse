package de.seleri.tools

import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Sprachen

@Suppress("LongMethod", "MaxLineLength")
fun main() {

    println("Hallo Welt!\n")

    // Hier die Daten eingeben - für bestehende Sammlungen die Namen aus der Datenbank kopieren:

    val spielName = "Privacy"
    val kategorieName = "2er"
    val sprache = Sprachen.DE
    val kartenTexte = listOf( // wird nur bei ID != 0 beachtet
        "Ich habe mich schon mal an jemandem gerächt.",
        "Eine Partnerschaft ohne Sex ist für mich ausgeschlossen.",
        "Ich schaue sehr viel Fernsehen.",
        "Ich habe schon mal einem Partner nachspioniert.",
        "Ich hatte schon mal Sex mit dem/der Ex.",
        "Ich bin aus der Kirche ausgetreten.",
        "Ich bin als Erwachsener schon mal bewusst schwarzgefahren.",
        "Hätte ich genügend Geld, würde ich nicht mehr arbeiten.",
        "Ich würde mein Leben heute anders leben.",
        "Veganer sind bessere Menschen.",
        "Ich sitze oft mit dem Handy auf der Toilette.",
        "Ich habe schon mal etwas aus einem Geschäft gestohlen.",
        "Ich habe schon mal Analsex ausprobiert.",
        "Ich mag keine Hunde.",
        "Ich könnte einen Seitensprung meines Partners verzeihen.",
        "Zweifelsfrei überführte Mörder oder Kinderschänder sollten zum Tode verurteilt werden.",
        "Wenn niemand da ist, fahre ich über eine rote Ampel.",
        "Ich hatte schon mal einen Quickie in der Küche.",
        "Ich hatte schon mal Videocall-Sex.",
        "Ich habe schon mal an einer politischen Demonstration teilgenommen.",
        "Ich rede hin und wieder mit Pflanzen.",
        "Ich hatte schon mal Sex am Strand.",
        "Aus eigener Erfahrung weiß ich: Dumm poppt gut.",
        "Ich hatte schon mal Telefonsex.",
        "Ich habe schon mal jemandem die Freundin/den Freund ausgespannt.",
        "Mein Penis bzw. Busen ist zu klein.",
        "Jemand mit mehr als 20 Kilo Übergewicht kommt für mich als Partner nicht in Frage.",
        "Ich habe mich in den letzten 4 Wochen selbst befriedigt.",
        "Ich würde gerne (noch) mal in einen Swingerclub gehen.",
        "Ich bin schon mal per Anhalter gefahren.",
        "Ich drücke gerne Pickel aus.",
        "Ich habe schon mal jemandem leidenschaftlich die Füße geküsst.",
        "Für den richtigen Partner würde ich auch in ein fremdes Land ziehen.",
        "Ich habe schon mal einen Orgasmus vorgetäuscht.",
        "Mir war meine Familie mal so richtig peinlich.",
        "Ich musste schon mal bei einer Polizeikontrolle ins Röhrchen pusten.",
        "Ich wünsche mir innerhalb der nächsten drei Jahre (noch) ein Kind.",
        "Ich hatte schon mal Sex im Schlafzimmer meiner Eltern.",
        "Männer können besser Auto fahren als Frauen.",
        "Mein rechter Nachbar sieht älter aus, als er ist.",
        "Ich finde es gut, wenn sich gleichgeschlechtliche Paare in der Öffentlichkeit küssen.",
        "Vegan zu leben ist nur ein Trend.",
        "Im Nachhinein betrachtet bereue ich meine Partnerwahl für das Erste Mal.",
        "Ich war schon mal länger als ein halbes Jahr arbeitslos gemeldet.",
        "Ich gehe häufig nicht zur Wahl - bringt ja sowieso nichts.",
        "Ich wurde schon mal von einem Gericht verurteilt.",
        "Ich habe schon mal Versicherungsbetrug begangen.",
        "Ich spreche auf Spaziergängen manchmal laut mit mir selbst.",
        "Ein Partner hat mir mal einen Seitensprung gebeichtet.",
        "Ich finde mich attraktiv.",
        "Ich hatte in den letzten 4 Wochen bitterlich geweint.",
        "Ich habe schon mal jemanden entjungfert.",
        "Ich habe schon mal einem Erwachsenen den Hintern abgewischt.",
        "Ich habe schon mal jemandem klipp und klar gesagt, dass ich ihn nicht leiden kann.",
        "Ich habe schon mal ein sexuelles Angebot von einer fremden Person erhalten.",
        "Ich bin in meinem Verhalten sehr umweltbewusst.",
        "Ich parke hin und wieder auf Behindertenparkplätzen.",
        "Ich besitze Sexspielzeug.",
        "Ich habe ein Geheimnis, das ich niemandem erzählen würde.",
        "Der Einsatz von Atombomben ist unter keinen Umständen gerechtfertigt.",
        "Ich habe mir im letzten Monat einen Porno angesehen.",
        "Ich hätte kein Problem damit, mich jetzt und hier in Unterwäsche zu präsentieren.",
        "Ich habe schon mal etwas ins Netz gestellt, das mir hinterher peinlich war.",
        "Ich hatte schon mal sexuelle Gedanken über den Partner meiner besten Freundin/meines besten Freundes.",
        "Ich habe einen schönen Hintern.",
        "Ich schlafe zu wenig.",
        "Ich pinkle manchmal unter der Dusche.",
        "Ich habe heute geduscht oder gebadet.",
        "Ich hatte schon mal Gefühle für das gleiche Geschlecht.",
        "Ich fürchte mich davor, wie meine Eltern zu werden.",
        "Ich vertraue den aktuellen Nachrichten nicht mehr.",
        "Ich habe schon mal Psychopharmaka eingenommen.",
        "Mein Konto ist am Ende des Monats immer in den roten Zahlen.",
        "Ich würde mein Leben immer wieder so leben.",
        "Bei meinem Ersten Mal war ich älter als 18 Jahre.",
        "Ich habe schon mal gelogen, um eine Party abzusagen.",
        "Wenn mir jemand gefällt, dann habe ich kein Problem, ihn/sie anzusprechen.",
        "Ich bin schon mal in der Schule oder auf der Arbeit eingeschlafen.",
        "Ich habe mir schon mal am Telefon etwas aufschwatzen lassen.",
        "Ich liebe meine Familie.",
        "Ich bin schon mal sexuell belästigt worden.",
        "Für viel Geld würde ich für immer auf Sex verzichten.",
        "Ich habe schon mal anonym falsche Tatsachen im Social Media verbreitet.",
        "Mein linker Nachbar kleidet sich sehr hübsch.",
        "Aktive Sterbehilfe finde ich in Ordnung.",
        "Ich zeige mich ungern in einer öffentlichen Umkleidekabine nackt.",
        "Sexualstraftäter müssen deutlich härter bestraft werden.",
        "Ich hatte schon mal einen obszönen Anruf."
    )

    // oben die Daten eingeben


    val dbs = Datenbanksystem.generieren()

    val neueKarten = dbs.neueKarten(kartenTexte, sprache)
    println("Folgende KartenIDs wurden eingegeben: ${neueKarten.map { it.id }}")

    val neueKategorie = dbs.neueKategorie(kategorieName, neueKarten, sprache)
    println("Folgende KategorienID wurden eingegeben: ${neueKategorie.id}:${neueKategorie.localizations[sprache]}")
    println("Sie besitzt folgende Karten:          ${neueKategorie.getAlleKarten().map { it.id }}")

    val neuesSpiel = dbs.neuesSpiel(spielName, listOf(neueKategorie), sprache)
    println("Folgende SpielID wurde eingegeben: ${neuesSpiel.id}:${neuesSpiel.localizations[sprache]}")
    println("Das Spiel besitzt folgende Kategorien: ${neuesSpiel.getAlleKategorien().map { it.localizations[sprache] }}")

}


