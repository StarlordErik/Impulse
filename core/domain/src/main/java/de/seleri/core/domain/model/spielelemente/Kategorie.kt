package de.seleri.core.domain.model.spielelemente

import de.seleri.core.domain.model.spielelemente.sammlungen.Bestandteil
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

data class Kategorie(
	private val spielelementDaten: SpielelementDaten,

	private val sammlungDaten: SammlungDaten<Kartentext>,
): Spielelement by spielelementDaten, Sammlung<Kartentext> by sammlungDaten, Bestandteil {

	override fun getAktiveKartentexte(): Collection<Kartentext> =
		if (!inaktiv) bestandteile.flatMap { it.getAktiveKartentexte() } else emptyList()

	override fun getUnbesprocheneKartentexte(): Collection<Kartentext> =
		getAktiveKartentexte().flatMap { it.getUnbesprocheneKartentexte() }

	override fun getUngeseheneKartentexte(): Collection<Kartentext> =
		getUnbesprocheneKartentexte().flatMap { it.getUngeseheneKartentexte() }
}
