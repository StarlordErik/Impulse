package de.seleri.core.domain.modell.idEntity.spielelemente

import de.seleri.core.common.ids.spielelementID.SpielelementID
import de.seleri.core.domain.modell.idEntity.IDentity

interface Spielelement<SID: SpielelementID>: IDentity<SID>, SpielelementDaten
