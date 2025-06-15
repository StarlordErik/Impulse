# SUPERWICHTIG

## Feature Branches
Jeder Feature-Branch muss folgende Namenskonvention einhalten:
```
feature/name
```
Der Grund ist das Erkennen durch die CI/CD-Pipeline - im Umkehrschluss wird jeder Branch abseits feature/*, dev, main nicht getestet.

## Merge bzw. Pull-Requests 
Jeder Merge-Commit muss mit `Merge` anfangen!

So können redundante Workflows durch den Merge-Commit gestichen werden, welche bereits beim Pull-Request ausgeführt wurden. Man erkennt die Funktion an automatisch geskippten Jobs in GitHub Actions.

---

# Impulse Wiki
Was ist für dieses Projekt wichtig zu beachten?

## CI/CD-Pipeline
Die Pipeline ist vorerst voll funktionsfähig, führt diverse Tests aus und baut auch eine herunterladbare APK zum Debuggen. ABER github stellt nur begrenzt viele Github-Action-Minuten zur Verfügung. Das führte dazu, dass auf Feature-Branches beim Push nur Code-Quality-Tests durchgeführt werden. Auf dem dev-Branch wird zusätzlich noch die APK gebaut und auf dem Main-Branch werden für allen relevanten Android APIs Emulatoren erstellt und Integration-Tests durchgeführt. Vor allem die Emulatoren-Durchläufe sind extrem teuer, vor allem bei jedem kleinen Push! Das Ganze funktioniert also nur, wenn wir hauptsächlich auf den Feature-Branches arbeiten - ebenso zur Dokumentation in bspw. dieser wunderschönen ReadMe hier.

### "Kosten" von pushes
Die folgende Werte sollen nur illustrieren, was mit dem Guthaben gemeint ist. Die Prozentangabe bezieht sich auf das gesamte Monats-Guthaben an Workflow-Minuten.
feature-Branches: ca. 0,15 % --> bis zu 700 pushes pro Monat möglich
dev-Branch: ca. 0,2 % --> bis zu 500
main-Branch: ca. 1,3% --> bis zu 80

Das können wir so ohne weiteres also in keinster Weise erreichen - vorausgesetzt wir pushen nicht jede Zeile auf main. :D


# Android-App Wiki
Für ein wenig mehr knowledge sharing...

## Kotlin 
... insbesonde im Vergleich zu Java.

### kurze, wichtige Unterschiede zu Java
* kein `;` am Ende einer Zeile
* `new` wird nicht benötigt
* man kann `null` nicht mehr (so einfach) zuweisen
* Getter und Setter werden automatisch benutzt: `person.getName() --> person.name`
* expliziter cast überflüssig
* mehr fancy stuff (bspw. Hinzufügen neuer Funktionen zu bestehenden Klassen)

### Variablen
Variablen bestimmen ihren Typ selbst, wenn sie das können.
```kotlin
val unveränderbareVariable = 10  // val steht für Value
unveränderbareVariable = 12  // illegal

var veränderlich = 10
veränderlich = 12  // ez

val freundin: String = "Lea"  // gelesen: Value mit dem Namen "Freundin" vom Datentyp "String" hat den Wert "Lea"
```
Man sollte immer `val` verwenden, solange man sie nicht explizit verändern möchte --> ist sicherer.

## Wie testen wir auf den richtigen Geräten?
Wir sollten sicherstellen, dass die App zumindest auf unseren Geräten läuft, ggf. noch auf einem allgemeinen Gerät. Dafür müssen wir die Emulatoren vernünfitg konfigurieren - wobei das teilweise bloß eine schicke Spielerei ist. :D

Über die Entwickleroptionen auf dem Handy kann man auch USB-Debugging aktivieren und per Kabel direkt am eigenen Gerät testen. Das ist tendenziell sogar noch besser.

### Eriks Galaxy S20 FE
* die Anleitung zum Einrichten des Emulators befindet sich auf dieser Webseite: https://developer.samsung.com/galaxy-emulator-skin/guide.html
* der Skin-Ordner befinden sich in unserem repository im Ordner "skins"
#### notwendige Eigenschaften:
* 6,5 Zoll Bildschirmdiagonale
* Auflösung: 1080 x 2400
* Android Version 13 (Tiramisu)
* sonst Standardeinstellungen übernehmen

### Selinas Oppo

### das dritte Gerät
Standardmäßig steht einem das Google Pixel 3a oder so zur Verfügung. Das klingt doch gut! 

Aber! Wir sollten da das Betriebssystem auf unsere niedrigste, zu nutzende Android-Version (Android 10 (Q)) setzen.

## Clean Code
Als Teil der CI/CD-Pipeline ist es auch gut zu wissen, worauf man sich hier eigentlich einlässt!

Gradle ermöglicht das Checken durch Lint und detekt über eine eigene Konsole. Diese findet man hier:
1. in der IDE oben rechts auf dem Elefanten
2. kleines Fenster-Icon mit Tooltip "Execute Gradle Task"
3. eingeben: "app:detekt" oder "app:lint"
4. bei gefunden "Problemen" muss man in der Ausgabe unten links auf den zweiten Error von unten: dort werden alle Fehler gelistet

### detekt
detekt findet "schlechten" Kotlin-Code.

In root/config/detekt/detekt.yml kann man die Bedingungen zum Failen von detekt konfigurieren!
