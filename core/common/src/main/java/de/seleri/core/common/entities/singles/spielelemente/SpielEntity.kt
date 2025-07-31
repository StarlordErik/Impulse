package de.seleri.core.common.entities.singles.spielelemente

import de.seleri.core.common.ids.spielelementID.SpielID

interface SpielEntity: SpielelementEntity {

	override val id: SpielID get() = SpielID(lokalisierungID.value)

	val bildDateiname: String?

	val anleitung: String?
	val texteProKarte: Int
}
