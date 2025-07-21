package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.ids.SammlungID
import de.seleri.core.domain.model.spiele.spielelemente.Kartentext
import de.seleri.core.domain.model.spiele.spielelemente.Kategorie
import de.seleri.core.domain.repositories.basis.BestandteilRepo
import de.seleri.core.domain.repositories.basis.DatenbankObjektRepo
import de.seleri.core.domain.repositories.basis.SammlungRepo

interface KategorieRepo: DatenbankObjektRepo<Kategorie>,
	BestandteilRepo<Kategorie>, SammlungRepo<Kategorie, SammlungID.KategorieID, Kartentext, BestandteilID.KartentextID>
