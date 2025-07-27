package de.seleri.core.domain.modell


data class EntityModellDaten(
	override val id: Int = Konstanten.ID
): EntityModell {

	companion object {

		fun fromEingabe(id: Int = Konstanten.ID): EntityModellDaten =
			EntityModellDaten(id)
	}
}
