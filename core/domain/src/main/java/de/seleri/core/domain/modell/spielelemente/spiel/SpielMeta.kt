package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.domain.modell.IDable
import de.seleri.core.domain.modell.spielelemente.Spielelement

interface SpielMeta: Spielelement, IDable {

	override val id: SpielID get() = SpielID(lokalisierung.id.value)

	val bildDateiname: String?
}
