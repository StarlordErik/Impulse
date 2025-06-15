package de.seleri.spielelemente

import org.yaml.snakeyaml.Yaml

data class Kategorie(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    override val originaleElemente: MutableMap<String, List<Karte>>,
    override var hinzugefuegteElemente: List<Karte>
) : SammlungAnSpielelementen<Karte>(id, localizations, originaleElemente, hinzugefuegteElemente) {

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        output.append(originaleUndHinzugefuegteElementeToYaml(KARTEN))
        return output.toString()
    }

    fun kartenEntfernen(karten: List<Karte>) = elementeEntfernen(karten)
    fun kartenHinzufuegen(karten: List<Karte>) = elementeHinzufuegen(karten)
    fun getAlleKarten(): List<Karte> = getAlleElemente()
    fun getAlleAktuellenKarten(): List<Karte> = getAlleAktuellenElemente()

    companion object {
        fun fromEingabe(
            id: Int, sprache: Sprachen, name: String, originaleKarten: List<Karte>
        ): Kategorie = eingabeToSammlung(id, sprache, name, originaleKarten, ::Kategorie)

        fun fromYaml(data: Map<String, Any>, moeglicheKarten: List<Karte>): Kategorie {
            return yamlToSammlung(data, moeglicheKarten, ::Kategorie)
        }

        fun fromYaml(yamlInput: String, moeglicheKarten: List<Karte>): List<Kategorie> {
            val daten = (Yaml().load(yamlInput) as List<Map<String, Any>>)
            return daten.map { fromYaml(it, moeglicheKarten) }
        }
    }
}
