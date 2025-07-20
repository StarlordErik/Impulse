package de.seleri.core.domain.repositories

import de.seleri.core.common.SpielelementID
import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.Lokalisierung

interface LokalisierungRepo: DatenbankObjektRepo<Lokalisierung>, BestandteilRepo<Lokalisierung> {

	suspend fun getForSpielelementInSprache(spielelementID: SpielelementID, sprache: Sprache): Lokalisierung
	suspend fun getForAllSpielelementInSprache(spielelementID: Collection<SpielelementID>): List<Lokalisierung>
//.
	/*
	suspend fun getBearbeitete(): List<Lokalisierung>

 */
}
