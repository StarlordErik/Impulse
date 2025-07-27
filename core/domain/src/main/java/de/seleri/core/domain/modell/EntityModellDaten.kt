package de.seleri.core.domain.modell

import de.seleri.core.common.idInt.IDint


data class EntityModellDaten(
	override val id: IDint = Konstanten.ID
): EntityModell {

	companion object {

		fun fromEingabe(id: IDint = Konstanten.ID): EntityModellDaten =
			EntityModellDaten(id)
	}
}
