package de.seleri.core.tools

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import java.io.File

@Suppress("MaxLineLength")
fun main() {
	println("Hello Se Wörldu!\n")

	val ogSprache = Sprache.DE
	val spielName = "Erzählt euch mehr - Klassik"

	val kategorieNamen = listOf(
		"Gedankenspiel", "Kreuzverhör", "Selbstreflexion"
	)

	val kartentextTexte = listOf(
		listOf(
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
		),
		listOf(
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
		),
		listOf(
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
		),
	)

	val kategorienMitKartentexten = kategorieNamen.zip(kartentextTexte)

	val spiel = Spiel.fromAllInOneEingabe(
		name = spielName, kategorienMitKartentexten = kategorienMitKartentexten, ogSprache = ogSprache
	)

	ausgabeInDatei(spiel)
}


private fun ausgabeInDatei(spiel: Spiel) {
	val outputFile = File("core/tools/src/main/java/de/seleri/core/tools/build/spiel.txt")
	outputFile.parentFile.mkdirs()

	val content = buildString {
		val spielLokalisierungen = spiel.lokalisierungen.map { lokalisierung ->
			lokalisierung.sprache to lokalisierung.bezeichnung
		}
		appendLine("Folgendes Spiel wurde erstellt:")
		appendLine("\"${spielLokalisierungen.first().second}\" in den Sprachen: ${spielLokalisierungen.map { it.first }}")
		appendLine()

		val kategorieLokalisierungen = spiel.bestandteile.map { kategorie ->
			kategorie.lokalisierungen.first().bezeichnung
		}
		appendLine("mit den Kategorien:")
		appendLine(kategorieLokalisierungen)
		appendLine()

		val kartentextLokalisierungen = spiel.bestandteile.map { kategorie ->
			kategorie.bestandteile.map { kartentext ->
				kartentext.lokalisierungen.map { lokalisierung ->
					lokalisierung.bezeichnung.replace("\n", "\\n")
				}
			}
		}

		val kmkLokalisierungen = kategorieLokalisierungen.zip(kartentextLokalisierungen)

		kmkLokalisierungen.forEach { kategorieMitKartentexten ->
			appendLine("${kategorieMitKartentexten.first}:")
			kategorieMitKartentexten.second.forEach { kartentext ->
				appendLine("\"${kartentext.first()}\",")
			}
			appendLine()
		}
	}

	outputFile.writeText(content)
	println(
		"Ausgabe gespeichert in: ${outputFile.absolutePath}\n" + "\t(Psst: Wenn das kein Link zur Datei ist, main() einfach nochmal ausführen.)"
	)
}

