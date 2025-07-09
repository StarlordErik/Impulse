package de.seleri.spielelemente

/** kürzere Schreibeweise von attributToYamlZeile() */
fun atyz(anzahlEinrueckungen: Int, attributsname: String, attributswert: Any?) =
    attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

fun testAttribute(): Map<String, Any?> {
    val collectionAnSpielelementen = setOf<LokalisierbaresSpielelement>(
        // TODO dummy Objekte (in LokalisierbaresSpielementTest.kt erstellen)
    ) as Collection<LokalisierbaresSpielelement>

    // keys sind die Klassentypen der Attributstypen
    return mapOf(
        "String" to "String",
        "Collection<LokalisierbaresSpielelement>" to collectionAnSpielelementen,
        "Map<Sprache, String>" to mapOf(Sprachen.OG to "Test"),
        "null" to null
    )
}

class UtilsTest {


}