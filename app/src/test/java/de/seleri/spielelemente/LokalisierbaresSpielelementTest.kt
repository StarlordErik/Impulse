package de.seleri.spielelemente

/** Dummy-Implementierung, da LokalisierbaresSpielelement abstrakt ist */
class DummyLokalisierbaresSpielelement(
    override val id: Int, override val localizations: MutableMap<Sprachen, String?>
): LokalisierbaresSpielelement(id, localizations)

class LokalisierbaresSpielelementTest {}
