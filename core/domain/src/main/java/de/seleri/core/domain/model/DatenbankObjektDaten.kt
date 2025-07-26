package de.seleri.core.domain.model


data class DatenbankObjektDaten(
	override val id: Int = DEFAULT_ID
): DatenbankObjekt {

	companion object {

		fun fromEingabe(id: Int = DEFAULT_ID): DatenbankObjektDaten =
			DatenbankObjektDaten(id)
	}
}
