package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.Lokalisierung
import de.seleri.core.domain.model.idTypes.SpielelementID

interface LokalisierungRepo {

	suspend fun upsertForSpiel(spielID: SpielelementID.SpielID, lokalisierung: Lokalisierung)
	suspend fun upsertForKategorie(kategorieID: SpielelementID.KategorieID, lokalisierung: Lokalisierung)
	suspend fun upsertForKartentext(kartentextID: SpielelementID.KartentextID, lokalisierung: Lokalisierung)
	suspend fun delete(lokalisierung: Lokalisierung)

	suspend fun getForSpiel(spielID: SpielelementID.SpielID): Collection<Lokalisierung>
	suspend fun getForKategorie(kategorieID: SpielelementID.KategorieID): Collection<Lokalisierung>
	suspend fun getForKartentext(kartentextID: SpielelementID.KartentextID): Collection<Lokalisierung>
}
