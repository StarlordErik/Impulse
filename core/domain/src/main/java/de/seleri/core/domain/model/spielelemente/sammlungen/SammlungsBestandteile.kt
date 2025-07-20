package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.domain.model.ids.BestandteilID

data class SammlungsBestandteile<ID: BestandteilID>(
	val bestandteileMap: Map<SammlungsBestandteilTyp, Collection<ID>>,
): Sammlung {

	fun originaleBestandteile(): Collection<ID> =
		bestandteileMap[SammlungsBestandteilTyp.ORIGINAL]
			?: emptyList()

	fun inaktiveBestandteile(): Collection<ID> =
		bestandteileMap[SammlungsBestandteilTyp.INAKTIV]
			?: emptyList()

	fun selbstErstellteBestandteile(): Collection<ID> =
		bestandteileMap[SammlungsBestandteilTyp.SELBST_ERSTELLT]
			?: emptyList()

	override fun karte(texteProKarte: Int): List<BestandteilID.KartentextID> {
		TODO("Not yet implemented")
	}
}
