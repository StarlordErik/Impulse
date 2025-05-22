# Impulse Wiki
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
