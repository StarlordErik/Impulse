package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.Spiel

interface SpielRepo: DatenbankObjektRepo<Spiel>, NzuMRepo<Spiel, Kategorie>
