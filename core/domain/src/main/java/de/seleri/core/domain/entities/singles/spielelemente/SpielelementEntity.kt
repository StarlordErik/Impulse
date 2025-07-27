package de.seleri.core.domain.entities.singles.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.common.idInt.LokalisierungIDint
import de.seleri.core.common.idInt.SpielelementIDint
import de.seleri.core.domain.entities.singles.DatenbankObjektEntity

interface SpielelementEntity: DatenbankObjektEntity {

	override val id: SpielelementIDint

	val lokalisierungID: LokalisierungIDint // Spielelemente sind eindeutig bzgl. ihrer LokalisierungID

	val ogSprache: Sprache
	val selbstErstellt: Boolean
	val inaktiv: Boolean
	val favorisiert: Boolean
}
