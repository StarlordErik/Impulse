package de.seleri.core.common.entities.singles.spielelemente

import de.seleri.core.common.ids.spielelementID.KategorieID

interface KategorieEntity: SpielelementEntity, BestandteilEntity {

	override val id: KategorieID get() = KategorieID(lokalisierungID.value)
}
