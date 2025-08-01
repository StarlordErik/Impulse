package de.seleri.core.tools

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.TranslationID
import de.seleri.core.domain.model.Translation
import de.seleri.core.domain.model.idEntity.Lokalisierung
import de.seleri.core.domain.model.idEntity.spielelemente.Kartentext
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie
import de.seleri.core.domain.model.idEntity.spielelemente.Spielelement
import de.seleri.core.domain.model.idEntity.spielelemente.SpielelementDO
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.SpielMetaDO
import de.seleri.core.tools.dbsMapper.toDatenbankSlice
import de.seleri.core.tools.fromSkriptUtils.entferneNullerKategorien
import de.seleri.core.tools.fromSkriptUtils.fromSkript
import de.seleri.core.tools.fromSkriptUtils.fromSkriptForAll
import de.seleri.core.tools.fromSkriptUtils.fromSkriptForKartentexte
import kotlinx.serialization.json.Json
import java.io.File

@Suppress("MaxLineLength", "LongMethod")
fun main() {

	println("Zeit für ein neues Spiel!\n.\n.\n.")

	// Schau in die Datenbank; Was ist die höchste, vergebene ID der jeweiligen Tabellen?

	var maxTranslationID = 0
	var maxLokalisierungID = 0
	var maxKartentextID = 0
	var maxKategorieID = 0
	var maxSpielID = 0

	// In welcher Sprache ist das Spiel geschrieben?

	val ogSprache = Sprache.DE

	// Spiel:

	val spielNameOG: String = "Erzählt euch mehr - Klassik"
	val spielNameERIK: String = "Erzählt euch mehr - Klassik"
	val spielNameDE: String = ""
	val spielNameEN: String = ""

	// Kategorien:

	val kategorie1NameOG: String = "Gedankenspiel"
	val kategorie1NameERIK: String = "Gedankenspiel"
	val kategorie1NameDE: String = ""
	val kategorie1NameEN: String = ""

	val kategorie2NameOG: String = "Kreuzverhör"
	val kategorie2NameERIK: String = "Kreuzverhör"
	val kategorie2NameDE: String = ""
	val kategorie2NameEN: String = ""

	val kategorie3NameOG: String = "Selbstreflexion"
	val kategorie3NameERIK: String = "Selbstreflexion"
	val kategorie3NameDE: String = ""
	val kategorie3NameEN: String = ""

	val kategorie4NameOG: String = ""
	val kategorie4NameERIK: String = ""
	val kategorie4NameDE: String = ""
	val kategorie4NameEN: String = ""

	// Kartentexte:

	val kartentexte1TexteOG: List<String> = listOf(
		"Du kannst einem Menschen auf dieser Welt einen Herzenswunsch erfüllen.\n\nWem erfüllst du welchen Wunsch?",
		"Du kannst auf eine persönliche Hygieneroutine verzichten, ohne, dass es sich negativ auswirkt.\n\nWelche Routine wählst du aus?",
		"Du kannst einen Tag in deinem Leben nochmal leben.\n\nWelchen Tag wählst du aus?",
		"Du bekommst die Möglichkeit, (noch) einmal zu studieren.\n\nFür welchen Studiengang entscheidest du dich?",
		"Du kannst für einen Tag die Darstellung des Google Logos bestimmen.\n\nWie würde es aussehen?",
		"Die Biografie welches deiner Verwandten würdest du am liebsten lesen und warum?",
		"Du kannst der weltweit führende Experte in einer bestimmten Nische sein.\n\nWelche Nische wählst du?",
		"Von all deinen Besitztümern darfst du lediglich fünf behalten.\n\nFür welche entscheidest du dich?",
		"Du hast eine fünfminütige Audienz beim Papst.\n\nWorüber sprichst du mit ihm?",
		"Du bist sowohl geografisch als auch finanziell unabhängig.\n\nWie und wo lebst du?",
		"Du findest heraus, dass dein gesamtes Leben ein einziger Traum ist. Du kannst jetzt entscheiden, ob du aufwachst oder in deinem Traum weiterlebst.\n\nWas tust du?",
		"Du kannst genau einen Umstand an der Art, wie du aufgewachsen bist, ändern.\n\nWofür entscheidest du dich?",
		"Du kannst deine tägliche Schlafzeit problemlos halbieren.\n\nWofür nutzt du die neu gewonnene Zeit?",
		"Du bist Bildungsminister und erarbeitest die Schulinhalte für die nächsten Jahre.\n\nWas muss zwingend auf die Lehrpläne?",
		"Du wirst für eine Woche lang mit einer Superkraft deiner Wahl ausgestattet.\n\nFür welche Superkraft entscheidest du dich?",
		"Du bekommst 100.000€, um einen Raum in deinem bestehenden Zuhause nach deinen Wünschen umzugestalten.\n\nWelchen Raum wählst du und was änderst du?",
		"Du bist mit einem seltenen Virus infiziert. Ärzte teilen dir mit, dass du nur noch eine Woche zu leben hast. Du bist mobil und frei beweglich.\n\nWie gestaltest du deine letzte Woche?",
		"Du reist in die Vergangenheit und hast ein fünfminütiges Gespräch mit deinem zehnjährigen Ich.\n\nWas erzählst du ihm?",
		"Du hast einen Freifahrtschein in der Schönheitsklinik. Würdest du etwas ändern lassen?\n\nFalls ja, was?",
		"Du wirst nach deinem Ableben einmalig wiedergeboren und kannst komplett frei bestimmen als was.\n\nWofür entscheidest du dich?",
		"Unter sämtlichen Menschen dieser Welt darfst du drei zum Abendessen einladen. Sie werden sicher erscheinen.\n\nWen lädst du ein?"
	)
	val kartentexte1TexteERIK: List<String> = listOf(
		"",
		"Du kannst auf eine persönliche Hygieneroutine verzichten, ohne dass es sich negativ auswirkt.\n\nWelche Routine wählst du aus?",
		"",
		"",
		"Du kannst für einen Tag die Darstellung des Google-Logos bestimmen.\n\nWie würde es aussehen?",
		"",
		"Du kannst der weltweit führende Experte* in einer bestimmten Nische sein.\n\nWelche Nische wählst du?",
		"Von all deinen Besitztümern darfst du lediglich 5 behalten.\n\nFür welche entscheidest du dich?",
		"Du hast eine 5-minütige Audienz beim Papst.\n\nWorüber sprichst du mit ihm?",
		"",
		"",
		"",
		"",
		"Du bist Bildungsminister* und erarbeitest die Schulinhalte für die nächsten Jahre.\n\nWas muss zwingend auf die Lehrpläne?",
		"",
		"",
		"Du bist mit einem seltenen Virus infiziert. Ärzte* teilen dir mit, dass du nur noch eine Woche zu leben hast. Du bist mobil und frei beweglich.\n\nWie gestaltest du deine letzte Woche?",
		"Du reist in die Vergangenheit und hast ein 5-minütiges Gespräch mit deinem 10-jährigen Ich.\n\nWas erzählst du deinem jüngeren Ich?",
		"",
		"",
		"Unter sämtlichen Menschen dieser Welt darfst du 3 zum Abendessen einladen. Sie werden sicher erscheinen.\n\nWen lädst du ein?"
	)
	val kartentexte1TexteDE: List<String> = listOf()
	val kartentexte1TexteEN: List<String> = listOf()

	val kartentexte2TexteOG: List<String> = listOf(
		"Würdest du lieber ...\n\n ... das Weltall oder den Ozean erkunden?\n ... den ganzen Tag lang Anzug oder Jogginganzug tragen?\n ... alle Sprachen dieser Welt oder alle Instrumente dieser Welt beherrschen?",
		"Bevorzugst du ...\n\n ... Podcast oder Hörbuch hören?\n ... Online Shopping oder in Geschäfte gehen?\n ... Nachrichten schreiben oder telefonieren?\n ... Sport machen oder Sport gucken?",
		"Wie informierst du dich? Nenne jeweils 3 ...\n\n ... Websites\n ... Printerzeugnisse\n ... TV Formate\n ... Podcasts",
		"Teile je Stichwort eine Kindheitserinnerung:\n\n-Süßigkeit\n-TV Serie\n-Gesellschaftsspiel\n-beste(r) Freund(in)",
		"Dein Lieblingsgetränk ...\n\n ... beim Frühstück?\n ... im Kino?\n ... im Club?\n ... auf der Arbeit?",
		"Bist du eher ...\n\n ... ein Fluss,\n ... ein See,\n ... ein Meer oder\n ... ein Wasserfall?\n\nWarum?",
		"Verzichtest du eher auf ...\n\n ... Kaffee oder Alkohol?\n ... Fleisch oder Fisch?\n ... die Fähigkeit zu schreiben oder die Fähigkeit zu lesen?\n ... 1,5 Monatsgehälter oder deinen Jahresurlaub?",
		"Regnerischer Spielnachmittag, aber wie?\n\n-Karten oder Brettspiel?\n-Wii oder Playstation?\n-Fifa oder Mario Kart?\n-Heißer Kakao oder Bier?",
		"Du hast eine eigene Minibar im Haus. Was darf nicht fehlen?\n\n-Wein oder Bier?\n-Klarer oder Kräuterschnaps?\n-Coca-Cola oder Red Bull?\n-Schokolade oder Erdnüsse?",
		"Wieviel Bargeld hast du in diesem Moment bei dir?\n\nWas war deine letzte Anschaffung < 100€?\n\nWas war deine letzte Anschaffung > 100€?\n\nWofür sparst du gerade?"
	)
	val kartentexte2TexteERIK: List<String> = listOf(
		"",
		"Wann bevorzugst du ...\n\n ... Podcast- oder Hörbuch-Hören?\n ... Online Shopping oder in Geschäfte Gehen?\n ... Nachrichten-Schreiben oder Telefonieren?\n ... Sport-Machen oder Sport-Gucken?",
		"",
		"Teile je Stichwort eine Kindheitserinnerung:\n\n- Süßigkeit\n- TV Serie\n- Gesellschaftsspiel\n- bester Freund*",
		"Dein Lieblingsgetränk ...\n\n ... beim Frühstück?\n ... im Kino?\n ... im Club?\n ... auf der Arbeit? (oder Schule/Uni)",
		"",
		"",
		"Regnerischer Spielnachmittag, aber wie?\n\n- Karten- oder Brettspiel?\n- Konsole oder PC?\n- Fifa oder Mario Kart?\n- Heißer Kakao oder Bier?",
		"Du hast eine eigene Minibar im Haus. Was darf nicht fehlen?\n\n- Wein oder Bier?\n- Klarer oder Kräuterschnaps?\n- Coca-Cola oder Red Bull?\n- Schokolade oder Erdnüsse?",
		"- Wieviel Bargeld hast du in diesem Moment bei dir?\n\n- Was war deine letzte Anschaffung unter 100€?\n\n- Was war deine letzte Anschaffung über 100€?\n\n- Wofür sparst du gerade?",
	)
	val kartentexte2TexteDE: List<String> = listOf()
	val kartentexte2TexteEN: List<String> = listOf()

	val kartentexte3TexteOG: List<String> = listOf(
		"Worauf freust du dich momentan ganz besonders?",
		"Was war dein persönlich größter Erfolg in deinem Leben?",
		"Wofür bewunderst du andere Menschen?",
		"Wo möchtest du im Leben stehen, wenn du deinen nächsten runden Geburtstag erreichst?",
		"Was war dein Berufswunsch als Kind und wie denkst du heute darüber?",
		"Gibt es etwas, woran du gerade voller Passion arbeitest?",
		"Was ist dein Lieblingsgeruch und was verbindest du mit ihm?",
		"Was hast du irgendwann einmal getan, wofür du dich heute ernsthaft schämst?",
		"Worüber hast du das letzte Mal so sehr gelacht, dass deine Augen anfingen zu tränen?",
		"Was ist, deiner Meinung nach, der größte Unterschied zwischen uns beiden?",
		"Welche Entscheidung, die du getroffen hast, hat dein Leben am stärksten beeinflusst?",
		"Welche Eigenschaft anderer Menschen macht dich wahnsinnig?",
		"Wofür gibst du gerne Geld aus und schaust auch nicht zwingend auf das Preisschild?",
		"Wann und in welcher Situation hast du das letzte Mal jemanden oder etwas aufgegeben?",
		"Was tust du, um dich selbst glücklich zu machen?",
		"Was tust du regelmäßig und immer wieder, obwohl es dir absolut nicht gefällt?",
		"Was ist momentan deine größte Herausforderung?",
		"Welcher Mensch hat dich besonders inspiriert und warum?",
		"Was ist das Beste daran, du zu sein?",
		"Was tust du, um andere Menschen glücklich zu machen?",
		"Was ist dein wichtigstes Ziel für die nächsten 6 Monate?",
		"Was haben wir deiner Meinung nach gemeinsam?",
		"Warst du jemals sehr beunruhigt oder ängstlich wegen einer Sache, die sich im Nachgang als halb so wild entpuppte?",
		"Welches Kompliment ist dir besonders in Erinnerung geblieben?",
		"Beschreibe, wie ein Buch und ein Film dich besonders beeinflusst haben.",
		"Wann hast du das letzte Mal in Gegenwart einer anderen Person geweint und warum?",
		"Welches Ereignis oder welche Person hat zuletzt deine Sichtweise auf ein bestimmtes Thema signifikant geändert?",
		"Was war das Hilfreichste, das du in letzter Zeit gelernt hast?",
		"Wie sieht dein perfekter Sonntagabend aus?",
		"Was ist deine aktuell größte Sorge?",
		"Was war dein stolzester Moment in den letzten 12 Monaten?",
		"Was verbessert deine Laune schlagartig?",
		"Beschreibe deine Morgenroutine.",
		"Was bedeutet es für dich ein glückliches Leben zu führen?",
		"Was denken viele Leute über dich, ist deiner Meinung nach aber nicht zutreffend?",
		"Erkläre den Einfluss deiner Kinderstube an der Art, wie du ...\n... Urlaub machst.\n... Weihnachten feierst.\n... in den Tag startest.",
		"Wann hast du dir das letzte Mal einen Rat oder eine Meinung eingeholt?\n\nZu wem gehst du in solchen Fällen?",
		"Wann war dein letzter richtig mieser Tag?\n\nWas ist geschehen?",
		"Gibt es etwas, von dem du schon lange träumst es zu tun?\n\nWas hielt dich bisher davon ab, es zu tun?"
	)
	val kartentexte3TexteERIK: List<String> = listOf(
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"",
		"Was bedeutet es für dich, ein glückliches Leben zu führen?",
		"",
		"",
		"",
		"",
		"Gibt es etwas, von dem du schon lange träumst, es zu tun?\n\nWas hielt dich bisher davon ab, es zu tun?"
	)
	val kartentexte3TexteDE: List<String> = listOf()
	val kartentexte3TexteEN: List<String> = listOf()

	val kartentexte4TexteOG: List<String> = listOf()
	val kartentexte4TexteERIK: List<String> = listOf()
	val kartentexte4TexteDE: List<String> = listOf()
	val kartentexte4TexteEN: List<String> = listOf()

	// ------------------------------------ EINGABE BIS HIERHIN UND NICHT WEITER ------------------------------------

	val startTranslationsID = maxTranslationID
	val startLokalisierungID = maxLokalisierungID
	val startKartentextID = maxKartentextID
	val startKategorieID = maxKategorieID
	val startSpielID = maxSpielID

	var translationsCounter = startTranslationsID

	// Eingabe to Translationen - fürs Spiel:

	val spielOGinfo = Translation.fromSkript(++maxTranslationID, Sprache.OG, spielNameOG)
	val spielOG = spielOGinfo.first
	translationsCounter += 1 + spielOGinfo.second

	val spielERIKinfo = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, spielNameERIK)
	val spielERIK = spielERIKinfo.first
	translationsCounter += 1 + spielERIKinfo.second

	val spielDEinfo = Translation.fromSkript(++maxTranslationID, Sprache.DE, spielNameDE)
	val spielDE = spielDEinfo.first
	translationsCounter += 1 + spielDEinfo.second

	val spielENinfo = Translation.fromSkript(++maxTranslationID, Sprache.EN, spielNameEN)
	val spielEN = spielENinfo.first
	translationsCounter += 1 + spielENinfo.second

	// Eingabe to Translationen - für die Kategorien:

	val kategorie1OGinfo = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie1NameOG)
	val kategorie1OG = kategorie1OGinfo.first
	translationsCounter += 1 + kategorie1OGinfo.second

	val kategorie1ERIKinfo = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie1NameERIK)
	val kategorie1ERIK = kategorie1ERIKinfo.first
	translationsCounter += 1 + kategorie1ERIKinfo.second

	val kategorie1DEinfo = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie1NameDE)
	val kategorie1DE = kategorie1DEinfo.first
	translationsCounter += 1 + kategorie1DEinfo.second

	val kategorie1ENinfo = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie1NameEN)
	val kategorie1EN = kategorie1ENinfo.first
	translationsCounter += 1 + kategorie1ENinfo.second

	val kategorie2OGinfo = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie2NameOG)
	val kategorie2OG = kategorie2OGinfo.first
	translationsCounter += 1 + kategorie2OGinfo.second

	val kategorie2ERIKinfo = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie2NameERIK)
	val kategorie2ERIK = kategorie2ERIKinfo.first
	translationsCounter += 1 + kategorie2ERIKinfo.second

	val kategorie2DEinfo = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie2NameDE)
	val kategorie2DE = kategorie2DEinfo.first
	translationsCounter += 1 + kategorie2DEinfo.second

	val kategorie2ENinfo = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie2NameEN)
	val kategorie2EN = kategorie2ENinfo.first
	translationsCounter += 1 + kategorie2ENinfo.second

	val kategorie3OGinfo = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie3NameOG)
	val kategorie3OG = kategorie3OGinfo.first
	translationsCounter += 1 + kategorie3OGinfo.second

	val kategorie3ERIKinfo = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie3NameERIK)
	val kategorie3ERIK = kategorie3ERIKinfo.first
	translationsCounter += 1 + kategorie3ERIKinfo.second

	val kategorie3DEinfo = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie3NameDE)
	val kategorie3DE = kategorie3DEinfo.first
	translationsCounter += 1 + kategorie3DEinfo.second

	val kategorie3ENinfo = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie3NameEN)
	val kategorie3EN = kategorie3ENinfo.first
	translationsCounter += 1 + kategorie3ENinfo.second

	val kategorie4OGinfo = Translation.fromSkript(++maxTranslationID, Sprache.OG, kategorie4NameOG)
	val kategorie4OG = kategorie4OGinfo.first
	translationsCounter += 1 + kategorie4OGinfo.second

	val kategorie4ERIKinfo = Translation.fromSkript(++maxTranslationID, Sprache.ERIK, kategorie4NameERIK)
	val kategorie4ERIK = kategorie4ERIKinfo.first
	translationsCounter += 1 + kategorie4ERIKinfo.second

	val kategorie4DEinfo = Translation.fromSkript(++maxTranslationID, Sprache.DE, kategorie4NameDE)
	val kategorie4DE = kategorie4DEinfo.first
	translationsCounter += 1 + kategorie4DEinfo.second

	val kategorie4ENinfo = Translation.fromSkript(++maxTranslationID, Sprache.EN, kategorie4NameEN)
	val kategorie4EN = kategorie4ENinfo.first
	translationsCounter += 1 + kategorie4ENinfo.second

	// Eingabe to Translationen - für die Kartentexte:

	val kartentexte1OGinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.OG, kartentexte1TexteOG)
	val kartentexte1OG = kartentexte1OGinfo.first
	maxTranslationID += (kartentexte1OGinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte1OGinfo.second / Sprache.entries.size)

	val kartentexte1ERIKinfo =
		Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.ERIK, kartentexte1TexteERIK)
	val kartentexte1ERIK = kartentexte1ERIKinfo.first
	maxTranslationID += (kartentexte1ERIKinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte1ERIKinfo.second / Sprache.entries.size)

	val kartentexte1DEinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.DE, kartentexte1TexteDE)
	val kartentexte1DE = kartentexte1DEinfo.first
	maxTranslationID += (kartentexte1DEinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte1DEinfo.second / Sprache.entries.size)

	val kartentexte1ENinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.EN, kartentexte1TexteEN)
	val kartentexte1EN = kartentexte1ENinfo.first
	maxTranslationID += (kartentexte1ENinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte1ENinfo.second / Sprache.entries.size)

	val kartentexte2OGinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.OG, kartentexte2TexteOG)
	val kartentexte2OG = kartentexte2OGinfo.first
	maxTranslationID += (kartentexte2OGinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte2OGinfo.second / Sprache.entries.size)

	val kartentexte2ERIKinfo =
		Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.ERIK, kartentexte2TexteERIK)
	val kartentexte2ERIK = kartentexte2ERIKinfo.first
	maxTranslationID += (kartentexte2ERIKinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte2ERIKinfo.second / Sprache.entries.size)

	val kartentexte2DEinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.DE, kartentexte2TexteDE)
	val kartentexte2DE = kartentexte2DEinfo.first
	maxTranslationID += (kartentexte2DEinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte2DEinfo.second / Sprache.entries.size)

	val kartentexte2ENinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.EN, kartentexte2TexteEN)
	val kartentexte2EN = kartentexte2ENinfo.first
	maxTranslationID += (kartentexte2ENinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte2ENinfo.second / Sprache.entries.size)

	val kartentexte3OGinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.OG, kartentexte3TexteOG)
	val kartentexte3OG = kartentexte3OGinfo.first
	maxTranslationID += (kartentexte3OGinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte3OGinfo.second / Sprache.entries.size)

	val kartentexte3ERIKinfo =
		Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.ERIK, kartentexte3TexteERIK)
	val kartentexte3ERIK = kartentexte3ERIKinfo.first
	maxTranslationID += (kartentexte3ERIKinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte3ERIKinfo.second / Sprache.entries.size)

	val kartentexte3DEinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.DE, kartentexte3TexteDE)
	val kartentexte3DE = kartentexte3DEinfo.first
	maxTranslationID += (kartentexte3DEinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte3DEinfo.second / Sprache.entries.size)

	val kartentexte3ENinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.EN, kartentexte3TexteEN)
	val kartentexte3EN = kartentexte3ENinfo.first
	maxTranslationID += (kartentexte3ENinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte3ENinfo.second / Sprache.entries.size)

	val kartentexte4OGinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.OG, kartentexte4TexteOG)
	val kartentexte4OG = kartentexte4OGinfo.first
	maxTranslationID += (kartentexte4OGinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte4OGinfo.second / Sprache.entries.size)

	val kartentexte4ERIKinfo =
		Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.ERIK, kartentexte4TexteERIK)
	val kartentexte4ERIK = kartentexte4ERIKinfo.first
	maxTranslationID += (kartentexte4ERIKinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte4ERIKinfo.second / Sprache.entries.size)

	val kartentexte4DEinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.DE, kartentexte4TexteDE)
	val kartentexte4DE = kartentexte4DEinfo.first
	maxTranslationID += (kartentexte4DEinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte4DEinfo.second / Sprache.entries.size)

	val kartentexte4ENinfo = Translation.fromSkriptForKartentexte(++maxTranslationID, Sprache.EN, kartentexte4TexteEN)
	val kartentexte4EN = kartentexte4ENinfo.first
	maxTranslationID += (kartentexte4ENinfo.second / Sprache.entries.size) + Sprache.entries.size - 1
	translationsCounter += 1 + (kartentexte4ENinfo.second / Sprache.entries.size)

	// Translationen in Lokalisierung gruppieren - für das Spiel:

	val spielLokalisierungInfo =
		Lokalisierung.fromSkript(++maxLokalisierungID, ogSprache, spielOG, spielERIK, spielDE, spielEN)
	val spielLokalisierung = spielLokalisierungInfo.first
	maxLokalisierungID += spielLokalisierungInfo.second
	translationsCounter += spielLokalisierungInfo.third

	// Translationen in Lokalisierung gruppieren - für die Kategorien:

	val kategorie1LokalisierungInfo = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie1OG, kategorie1ERIK, kategorie1DE, kategorie1EN
	)
	val kategorie1Lokalisierung = kategorie1LokalisierungInfo.first
	maxLokalisierungID += kategorie1LokalisierungInfo.second
	translationsCounter += kategorie1LokalisierungInfo.third

	val kategorie2LokalisierungInfo = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie2OG, kategorie2ERIK, kategorie2DE, kategorie2EN
	)
	val kategorie2Lokalisierung = kategorie2LokalisierungInfo.first
	maxLokalisierungID += kategorie2LokalisierungInfo.second
	translationsCounter += kategorie2LokalisierungInfo.third

	val kategorie3LokalisierungInfo = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie3OG, kategorie3ERIK, kategorie3DE, kategorie3EN
	)
	val kategorie3Lokalisierung = kategorie3LokalisierungInfo.first
	maxLokalisierungID += kategorie3LokalisierungInfo.second
	translationsCounter += kategorie3LokalisierungInfo.third

	val kategorie4LokalisierungInfo = Lokalisierung.fromSkript(
		++maxLokalisierungID, ogSprache, kategorie4OG, kategorie4ERIK, kategorie4DE, kategorie4EN
	)
	val kategorie4Lokalisierung = kategorie4LokalisierungInfo.first
	maxLokalisierungID += kategorie4LokalisierungInfo.second
	translationsCounter += kategorie4LokalisierungInfo.third

	// Translationen in Lokalisierung gruppieren - für die Kartentexte:

	val kartentexte1LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte1OG, kartentexte1ERIK, kartentexte1DE, kartentexte1EN
	)
	val kartentexte1Lokalisierungen = kartentexte1LokalisierungsInfos.first
	maxLokalisierungID += kartentexte1LokalisierungsInfos.second
	translationsCounter += kartentexte1LokalisierungsInfos.third

	val kartentexte2LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte2OG, kartentexte2ERIK, kartentexte2DE, kartentexte2EN
	)
	val kartentexte2Lokalisierungen = kartentexte2LokalisierungsInfos.first
	maxLokalisierungID += kartentexte2LokalisierungsInfos.second
	translationsCounter += kartentexte2LokalisierungsInfos.third

	val kartentexte3LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte3OG, kartentexte3ERIK, kartentexte3DE, kartentexte3EN
	)
	val kartentexte3Lokalisierungen = kartentexte3LokalisierungsInfos.first
	maxLokalisierungID += kartentexte3LokalisierungsInfos.second
	translationsCounter += kartentexte3LokalisierungsInfos.third

	val kartentexte4LokalisierungsInfos = Lokalisierung.fromSkriptForKartentexte(
		++maxLokalisierungID, ogSprache, kartentexte4OG, kartentexte4ERIK, kartentexte4DE, kartentexte4EN
	)
	val kartentexte4Lokalisierungen = kartentexte4LokalisierungsInfos.first
	maxLokalisierungID += kartentexte4LokalisierungsInfos.second
	translationsCounter += kartentexte4LokalisierungsInfos.third

	// Lokalisierungen zu Kartentexten:

	val kartentexte1Infos = Kartentext.fromSkriptForAll(++maxKartentextID, kartentexte1Lokalisierungen)
	val kartentexte1 = kartentexte1Infos.first
	maxKartentextID += kartentexte1Infos.second

	val kartentexte2Infos = Kartentext.fromSkriptForAll(++maxKartentextID, kartentexte2Lokalisierungen)
	val kartentexte2 = kartentexte2Infos.first
	maxKartentextID += kartentexte2Infos.second

	val kartentexte3Infos = Kartentext.fromSkriptForAll(++maxKartentextID, kartentexte3Lokalisierungen)
	val kartentexte3 = kartentexte3Infos.first
	maxKartentextID += kartentexte3Infos.second

	val kartentexte4Infos = Kartentext.fromSkriptForAll(++maxKartentextID, kartentexte4Lokalisierungen)
	val kartentexte4 = kartentexte4Infos.first
	maxKartentextID += kartentexte4Infos.second

	// Kartentexte zu Kategorien (mit Lokalisierungen):

	val kategorie1Info = fromSkript(++maxKategorieID, kategorie1Lokalisierung, kartentexte1)
	val kategorie1 = kategorie1Info.first
	maxKategorieID += kategorie1Info.second

	val kategorie2Info = fromSkript(++maxKategorieID, kategorie2Lokalisierung, kartentexte2)
	val kategorie2 = kategorie2Info.first
	maxKategorieID += kategorie2Info.second

	val kategorie3Info = fromSkript(++maxKategorieID, kategorie3Lokalisierung, kartentexte3)
	val kategorie3 = kategorie3Info.first
	maxKategorieID += kategorie3Info.second

	val kategorie4Info = fromSkript(++maxKategorieID, kategorie4Lokalisierung, kartentexte4)
	val kategorie4 = kategorie4Info.first
	maxKategorieID += kategorie4Info.second

	val kategorien = entferneNullerKategorien(listOf(kategorie1, kategorie2, kategorie3, kategorie4))

	// Kategorien zu Spiel (mit Lokalisierungen):

	val spielCursed = fromSkript(++maxSpielID, spielLokalisierung, kategorien)

	// Fix der Translations-IDs anhand ihrer Lokalisierungs-IDs:

	val spiel = fixeTranslationsIDsGlobal(spielCursed)

	println("Fertig!\n")

	// -------------------------------- SPIEL WURDE ERSTELLT - NUN ZUR AUSWERTUNG -------------------------------------

	println("\"$spielNameOG\" wurde erstellt! \n")

	val anzahlSpiele = maxSpielID - startSpielID
	println("Statistik für $anzahlSpiele Spiel:")

	val anzahlKategorien = maxKategorieID - startKategorieID
	println("\t$anzahlKategorien Kategorien")

	val anzahlKartentexte = maxKartentextID - startKartentextID
	println("\t$anzahlKartentexte Kartentexte")

	val anzahlLokalisierungen = maxLokalisierungID - startLokalisierungID
	println("\t$anzahlLokalisierungen Lokalisierungen")

	val anzahlTranslationen = translationsCounter - startTranslationsID
	println("\t$anzahlTranslationen Translationen (der Wert stimmt probably nicht)\n\n")

	// --------------------------------- SPIEL ZUM CHECK IN EINE DATEI SCHREIBEN --------------------------------------

	val outputName = toDateiname(spielNameOG)

	val outputFile = File("core/tools/build/neue_Spiele/$outputName/Check.txt")
	outputFile.parentFile.mkdirs()

	val content = buildString {
		appendLine("Das ist der konkrete Inhalt - zum Checken, ob der Code das richtige getan hat:\n")

		// Spiel:

		appendLine("Spiel:")
		appendLine("\tSpielID: ${spiel.id.value}")
		append(spielelementToTXT(spiel))

		// Kategorien:
		appendLine("\nKategorien:")
		spiel.bestandteile.forEach { kategorie ->
			appendLine("\tKategorieID: ${kategorie.id.value}")
			append(spielelementToTXT(kategorie))
		}

		// Kartentexte:
		appendLine("\nKartentexte:")
		spiel.bestandteile.forEach { kategorie ->
			appendLine("  ${kategorie.lokalisierung.translationen.first().bezeichnung}:")
			kategorie.bestandteile.forEach { kartentext ->
				appendLine("\tKartentextID: ${kartentext.id.value}")
				append(spielelementToTXT(kartentext))
			}
		}
	}

	println(content)

	outputFile.writeText(content)
	val uri = outputFile
		.toPath()
		.toUri()
		.toString()
	println("Check-Datei gespeichert in: $uri\n")

	// ------------------------------------------ NUN IN DIE DATENBANK -----------------------------------------------

	val dbs = spiel.toDatenbankSlice()

	dbsToJson(dbs.spiele, "spiele", outputName)
	dbsToJson(dbs.kategorien, "kategorien", outputName)
	dbsToJson(dbs.kartentexte, "kartentexte", outputName)
	dbsToJson(dbs.lokalisierungen, "lokalisierungen", outputName)
	dbsToJson(dbs.translationen, "translationen", outputName)
	dbsToJson(dbs.spielXkategorienEntity, "spiel_x_kategorie", outputName)
	dbsToJson(dbs.kategorieXkartentexteEntity, "kategorie_x_kartentext", outputName)
}

private inline fun <reified T> dbsToJson(tabelle: List<T>, name: String, outputName: String) {
	val jsonDatei = File("core/tools/build/neue_Spiele/$outputName/$name.json")
	jsonDatei.parentFile.mkdirs()

	val json = Json.encodeToString(tabelle)

	jsonDatei.writeText(json)
	val uri = jsonDatei
		.toPath()
		.toUri()
		.toString()
	println("\"$name\"-Tabelle: $uri")
}

private fun fixeTranslationsIDsGlobal(spiel: Spiel): Spiel {
	val neueKategorien = mutableListOf<Kategorie>()

	spiel.bestandteile.forEach { kategorie ->
		val neueKartentexte = mutableListOf<Kartentext>()

		kategorie.bestandteile.forEach { kartentext ->
			neueKartentexte += kartentext.copy(
				spielelementDaten = SpielelementDO(
					lokalisierung = fixeTranslationsIDs(kartentext.lokalisierung)
				)
			)
		}

		neueKategorien += kategorie.copy(
			spielelementDaten = SpielelementDO(
				lokalisierung = fixeTranslationsIDs(kategorie.lokalisierung)
			), bestandteile = neueKartentexte
		)
	}

	return spiel.copy(
		spielMetaDaten = SpielMetaDO(spielelementDaten = SpielelementDO(lokalisierung = spiel.lokalisierung)),
		bestandteile = neueKategorien
	)
}

private fun fixeTranslationsIDs(lokalisierung: Lokalisierung): Lokalisierung {
	val lokalisierungID = lokalisierung.id.value
	val ogID = (lokalisierungID - 1) * Sprache.entries.size + 1
	val erikID = ogID + 1
	val deID = erikID + 1
	val enID = deID + 1

	val neueTranslationen = mutableListOf<Translation>()

	lokalisierung.translationen.forEach { translation ->
		neueTranslationen += when (translation.sprache) {
			Sprache.OG -> translation.copy(id = TranslationID(ogID))
			Sprache.ERIK -> translation.copy(id = TranslationID(erikID))
			Sprache.DE -> translation.copy(id = TranslationID(deID))
			Sprache.EN -> translation.copy(id = TranslationID(enID))
		}
	}

	return lokalisierung.copy(translationen = neueTranslationen)
}

private fun spielelementToTXT(spielElement: Spielelement): String =
	buildString {
		appendLine("\t\tLokalisierungID: ${spielElement.lokalisierung.id.value}")
		appendLine("\t\tTranslID | Spr. | Bezeichnung")
		spielElement.lokalisierung.translationen.forEach {
			val id = it.id.id.toString()
			val sprache = it.sprache.toString()
			val bezeichnung = it.bezeichnung
				.replace("\n", "\\n")
				.replace("\t", "\\t")
			appendLine("\t\t\t${in4Zeichen(id)} | ${in4Zeichen(sprache)} | $bezeichnung")
		}
	}

private fun in4Zeichen(input: String): String {
	val anzahlZeichen = 1 + 1 + 1 + 1
	return input
		.take(anzahlZeichen)
		.padStart(anzahlZeichen, ' ')
}

private fun toDateiname(input: String): String {
	val replacements = mapOf(
		'ä' to "ae", 'ö' to "oe", 'ü' to "ue", 'Ä' to "Ae", 'Ö' to "Oe", 'Ü' to "Ue", 'ß' to "ss", ' ' to "_"
	)

	val sanitized = buildString {
		for (char in input) {
			append(
				replacements[char]
					?: when {
						char.isLetterOrDigit() || char == '_' || char == '-' -> char
						else -> "" // Entferne ungültige Zeichen
					}
			)
		}
	}

	return sanitized.lowercase()
}
