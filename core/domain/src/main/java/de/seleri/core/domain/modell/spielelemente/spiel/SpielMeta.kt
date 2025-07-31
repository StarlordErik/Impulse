package de.seleri.core.domain.modell.spielelemente.spiel

import de.seleri.core.domain.modell.IDable
import de.seleri.core.domain.modell.spielelemente.Spielelement

interface SpielMeta: Spielelement, IDable {

	val bildDateiname: String?
}
