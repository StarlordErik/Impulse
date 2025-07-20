package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.Spiel
import de.seleri.core.domain.repositories.basis.DatenbankObjektRepo
import de.seleri.core.domain.repositories.basis.SammlungRepo

interface SpielRepo: DatenbankObjektRepo<Spiel>, SammlungRepo<Spiel, Kategorie, BestandteilID.KategorieID>
