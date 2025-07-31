package de.seleri.core.common.entities.singles.lokStern.spielelemente

import de.seleri.core.common.id.spielelementID.KartentextID

interface KartentextEntity: SpielelementEntity, BestandteilEntity {

	override val id: KartentextID get() = KartentextID(lokalisierungID.id)

	val gesehen: Boolean
	val besprochen: Boolean
}
