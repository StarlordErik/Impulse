package de.seleri.core.domain.modell.idEntity.spielelemente.spiel

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.domain.modell.idEntity.spielelemente.Spielelement

interface SpielMeta: Spielelement<SpielID> {

	override val id: SpielID get() = SpielID(lokalisierung.id.value)

	val bildDateiname: String?
}
