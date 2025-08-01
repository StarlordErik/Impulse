package de.seleri.core.domain.model.idEntity.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.ModelEntity
import de.seleri.core.domain.model.idEntity.Lokalisierung

interface SpielelementDaten: ModelEntity {

	val lokalisierung: Lokalisierung

	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean

	fun getBezeichnung(sprache: Sprache): String =
		lokalisierung.translationen[sprache]?.bezeichnung
			?: "localization unavailable"

}
