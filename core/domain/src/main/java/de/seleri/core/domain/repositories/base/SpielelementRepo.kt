package de.seleri.core.domain.repositories.base

import de.seleri.core.common.ids.spielelementID.SpielelementID
import de.seleri.core.domain.modell.spielelemente.Spielelement

interface SpielelementRepo<S: Spielelement, SID: SpielelementID>: UpdateableRepo<S>, GetByIDRepo<S, SID>
