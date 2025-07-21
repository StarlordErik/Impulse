package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.ids.SammlungID
import de.seleri.core.domain.model.spiele.Spiel
import de.seleri.core.domain.model.spiele.spielelemente.Kategorie
import de.seleri.core.domain.repositories.basis.DatenbankObjektRepo
import de.seleri.core.domain.repositories.basis.SammlungRepo

interface SpielRepo: DatenbankObjektRepo<Spiel>,
	SammlungRepo<Spiel, SammlungID.SpielID, Kategorie, BestandteilID.KategorieID> {

	suspend fun getAlleSpiele(): List<Spiel>
}
