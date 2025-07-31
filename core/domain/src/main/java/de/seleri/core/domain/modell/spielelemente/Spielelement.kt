package de.seleri.core.domain.modell.spielelemente

import de.seleri.core.common.ids.spielelementID.SpielelementID
import de.seleri.core.domain.modell.IDable

interface Spielelement<SID: SpielelementID>: IDable<SID>, SpielelementDaten
