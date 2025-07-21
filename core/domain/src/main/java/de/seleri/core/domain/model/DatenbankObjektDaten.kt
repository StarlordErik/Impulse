package de.seleri.core.domain.model

data class DatenbankObjektDaten(
	override val id: Int = 0
): DatenbankObjekt {

	override val datenbankObjektDaten: DatenbankObjektDaten get() = this
}
