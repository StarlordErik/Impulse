package de.seleri.core.domain.model

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.sammlungen.Bestandteil

data class Lokalisierung(
	private val datenbankObjektDaten: DatenbankObjektDaten = DatenbankObjektDaten(),

	val bezeichnung: String,
	val sprache: Sprache = Sprache.OG,
	val bearbeitet: Boolean = false,
): DatenbankObjekt by datenbankObjektDaten, Bestandteil
