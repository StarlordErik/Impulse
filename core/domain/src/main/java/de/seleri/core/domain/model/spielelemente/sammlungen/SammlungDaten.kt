package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie

data class SammlungDaten<B: Bestandteil>(
	override val bestandteile: Collection<B>,
): Sammlung<B> {

	/**
	 * Das Problem an der Funktion ist wie folgt: Sammlung definiert diese Funktion - und alle Sammlungen wie Spiel
	 * und Kategorie haben auch eine sinnvolle Weise, diese Funktion zu implementieren. Aber das geht nur, weil sie
	 * wissen, was ihr konkreter Bestandteil ist, auf den sie dann weitere Funktionen aufrufen können. Da die Daten
	 * zu Sammlung, also [SammlungDaten], aber auch eine Sammlung sind... ist das ganze etwas lost.
	 *
	 * MÖGLICHE LÖSUNG: Wenn SammlungDaten nur Daten hält, aber keine Funktionen ausführt, kann man die Delegation
	 * auch lassen.
	 */
	@Deprecated("Muss von verwendeten Klassen überschrieben werden.")
	override fun getKategorieMitKarte(
		anzahlTexte: Int, bereitsEnthalteneKT: Collection<Kartentext>
	): Pair<Kategorie, List<Kartentext>> =
		error("Muss von Implementierungen überschrieben werden.")
}
