package de.seleri.spielelemente

const val KARTEN: String = "Karten"
const val KATEGORIEN: String = "Kategorien"
const val SPIELE: String = "Spiele"

private const val EIN_TAB: String = "  "
private const val ZWEI_TAB: String = "$EIN_TAB$EIN_TAB"
const val DREI_TAB: String = "$ZWEI_TAB$EIN_TAB"

const val ID: String = "ID"
const val ID_: String = "$EIN_TAB- $ID: "

const val TEXT: String = "Text"
const val TEXT__: String = "\n$ZWEI_TAB$TEXT:\n"
const val NAME: String = "Name"
const val NAME__: String = "\n$ZWEI_TAB$NAME:\n"

const val GESEHEN: String = "gesehen"
const val GESEHEN_: String = "$ZWEI_TAB$GESEHEN: "
const val GELOESCHT: String = "gelöscht"
const val GELOESCHT_: String = "\n$ZWEI_TAB$GELOESCHT: "

const val URSPRUENGLICHE: String = "ursprüngliche_"
const val TAB_URSPRUENGLICHE: String = "$ZWEI_TAB$URSPRUENGLICHE"
const val WEITERE: String = "weitere_"
const val TAB_WEITERE: String = "$ZWEI_TAB$WEITERE"
const val IDS: String = "-${ID}s"
const val IDS_: String = "$IDS: ["
const val LISTENENDE: String = "]\n"


// So sieht die Struktur in der Datenbank aus:

val KARTE = """
    |$EIN_TAB- $ID: X
    |$ZWEI_TAB$TEXT:
    |${DREI_TAB}XX: "XXX"
    |${DREI_TAB}XX: "XXX"
    |$ZWEI_TAB$GESEHEN: X
    |$ZWEI_TAB$GELOESCHT: X
    |""".trimMargin()

val KATEGORIE = """
    |$EIN_TAB- $ID: X
    |$ZWEI_TAB$NAME:
    |${DREI_TAB}XX: "XXX"
    |${DREI_TAB}XX: "XXX"
    |$TAB_URSPRUENGLICHE$KARTEN$IDS: [X,X,X]
    |$TAB_WEITERE$KARTEN$IDS: [X,X,X]
    |""".trimMargin()

val SPIEL = """
    |$EIN_TAB- $ID: X
    |$ZWEI_TAB$NAME:
    |${DREI_TAB}XX: "XXX"
    |${DREI_TAB}XX: "XXX"
    |$TAB_URSPRUENGLICHE$KATEGORIEN$IDS: [X,X,X]
    |$TAB_WEITERE$KATEGORIEN$IDS: [X,X,X]
    |""".trimMargin()

fun main() {
    println("$KARTEN:")
    print(KARTE)
    print(KARTE)
    println("\n$KATEGORIEN:")
    print(KATEGORIE)
    print(KATEGORIE)
    println("\n$SPIELE:")
    print(SPIEL)
    print(SPIEL)
}
