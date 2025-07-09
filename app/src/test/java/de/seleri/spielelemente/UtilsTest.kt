package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun `attributToYamlZeile() null wird als eine Yaml-Zeile ausgegeben`() {
        val anzahlEinrueckungen = 0
        val attributsname = "null"
        val attributswert = null

        val actual = attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

        val expected = "$attributsname:\n"
        assertEquals(expected, actual)
    }

    @Test
    fun `attributToYamlZeile() Integer wird als eine Yaml-Zeile ausgegeben`() {
        val anzahlEinrueckungen = 1
        val attributsname = "Integer"
        val attributswert = 0

        val actual = attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

        val expected = "  $attributsname: $attributswert\n"
        assertEquals(expected, actual)
    }

    @Test
    fun `attributToYamlZeile() Boolean wird als eine Yaml-Zeile ausgegeben`() {
        val anzahlEinrueckungen = 2
        val attributsname = "Boolean"
        val attributswert = true

        val actual = attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

        val expected = "    $attributsname: $attributswert\n"
        assertEquals(expected, actual)
    }

    @Test
    fun `attributToYamlZeile() String wird als eine Yaml-Zeile ausgegeben`() {
        val anzahlEinrueckungen = 1
        val attributsname = "String"
        val attributswert =
            "^1234567890ß´\tqwertzuiopü+\nasdfghjklöä#<yxcvbnm,.- °!\"§$%&" + //.
                    "/()=?`QWERTZUIOPÜ*ASDFGHJKLÖÄ'>YXCVBNM;:_²³{[]}\\@€~|"

        val actual = attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

        val expectedAttributswert = "^1234567890ß´\\tqwertzuiopü+\\nasdfghjklöä#<yxcvbn" + //.
                "m,.- °!\\\"§$%&/()=?`QWERTZUIOPÜ*ASDFGHJKLÖÄ'>YXCVBNM;:_²³{[]}\\\\@€~|"
        val expected = "  $attributsname: \"$expectedAttributswert\"\n"
        assertEquals(expected, actual)
    }

    @Test
    fun `attributToYamlZeile() Collection von Spielelementen wird als eine Yaml-Zeile ausgegeben`() {
        val anzahlEinrueckungen = 1
        val attributsname = "Collection von Spielelementen"
        val attributswert = alleDummyKarten()

        val actual = attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

        val expected = "  $attributsname: [1,2,3,4,5]\n"
        assertEquals(expected, actual)
    }

    @Test
    fun `attributToYamlZeile() Map wird als eine Yaml-Zeile ausgegeben`() {
        val anzahlEinrueckungen = 1
        val attributsname = "Map"
        val attributswert = dummyLocalizations()

        val actual = attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

        // EN wird übersprungen, weil null
        val expected = """
            |  $attributsname:
            |    ${Sprachen.OG}: "$DUMMY_OG"
            |    ${Sprachen.DE}: "$DUMMY_DE"
            |""".trimMargin()
        assertEquals(expected, actual)
    }

    @Test
    fun `neueID() ID wird korrekt initialisiert, wenn Collection leer`() {
        val dummys = emptySet<DummyLokalisierbaresSpielelement>()

        val actual = neueID(dummys)

        val expected = 1
        assertEquals(expected, actual)
    }

    @Test
    fun `neueID() ID wird hoeher als die aktuellen IDs ausgegeben`() {
        val dummys = alleDummyKarten()

        val actual = neueID(dummys)

        val expected = 6
        assertEquals(expected, actual)
    }

    @Test
    fun `finde(String) Element wird anhand seiner Bezeichnung gefunden`() {
        val dummys = alleDummyKarten()

        val actual = dummys.finde("Dummy 3")

        val expected = dummyKarte3()
        assertEquals(expected, actual)
    }
}