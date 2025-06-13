package de.seleri.spielelemente

abstract class SammlungAnSpielelementen<T : LokalisierbaresSpielelement>(
    override val id: Int,
    override val localizations: MutableMap<Sprachen, String>,
    open val originaleElemente: MutableMap<String, List<T>>,
    open var hinzugefuegteElemente: List<T>
) : LokalisierbaresSpielelement(id, localizations) {

    internal fun elementeEntfernen(entfernteElemente: List<T>) {
        entfernteElemente.forEach {
            originaleElemente[DAVON_ENTFERNT] = originaleElemente[DAVON_ENTFERNT]!!.plus(it)
            hinzugefuegteElemente = hinzugefuegteElemente.minus(it)
        }
    }

    internal fun elementeHinzufuegen(neueElemente: List<T>) {
        neueElemente.forEach { originaleElemente[DAVON_ENTFERNT]!!.minus(it) }

        val neuHinzugefuegt: MutableSet<T> = emptySet<T>().toMutableSet()
        neuHinzugefuegt.addAll(hinzugefuegteElemente)
        neuHinzugefuegt.addAll(neueElemente.filter { !getAlleElemente().contains(it) })
        hinzugefuegteElemente = neuHinzugefuegt.toList()
    }

    internal fun getAlleElemente(): List<T> {
        val alleElemente = mutableListOf<T>()
        originaleElemente[IDS]?.let { alleElemente.addAll(it) }
        alleElemente.addAll(hinzugefuegteElemente)
        return alleElemente
    }

    internal fun getAlleAktuellenElemente(): List<T> {
        val aktuelleElemente = getAlleElemente().toMutableList()
        originaleElemente[DAVON_ENTFERNT]?.let { aktuelleElemente.removeAll(it) }
        return aktuelleElemente
    }

    override fun toYaml(): String {
        val output = StringBuilder()
        output.append(super.toYaml())
        output.append(localizationsToYaml(NAME))
        return output.toString()
    }

    internal fun originaleUndHinzugefuegteElementeToYaml(elemente: String): String {
        val output = StringBuilder()
        output.append(attributToYamlZeile(2, "$ORIGINALE$elemente", originaleElemente))
        output.append(
            attributToYamlZeile(
                2, "$HINZUGEFUEGTE$elemente$BINDESTRICH_IDS", hinzugefuegteElemente
            )
        )
        return output.toString()
    }
}

@Suppress("UNCHECKED_CAST")
fun <T : SammlungAnSpielelementen<E>, E : LokalisierbaresSpielelement> yamlToSammlung(
    data: Map<String, Any>,
    moeglicheKarten: List<E>,
    constructor: (Int, MutableMap<Sprachen, String>, MutableMap<String, List<E>>, List<E>) -> T
): T {
    val dummy = yamlToLokalisierbaresElement(data, ::LokalisierbaresSpielelement)

    val originaleElementeIDs =
        ((data["$ORIGINALE$KARTEN"] ?: data["$ORIGINALE$KATEGORIEN"]) as Map<String, List<Int>>)
    val originaleIDs = findeElemente(originaleElementeIDs[IDS]!!, moeglicheKarten)
    val entfernteIDs = findeElemente(originaleElementeIDs[DAVON_ENTFERNT]!!, moeglicheKarten)
    val originaleElemente =
        mutableMapOf<String, List<E>>(IDS to originaleIDs, DAVON_ENTFERNT to entfernteIDs)

    val weitereIds = (data["$HINZUGEFUEGTE$KARTEN$BINDESTRICH_IDS"]
        ?: data["$HINZUGEFUEGTE$KATEGORIEN$BINDESTRICH_IDS"]) as List<Int>

    return constructor(
        dummy.id, dummy.localizations, originaleElemente, findeElemente(weitereIds, moeglicheKarten)
    )
}

fun <T : SammlungAnSpielelementen<E>, E : LokalisierbaresSpielelement> eingabeToSammlung(
    id: Int,
    sprache: Sprachen,
    name: String,
    originaleElemente: List<E>,
    constructor: (Int, MutableMap<Sprachen, String>, MutableMap<String, List<E>>, List<E>) -> T
): T {
    val dummy = eingabeToLokalisierbaresElement(id, sprache, name, ::LokalisierbaresSpielelement)
    val originaleElementeMitNullEntfernten =
        mutableMapOf<String, List<E>>(IDS to originaleElemente, DAVON_ENTFERNT to emptyList())
    return constructor(dummy.id, dummy.localizations, originaleElementeMitNullEntfernten, listOf())
}
