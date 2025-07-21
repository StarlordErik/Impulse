package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.Lokalisierung

interface LokalisierungRepo {

	suspend fun upsertForSpiel(spielId: Int, lokalisierung: Lokalisierung)
	suspend fun upsertForKategorie(kategorieId: Int, lokalisierung: Lokalisierung)
	suspend fun upsertForKartentext(kartentextId: Int, lokalisierung: Lokalisierung)

	suspend fun delete(lokalisierung: Lokalisierung)

	suspend fun getForSpiel(spielId: Int): Collection<Lokalisierung>
	suspend fun getForKategorie(kategorieId: Int): Collection<Lokalisierung>
	suspend fun getForKartentext(kartentextId: Int): Collection<Lokalisierung>
}
