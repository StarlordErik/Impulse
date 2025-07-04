package de.seleri.spielelemente;

import org.junit.Assert.assertEquals

fun alleDummyKategorien(): Set<Kategorie> {
    val dummyKategorien = getDummyKategorien("dummyKategorien")
    val actual = dummyKategorien.size

    val expected = 5
    assertEquals(expected, actual)

    return dummyKategorien
}

fun dummyKategorien1(): Kategorie = alleDummyKategorien().finde(1)
fun dummyKategorien2(): Kategorie = alleDummyKategorien().finde(2)
fun dummyKategorien3(): Kategorie = alleDummyKategorien().finde(3)
fun dummyKategorien4(): Kategorie = alleDummyKategorien().finde(4)
fun dummyKategorien5(): Kategorie = alleDummyKategorien().finde(5)

fun yamlDummySpiel(): String = """
        |  - $ID: $DUMMY_ID
        |    $DUMMY_NAME:
        |      ${Sprachen.OG}: "$DUMMY_OG"
        |      ${Sprachen.DE}: "$DUMMY_DE"
        |    $ORIGINALE$KATEGORIEN:
        |      $IDS: [1,2,3,4]
        |      $DAVON_ENTFERNT: [2,4]
        |    $HINZUGEFUEGTE$KATEGORIEN$BINDESTRICH_IDS: []
        |
        """.trimMargin()

fun getDummySpiele(elementart: String): Set<Spiel> =
    getDummyDaten("Spiel.yml", elementart) { Spiel.fromYaml(it, alleDummyKategorien()) }


class SpielTest {

    /*
    ------------------------------------------------------------------------------------------------
    WICHTIG: Wie ist jeder Test aufgebaut?

    1: benötigte Variablen & Konstanten instantiieren

        (1.5: ggf. wird hier expected instanziiert, falls sich nichts ändern soll)

    2: actual = Ausführung des zu testenden Codes

        (2.5: bei AssertTrue wird 2 & 3 ersetzt durch condition = Ausführung des zu testenden Codes)

    3: expected instanzieeren & Test ausführen
    ------------------------------------------------------------------------------------------------
    */

}
