package de.seleri.tools

import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Sprachen

@Suppress("LongMethod", "MaxLineLength")
fun main() {

    println("Hallo Welt!\n")

    // Hier die Daten eingeben - für bestehende Sammlungen die Namen aus der Datenbank kopieren:

    val spielName = "Privacy"
    val kategorieName = "1"
    val sprache = Sprachen.DE
    val kartenTexte = listOf( // wird nur bei ID != 0 beachtet
        "Ich habe im Restaurant schon mal Essen zurückgehen lassen.",
        "Ich bin mit meiner Figur unzufrieden.",
        "Ich habe im Internet in den letzten 14 Tagen eine Sex-Seite besucht.",
        "Ich habe schon mal freiwillig einen Aids-Test gemacht.",
        "Ich könnte heute problemlos die Nacht durchmachen.",
        "Ich bin in der Schule mal sitzen geblieben bzw. zurückgestuft worden.",
        "Ich habe schon mal einem Menschen in Not geholfen.",
        "Ich habe Schwierigkeiten, mit meinem Geld auszukommen.",
        "Ich habe schon mal einen Toten gesehen.",
        "Ich habe schon mal Drogen genommen.",
        "Ich finde unrasierte Beine sehr unattraktiv.",
        "Ich habe schon mal jemanden auf den Mund geküsst, den ich eklig fand.",
        "Ich treibe zu wenig Sport.",
        "Ich war schon mal heimlich in einen Kollegen/eine Kollegin oder jemanden aus dem Freundeskreis verliebt.",
        "Ich glaube nicht, dass ich bis zu meinem Lebensende treu sein kann.",
        "Ich wurde in den letzten 6 Monaten wegen zu schnellem Fahren geblitzt.",
        "Ich hatte schon mal eine echte Lebenskrise.",
        "Ich habe heute schon über jemanden gelästert.",
        "Ich habe regelmäßig Verstopfung.",
        "Ich habe schon mal eine beendete Beziehung neu begonnen.",
        "Ich war schon mal im Bordell.",
        "Ich bin charmant.",
        "Aus meinem engsten Familienkreis hat schon mal jemand im Knast gesessen.",
        "Ich habe schon mal etwas in einem Sexshop gekauft.",
        "Mir hat schon mal jemand gesagt, dass ich Mundgeruch habe.",
        "Ich habe schon mal jemanden, der nicht mein Partner/meine Partnerin war, meine Liebe online gestanden.",
        "Ich wünsche mir mehr Oralsex.",
        "Ich trage hin und wieder keine Unterwäsche.",
        "Ich hatte schon mal Sex im Zug.",
        "Ich habe heute schon gelogen.",
        "Ich bin stolz auf meine Nationalität.",
        "Ich habe als Erwachsener schon mal einen Popel unter den Tisch/Stuhl geschmiert.",
        "Ich wäre oft lieber woanders.",
        "Ich bin mindestens einmal durch die Führerscheinprüfung gefallen.",
        "Ich hatte schon mal ein gleichgeschlechtliches erotisches Erlebnis.",
        "Ich habe schon mal absichtlich fremde Post geöffnet.",
        "Ich habe mich schon mal von jemandem nackt fotografieren lassen.",
        "Ich habe schon mal jemandem heimlich in sein Essen/Trinken gespuckt.",
        "Von mir gibt es professionelle Aktfotos.",
        "Ich hatte schon mal ein Verhältnis mit einer verheirateten Person.",
        "Sex mit Kondom ist abturnend.",
        "Ich bin schon mal von einer Zecke gebissen worden.",
        "Ich habe schon mal meinen Hochzeitstag oder einen Geburtstag vergessen.",
        "Ich spiele zu viel am Computer.",
        "Ich war schon mal im Fernsehen zu sehen.",
        "Ich sage in einer neuen Partnerschaft die Wahrheit über die Anzahl früherer Sexpartner.",
        "Ich habe schon mal Sex-Stellungen aus einem Buch/Film gezielt nachgemacht.",
        "Ich war schon mal in psychotherapeutischer Behandlung.",
        "Auf einer Party habe ich mal etwas getan, das mir danach sehr peinlich war.",
        "Ich habe schon mal sexy Bilder oder Videos von mir im Netz hochgeladen.",
        "Ich bin im Intimbereich rasiert.",
        "Ich wüsste gern meinen Todestag.",
        "Influencer finde ich gut.",
        "Ich habe mich schon mal beim Liebesspiel gefilmt.",
        "Ich bin schon mal betrunken Auto gefahren.",
        "Bei der Wahl des Partners ist mir Aussehen zweitrangig.",
        "Kinderschänder und Vergewaltiger sollten kastriert werden.",
        "Ich war schon mal auf einer Erotikmesse.",
        "Ich hatte schon mal ein Treffen über eine Dating-App.",
        "Ich habe mich schon mal beim Sex fesseln lassen.",
        "Die Wiedervereinigung Deutschlands war richtig.",
        "Ich musste mich schon mal unangenehm im Analbereich behandeln lassen.",
        "Ich besitze noch einen Liebesbrief aus einer früheren Beziehung.",
        "Ich hatte an einem Tag mehr als dreimal Sex.",
        "Ich hatte schon mal Sex im Freien.",
        "Ich habe mir schon mal mit einem Partner/einer Partnerin einen Porno angesehen.",
        "Ich hatte außerhalb einer Beziehung schon ungeschützten Sex.",
        "Auch Menschen über 50 können sehr attraktiv sein.",
        "Ich bin morgens sehr schnell im Bad.",
        "Ich wurde heute schon intim berührt.",
        "Ich wäre gerne ein berühmter Star.",
        "Das Leben ist toll.",
        "Ich habe ein psychologisches Problem, das möglicherweise therapiebedürftig ist.",
        "Ich hatte beim Sex schon mal einen Partnertausch.",
        "Ich hatte schon mal Sex auf einem Konzert.",
        "Ich habe schon mal in der Badewanne gepupst und mich über die Bläschen gefreut.",
        "Ich hatte schon mal einen lustigen oder schmerzhaften Sex-Unfall.",
        "Ich hatte schon mal Sex im Aufzug.",
        "Mein linker Nachbar sieht gut aus.",
        "Ich benutze beim Sex gerne schweinische Wörter.",
        "Ich habe schon mal meinen Führerschein abgeben müssen.",
        "Ich habe als Erwachsener schon mal FKK gemacht.",
        "Mir wurde schon mal von einer fremden Person an den Hintern gefasst.",
        "Es gibt etwas, das ich meinen Eltern vorwerfe.",
        "Ich fahre gerne sehr schnell Auto.",
        "Ich habe Angst vor der Arbeitslosigkeit.",
        "Ich kann mir vorstellen, ein Kind zu adoptieren.",
        "Ich habe schon mal Geld gestohlen."
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


