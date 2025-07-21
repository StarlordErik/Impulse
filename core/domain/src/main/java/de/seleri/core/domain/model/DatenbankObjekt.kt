package de.seleri.core.domain.model

interface DatenbankObjekt {

	val datenbankObjektDaten: DatenbankObjektDaten
	val id get() = datenbankObjektDaten.id
}
