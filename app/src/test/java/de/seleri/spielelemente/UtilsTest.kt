package de.seleri.spielelemente

import org.junit.Assert.assertEquals
import org.junit.Test

class UtilsTest {

    @Test
    fun `attributToYamlZeile() null`() {
        val anzahlEinrueckungen = 0
        val attributsname = "null"
        val attributswert = null

        val actual = attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

        val expected = "$attributsname:\n"
        assertEquals(expected, actual)
    }

    @Test
    fun `attributToYamlZeile() Integer`() {
        val anzahlEinrueckungen = 1
        val attributsname = "Integer"
        val attributswert = 0

        val actual = attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

        val expected = "  $attributsname: $attributswert\n"
        assertEquals(expected, actual)
    }

    @Test
    fun `attributToYamlZeile() Boolean`() {
        val anzahlEinrueckungen = 2
        val attributsname = "Boolean"
        val attributswert = true

        val actual = attributToYamlZeile(anzahlEinrueckungen, attributsname, attributswert)

        val expected = "    $attributsname: $attributswert\n"
        assertEquals(expected, actual)
    }

    @Test
    fun `attributToYamlZeile() String`() {
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

}