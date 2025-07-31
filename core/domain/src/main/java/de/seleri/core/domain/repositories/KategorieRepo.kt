package de.seleri.core.domain.repositories

import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.repositories.base.SpielelementRepo

interface KategorieRepo: SpielelementRepo<Kategorie, KategorieID>
