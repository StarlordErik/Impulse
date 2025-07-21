package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.repositories.basis.SammlungRepo
import de.seleri.core.domain.repositories.basis.SpielelementRepo

interface KategorieRepo: SpielelementRepo<Kategorie>, SammlungRepo<Kartentext, Kategorie>
