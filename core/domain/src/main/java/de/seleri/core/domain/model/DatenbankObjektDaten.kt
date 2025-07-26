package de.seleri.core.domain.model


data class DatenbankObjektDaten(
	override val id: Int = Konstanten.ID
): DatenbankObjekt {

	companion object {

		fun fromEingabe(id: Int = Konstanten.ID): DatenbankObjektDaten =
			DatenbankObjektDaten(id)
	}
}
