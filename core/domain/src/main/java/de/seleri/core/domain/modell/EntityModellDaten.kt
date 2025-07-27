package de.seleri.core.domain.modell

import de.seleri.core.common.idInt.EntityIDint


data class EntityModellDaten(
	override val id: EntityIDint = Konstanten.ENTITY_ID
): EntityModell {

	companion object {

		fun fromEingabe(id: EntityIDint = Konstanten.ENTITY_ID): EntityModellDaten =
			EntityModellDaten(id)
	}
}
