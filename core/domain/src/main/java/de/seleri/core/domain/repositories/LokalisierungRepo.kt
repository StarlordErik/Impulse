package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.ids.SpielelementID
import de.seleri.core.domain.repositories.basis.BestandteilRepo
import de.seleri.core.domain.repositories.basis.DatenbankObjektRepo

interface LokalisierungRepo: DatenbankObjektRepo<Lokalisierung>, BestandteilRepo<Lokalisierung> {

	suspend fun getLokalisierungIDsForSpielelement(spielelementID: SpielelementID): List<BestandteilID.LokalisierungID>

	/*
		suspend fun getForSpielelementInSprache(spielelementID: SpielelementID, sprache: Sprache): Lokalisierung
		suspend fun getForSpielelementeInSprache(spielelementID: Collection<SpielelementID>): List<Lokalisierung>

		suspend fun upsertForSpielelementInSprache(spielelementID: SpielelementID, sprache: Sprache)
	*/
}
