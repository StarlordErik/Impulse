package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.repositories.basis.BestandteilRepo
import de.seleri.core.domain.repositories.basis.DatenbankObjektRepo

interface KartentextRepo: DatenbankObjektRepo<Kartentext>, BestandteilRepo<Kartentext> {

	fun upsert(kartentexte: Collection<Kartentext>)
	fun getUngeseheneByIDs(kartentexteIDs: Collection<BestandteilID.KartentextID>): Collection<Kartentext>
	fun setUngesehen(kartentextIDs: Collection<BestandteilID.KartentextID>)
}
