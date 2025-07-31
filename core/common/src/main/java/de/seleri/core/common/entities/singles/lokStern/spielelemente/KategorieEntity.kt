package de.seleri.core.common.entities.singles.lokStern.spielelemente

import de.seleri.core.common.id.spielelementID.KategorieID

interface KategorieEntity: SpielelementEntity, BestandteilEntity {

	override val id: KategorieID get() = KategorieID(lokalisierungID.id)
}
