package de.seleri.core.domain.repositories

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.domain.model.Lokalisierung

interface LokalisierungRepo {

	suspend fun upsertForSpiel(spielID: SpielelementID.SpielID, lokalisierung: Lokalisierung)
	suspend fun upsertForKategorie(kategorieID: SpielelementID.KategorieID, lokalisierung: Lokalisierung)
	suspend fun upsertForKartentext(kartentextID: SpielelementID.KartentextID, lokalisierung: Lokalisierung)
	suspend fun delete(lokalisierung: Lokalisierung)

	suspend fun getForSpiel(spielID: SpielelementID.SpielID): List<Lokalisierung>
	suspend fun getForKategorie(kategorieID: SpielelementID.KategorieID): List<Lokalisierung>
	suspend fun getForKartentext(kartentextID: SpielelementID.KartentextID): List<Lokalisierung>
}
