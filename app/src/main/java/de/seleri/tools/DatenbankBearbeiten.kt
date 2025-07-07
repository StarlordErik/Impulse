package de.seleri.tools

import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.Sprachen

@Suppress("LongMethod", "MaxLineLength")
fun main() {

    println("Hallo Welt!\n")

    // Hier die Daten eingeben - für bestehende Sammlungen die Namen aus der Datenbank kopieren:

    val spielName = "Privacy"
    val kategorieName = "4er"
    val sprache = Sprachen.DE
    val kartenTexte = listOf( // wird nur bei ID != 0 beachtet
        "Ich wäre gerne 10 Jahre jünger.",
        "Lehrer müssen auch bei frechen Schülern die Ruhe bewahren.",
        "Ich hatte schon mal Depressionen.",
        "Ich bin für die Homo-Ehe.",
        "Ich habe Nacktfotos von einer bestimmten Person im Internet gesucht.",
        "Mein linker Nachbar sollte sich eine neue Frisur zulegen.",
        "Ich besitze mindestens ein Nacktfoto eines Expartners/einer Expartnerin.",
        "Ich habe mindestens zwei richtig gute Freunde, die fast alles über mich wissen.",
        "Ich habe schon mal einer fremden Person gesagt, dass ich gerne mit ihm/ihr schlafen möchte.",
        "Ich hätte gerne mal Sex mit einer bestimmten berühmten Persönlichkeit.",
        "Ich hatte schon mal ein Blind-Date.",
        "Ich habe meinen Partner schon mal ins Geschlechtsteil gebissen.",
        "Ich hatte schon mal einen gleichgeschlechtlichen Zungenkuss.",
        "Ich habe heute jemanden leidenschaftlich geküsst.",
        "Ich glaube an Gott.",
        "Ich habe sehr häufig Albträume/Schlafstörungen.",
        "Ich habe schon mal von Sozialhilfe oder Hartz IV gelebt.",
        "Ich hatte schon mal Sex im Auto.",
        "Ich hatte schon Sex unter der Dusche oder in der Badewanne.",
        "Ich würde lieber einen anderen Beruf ausüben.",
        "Ich habe Angst vor der Zukunft.",
        "Ich habe eine exhibitionistische Neigung.",
        "Ich bin gegen jede Art von Tierversuchen.",
        "Oralsex ist eklig.",
        "Ich habe schon mal jemanden angezeigt oder anonym angeschwärzt.",
        "Ich habe schon mal einen Striptease in einem Lokal oder in einer Bar gesehen.",
        "Ich habe einem Internetflirt mal ein Nacktfoto oder ein falsches Foto gemailt.",
        "Ich hätte kein Problem damit, jetzt und hier ein Lied vorzusingen.",
        "Ich habe mal einen Fehler begangen, den ich mir niemals verzeihen werde.",
        "Es gibt jemanden, dem ich gesagt habe, dass ich nichts mehr mit ihm/ihr zu tun haben will.",
        "Ich stehe auf Spanking.",
        "Ich habe Angst vor dem Sterben und denke sehr häufig daran.",
        "Meine Eltern haben sich in meiner Kindheit zu wenig um mich gekümmert.",
        "Ich fühle mich ausreichend geliebt.",
        "Es gibt etwas beim Sex, nach dem ich mich sehne, es aber nicht bekomme.",
        "Ich hatte schon mal einen heftigen Nachbarschaftsstreit.",
        "Ich bin in den letzten 3 Monaten in ein peinliches Fettnäpfchen getreten.",
        "Sexspielzeug finde ich überbewertet.",
        "Ich hatte schon mal ein total verkrampftes Rendezvous.",
        "Ich habe schon mal etwas Unanständiges in einer Kirche getan.",
        "Ich putze mir jeden Tag die Zähne.",
        "Ich bin ein guter Verlierer.",
        "Ich habe etwas gemacht, das meine Eltern nie erfahren dürfen.",
        "Ich war schon mal richtig stolz auf meine Familie/Eltern.",
        "Ein Teil dieser Fragen erregt mich erotisch.",
        "Die Frau beim Sex oben gefällt mir wesentlich besser, als wenn sie unten ist.",
        "Ich hatte schon mal Sex mit einem Kollegen oder einer Kollegin.",
        "Ich denke ständig an Sex.",
        "Ich war oder bin lange in jemanden verliebt, habe es ihm/ihr aber nie gesagt.",
        "Ich habe mir schon mal ein Autogramm geben lassen.",
        "Mein Partner/meine Partnerin geht mir regelmäßig auf die Nerven.",
        "Ich weiß, was Polyamorie bedeutet.",
        "Ich habe schon mal versucht, einen Expartner/eine Expartnerin zurückzugewinnen.",
        "Wenn ich auf Reisen gehe, habe ich immer Kondome dabei.",
        "Ich habe in den letzten 3 Monaten Kondome gekauft.",
        "Eines meiner wichtigsten Lebensziele habe ich erreicht.",
        "Mein Erstes Mal war alles andere als toll.",
        "Ich habe/hatte ein Suchtproblem.",
        "Ich habe schon mal Nacktfotos von mir in die sozialen Medien gestellt.",
        "Ich hatte in meinem Leben mehr als 10 Sexualpartner.",
        "Ich hatte nicht mehr als 2 feste Partnerschaften.",
        "Ich habe jetzt gerade Blähungen.",
        "Ich bin schon mal am Steuer eingenickt.",
        "Ich habe schon mal einer wildfremden Person gesagt, dass ich ihn/sie toll finde.",
        "Ich habe als Kind meine Eltern beim Sex gesehen.",
        "Ich habe schon mal SM praktiziert.",
        "Ich wünsche mir mehr Zärtlichkeit.",
        "Ich habe beim Sex mal einen weiblichen Multiorgasmus (mit)erlebt.",
        "Es gibt mindestens einen Expartner/eine Expartnerin, zu dem/der ich gar keinen Kontakt mehr habe.",
        "In den letzten 2 Jahren hatte ich mit jemandem eine handgreifliche Auseinandersetzung.",
        "Ich würde mir einen Chip unter die Haut setzen lassen.",
        "In der Schule wurde ich gehänselt.",
        "Ich habe schon mal jemanden mit der Hand zum Orgasmus gebracht.",
        "Ich habe in den letzten 6 Monaten mit jemandem geknutscht, der nicht mein Partner war.",
        "Ich habe einen sexuellen Fetisch.",
        "Ich bin gut im Bett.",
        "Wer Arbeit sucht, findet auch eine.",
        "Ich trage schon mal verschiedene Socken.",
        "Ich meditiere regelmäßig.",
        "Ich weiß, was Packaging bedeutet.",
        "Mir ist schon mal ein Kondom geplatzt.",
        "Ich war mal mit jemandem länger als ein Jahr zusammen, obwohl ich ihn/sie nicht (mehr) liebte.",
        "Ich würde gerne Tanzstunden nehmen.",
        "Ich sehe mich ungern nackt im Spiegel.",
        "Ich habe schon mal jemanden geküsst, dessen Namen ich nicht kannte.",
        "Ich bin sehr selbstbewusst.",
        "Monogamie ist langweilig.",
        "Ich bin schon mal von einer Partnerin/einem Partner betrogen worden.",
        "Ich würde gerne noch (mal) Sex zu dritt ausprobieren.",
        "Ich hatte in den letzten 48 Stunden Sex.",
        "Ich bin schon mal von einer Partnerin/einem Partner verlassen worden.",
        "Ich habe schon mal für Sex bezahlt."

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


