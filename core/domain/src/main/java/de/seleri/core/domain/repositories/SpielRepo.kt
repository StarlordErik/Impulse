package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.Spiel
import de.seleri.core.domain.repositories.basis.DatenbankObjektRepo
import de.seleri.core.domain.repositories.basis.NzuMRepo

interface SpielRepo: DatenbankObjektRepo<Spiel>, NzuMRepo<Spiel, Kategorie>
