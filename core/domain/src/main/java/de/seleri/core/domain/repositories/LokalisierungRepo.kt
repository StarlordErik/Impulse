package de.seleri.core.domain.repositories

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.ids.SpielelementID
import de.seleri.core.domain.repositories.basis.BestandteilRepo
import de.seleri.core.domain.repositories.basis.DatenbankObjektRepo

interface LokalisierungRepo: DatenbankObjektRepo<Lokalisierung>, BestandteilRepo<Lokalisierung> {

	// @formatter:off
	suspend fun getLokalisierungIDsForSpielelement(spielelementID: SpielelementID):
		Collection<BestandteilID.LokalisierungID>
	// @formatter:on

	suspend fun getByIDinSprache(spielelementID: SpielelementID, inSprache: Sprache): Lokalisierung?
}
