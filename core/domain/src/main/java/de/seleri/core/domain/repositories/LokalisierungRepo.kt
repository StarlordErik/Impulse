package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.Lokalisierung

interface LokalisierungRepo {

	suspend fun upsertForSpiel(spielID: Int, lokalisierung: Lokalisierung)
	suspend fun upsertForKategorie(kategorieID: Int, lokalisierung: Lokalisierung)
	suspend fun upsertForKartentext(kartentextID: Int, lokalisierung: Lokalisierung)
	suspend fun delete(lokalisierung: Lokalisierung)

	suspend fun getForSpiel(spielID: Int): Collection<Lokalisierung>
	suspend fun getForKategorie(kategorieID: Int): Collection<Lokalisierung>
	suspend fun getForKartentext(kartentextID: Int): Collection<Lokalisierung>
}
