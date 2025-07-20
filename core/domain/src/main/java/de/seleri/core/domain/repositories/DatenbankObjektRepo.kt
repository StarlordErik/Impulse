package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.DatenbankObjekt

interface DatenbankObjektRepo<D: DatenbankObjekt> {

	suspend fun upsert(datenbankObjekt: D)
	suspend fun delete(datenbankObjekt: D)
}
