# Impulse Wiki
Für ein wenig mehr knowledge sharing...

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
