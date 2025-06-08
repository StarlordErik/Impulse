package de.seleri.spielelemente

abstract class LokalisierbaresSpielelement(
    open val id: Int, open val localizations: Localizations
)

fun <T : LokalisierbaresSpielelement> eingabeToLokalisierbaresElement(
    id: Int, sprache: Sprachen, text: String, constructor: (Int, Localizations) -> T
): T {
    return constructor(id, eingabeToLocalizations(sprache, text))
}

fun <T : LokalisierbaresSpielelement> findeElemente(ids: List<Int>, findeIn: List<T>): List<T> {
    return ids.map { id ->
        findeIn.find { it.id == id } ?: error("Element mit ID $id nicht gefunden")
    }
}
