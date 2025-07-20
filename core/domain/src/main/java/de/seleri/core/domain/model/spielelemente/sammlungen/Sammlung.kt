package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.spielelemente.Kartentext

interface Sammlung<B: Bestandteil> {
	val originaleBestandteile: Collection<B>
	val inaktiveBestandteile: Collection<B>
	val selbstErstellteBestandteile: Collection<B>

	fun erstelleKarte(texteProKarte: Int): List<Kartentext> {
		TODO("Not yet implemented")
	}
}
