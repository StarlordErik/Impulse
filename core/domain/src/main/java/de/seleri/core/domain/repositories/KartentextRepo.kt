package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.repositories.basis.BestandteilRepo
import de.seleri.core.domain.repositories.basis.DatenbankObjektRepo

interface KartentextRepo: DatenbankObjektRepo<Kartentext>, BestandteilRepo<Kartentext> {

	fun upsert(kartentexte: Collection<Kartentext>)
}
