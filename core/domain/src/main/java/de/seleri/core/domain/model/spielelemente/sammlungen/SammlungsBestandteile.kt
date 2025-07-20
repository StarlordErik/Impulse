package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.ids.BestandteilID

data class SammlungsBestandteile<ID: BestandteilID>(
	val sammlungsBestandteile: Map<SammlungsBestandteilTyp, Collection<ID>>,
): Sammlung {

	fun originaleBestandteile(): Collection<ID> =
		sammlungsBestandteile[SammlungsBestandteilTyp.ORIGINAL]
			?: emptyList()

	fun inaktiveBestandteile(): Collection<ID> =
		sammlungsBestandteile[SammlungsBestandteilTyp.INAKTIV]
			?: emptyList()

	fun selbstErstellteBestandteile(): Collection<ID> =
		sammlungsBestandteile[SammlungsBestandteilTyp.SELBST_ERSTELLT]
			?: emptyList()

	override fun karte(texteProKarte: Int): List<BestandteilID.KartentextID> {
		TODO("Not yet implemented")
	}
}
