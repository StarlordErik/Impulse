package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie

interface KategorieRepo: DatenbankObjektRepo<Kategorie>, BestandteilRepo<Kategorie>, NzuMRepo<Kategorie, Kartentext>
