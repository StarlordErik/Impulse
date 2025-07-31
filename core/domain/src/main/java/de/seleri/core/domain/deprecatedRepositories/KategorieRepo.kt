package de.seleri.core.domain.deprecatedRepositories

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.Kategorie
import de.seleri.core.domain.deprecatedRepositories.basis.SammlungRepo
import de.seleri.core.domain.deprecatedRepositories.basis.SpielelementRepo

interface KategorieRepo: SpielelementRepo<Kategorie, SpielelementID.KategorieID>, SammlungRepo<Kartentext, Kategorie>
