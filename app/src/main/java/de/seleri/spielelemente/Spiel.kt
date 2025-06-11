package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Spiel(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    override val originaleElemente: MutableMap<String, List<Kategorie>>,
    override var hinzugefuegteElemente: List<Kategorie>
) : SammlungAnSpielelementen<Kategorie>(
    id, localizations, originaleElemente, hinzugefuegteElemente
) {

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        output.append(originaleUndHinzugefuegteElementeToYaml(KATEGORIEN))
        return output.toString()
    }

    fun kategorienEntfernen(kategorien: List<Kategorie>) = elementeEntfernen(kategorien)
    fun kategorienHinzufuegen(kategorien: List<Kategorie>) = elementeHinzufuegen(kategorien)
    fun getAlleKategorien(): List<Kategorie> = getAlleElemente()
    fun getAlleAktuellenKategorien(): List<Kategorie> = getAlleAktuellenElemente()
    fun getAlleKarten(): List<Karte> = getAlleKategorien().flatMap { it.getAlleKarten() }.distinct()
    fun getAlleAktuellenKarten(): List<Karte> =
        getAlleKategorien().flatMap { it.getAlleAktuellenKarten() }.distinct()
}

fun yamlToSpiele(yamlInput: String, moeglicheKategorien: List<Kategorie>): List<Spiel> {
    val daten = (Yaml().load(yamlInput) as List<Map<String, Any>>)
    return daten.map { yamlToSpiel(it, moeglicheKategorien) }
}

fun yamlToSpiel(data: Map<String, Any>, moeglicheKategorien: List<Kategorie>): Spiel {
    return yamlToSammlung(data, moeglicheKategorien, ::Spiel)
}

fun eingabeToSpiel(
    id: Int, sprache: Sprachen, name: String, originaleKategorien: List<Kategorie>
): Spiel = eingabeToSammlung(id, sprache, name, originaleKategorien, ::Spiel)
