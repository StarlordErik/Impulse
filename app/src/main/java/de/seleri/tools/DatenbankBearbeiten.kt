package de.seleri.tools

import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Sprachen

@Suppress("LongMethod", "MaxLineLength")
fun main() {

    println("Hallo Welt!\n")

    // Hier die Daten eingeben - für bestehende Sammlungen die Namen aus der Datenbank kopieren:

    val spielName = "Privacy"
    val kategorieName = "3er"
    val sprache = Sprachen.DE
    val kartenTexte = listOf( // wird nur bei ID != 0 beachtet
        "Ich habe schon mal heimlich im Handy meines Partners spioniert.",
        "Behaarung im Intimbereich ist erotisch.",
        "Ich habe mich schon mal heimlich von einem Date weggeschlichen.",
        "Ich habe mich schon mal selbst nackt fotografiert.",
        "Ich habe aktuell mehr als 10.000 Euro auf dem Girokonto.",
        "Ich habe mir als Erwachsener schon mal in die Hosen gemacht.",
        "Ich habe schon mal Fake-Profile von mir auf einer Dating-Plattform angelegt.",
        "Ich habe schon einmal gelebt.",
        "Ich hatte an einem Tag mit 2 Personen (unabhängig voneinander) Sex.",
        "Ich genieße es, mich hin und wieder selbst intim zu berühren.",
        "Mein rechter Nachbar könnte deutlich mehr aus sich machen.",
        "Ich habe als Erwachsener schon mal ins Schwimmbad gepinkelt.",
        "Ich habe in den letzten 6 Monaten in der Bibel oder im Koran gelesen.",
        "Es gibt jemanden, den ich sehr vermisse und an den ich fast täglich denken muss.",
        "Ich hatte mal einen One-Night-Stand und konnte mich an den Namen danach nicht mehr erinnern.",
        "Ich singe heimlich bei guter fremdsprachiger Musik lauthals mit, obwohl ich den Text nicht wirklich kenne.",
        "Ich möchte meinem Partner/meiner Partnerin beim Masturbieren zusehen.",
        "Ich habe mal etwas in einer fremden Wohnung kaputt gemacht - und nichts gesagt.",
        "Künstliche Intelligenz gefährdet die Menschheit.",
        "Ich bin intelligenter als mein rechter Nachbar.",
        "Intimschmuck finde ich erotisch.",
        "Ich schaue immer auf den Hintern einer attraktiven Person.",
        "Es gibt eine Person (nicht mein Partner/meine Partnerin), die in mich verliebt ist.",
        "Für 10.000 Euro würde ich mich nackt in der Zeitung abbilden lassen.",
        "Schläge in der Kindererziehung gehören verboten.",
        "Ich habe mal Fahrerflucht begangen.",
        "Ich trage manchmal zwei Tage nacheinander dieselbe Unterwäsche.",
        "Ich könnte nie auf Fleisch beim Essen verzichten.",
        "Ich bin schon mal in einer Beziehung fremdgegangen.",
        "Ich habe schon mal eine Diät gemacht.",
        "Ich hatte in den letzten 12 Monaten mit mehr als einer Person Sex.",
        "Unter gewissen Umständen könnte ich mir vorstellen, einen Menschen zu töten.",
        "Ich hatte schon mal eine übersinnliche Wahrnehmung.",
        "Ich habe schon mal fremdgeküsst.",
        "Ich spreche mit meinem besten Freund/meiner besten Freundin über Intimes.",
        "Ich habe schon mal einen Mitspieler/eine Mitspielerin gegoogelt.",
        "Ich habe jemanden in meinem Freundeskreis, mit dem ich gerne schlafen würde.",
        "Ich habe mich schon mal für jemand anderen ausgegeben.",
        "Ich bin schon mal überfallen worden.",
        "Ich hatte schon mal eine Geschlechtskrankheit.",
        "Ich bin schon mal nackt im Meer/See/Freibad geschwommen.",
        "Homosexualität ist völlig normal.",
        "Ich hatte mich mal mit den Eltern eines Partners/einer Partnerin überhaupt nicht verstanden.",
        "Ich würde problemlos mit dieser Spielrunde in die Sauna gehen.",
        "Ich hatte schon mal alkoholbedingt einen Filmriss.",
        "Eine Rede/Ansprache vor vielen Menschen zu halten, ist mir sehr unangenehm.",
        "Ich habe schon mal heimlich ein fremdes Tagebuch gelesen.",
        "Ich hänge immer noch an einem/einer meiner Expartner/Expartnerinnen.",
        "Ich bin schon mal über einen Zaun geklettert, um das Eintrittsgeld zu sparen.",
        "Ich trage gerne Masken beim Sex.",
        "Ich habe schon mal einen Striptease gemacht.",
        "Ich habe mir beim Sex mal einen Gegenstand einführen lassen, der nicht für diesen Zweck bestimmt ist.",
        "Bei meinem ersten Zungenkuss war ich jünger als 13 Jahre.",
        "Ab einem gewissen Minimum ist Frauen die Penisgröße egal.",
        "Ich hätte gerne Sex in einer Kirche.",
        "Ich habe schon mal vor jemandem gepupst und dann so getan, als ob ich es nicht war.",
        "Ich habe schon mal wegen eines neuen Partners eine Beziehung beendet.",
        "Ich wünschte, ich wäre wieder ein Kind.",
        "Ich habe sexuelle Fantasien.",
        "Ich habe heute schon ein Kompliment bekommen.",
        "Ich würde gern mal Busen-Sex ausprobieren.",
        "Ich habe schon mal einen Vibrator ins Liebesspiel mit einbezogen.",
        "Abtreibung ist Mord.",
        "Ich hatte schon mal Sex an einem ungewöhnlichen Ort.",
        "Ich kann mit meinem Partner/meiner Partnerin über meine intimsten Wünsche sprechen.",
        "Ich habe vor Verzweiflung schon mal Rotz und Wasser geheult.",
        "Ich habe in den letzten 2 Monaten mindestens ein komplettes Buch gelesen.",
        "Ich habe schon mal etwas völlig Durchgeknalltes getan.",
        "Meine rechter Nachbar hat einen schönen Mund.",
        "Ich hatte schon mal Sex im Flugzeug.",
        "Ich habe mal eine Liebeserklärung von einer unbekannten Person erhalten.",
        "Ich bin schon mal beim Sex erwischt worden.",
        "Mein linker Nachbar ist mir sehr sympathisch.",
        "Ich hatte schon mal eine offene Beziehung.",
        "Ich hatte als Erwachsener schon mal Läuse.",
        "Ich habe schon mal in großer Runde heimlich gepupst, sodass es heftig gestunken hat.",
        "Ich habe in den letzten vier Wochen mit einem/einer Fremden geflirtet.",
        "Ich nehme Shampoo und Duschgel aus Hotelzimmern mit.",
        "Ich habe mal etwas getan, für das ich mich heute schäme.",
        "Ich habe schon mal heimlich ein Pärchen beim Liebesakt beobachtet.",
        "Ich bin beim Oralsex schon mal im Mund gekommen bzw. habe jemanden in meinem Mund kommen lassen.",
        "Ich habe einen geheimen Tick, den mein Partner/meine Partnerin nicht kennt.",
        "Die wissenschaftliche Nutzung der embryonalen Genmanipulation finde ich in Ordnung.",
        "Ich habe schon mal eine Beziehung mit einem schrecklichen Streit beendet.",
        "Ich hatte schon mal Sex zu dritt.",
        "Ich schlafe manchmal nackt.",
        "Ich kann gut küssen.",
        "Ich besitze Reizwäsche.",
        "Ich habe mal an einem Speed-Dating teilgenommen.",
        "Ich hatte schon mal Sex bei einer Verabredung über eine Dating-App.",
        "Ich trage jetzt gerade sexy Unterwäsche.",
        "Ich hätte gerne Sex mit einer Person aus dieser Runde."
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


