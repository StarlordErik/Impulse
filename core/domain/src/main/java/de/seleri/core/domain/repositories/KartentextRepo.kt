package de.seleri.core.domain.repositories

import de.seleri.core.common.ids.spielelementID.KartentextID
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.repositories.base.GetByIDRepo
import de.seleri.core.domain.repositories.base.UpdateableRepo

interface KartentextRepo: UpdateableRepo<Kartentext>, GetByIDRepo<Kartentext, KartentextID>
