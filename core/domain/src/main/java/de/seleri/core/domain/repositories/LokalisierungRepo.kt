package de.seleri.core.domain.repositories

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.SpielelementID
import de.seleri.core.domain.model.Lokalisierung

interface LokalisierungRepo: DatenbankObjektRepo<Lokalisierung>, BestandteilRepo<Lokalisierung> {

	suspend fun getForSpielelementInSprache(spielelementID: SpielelementID, sprache: Sprache): Lokalisierung
	suspend fun getForSpielelementeInSprache(spielelementID: Collection<SpielelementID>): List<Lokalisierung>

}
