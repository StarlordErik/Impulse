package de.seleri.core.domain.mapper

import de.seleri.core.domain.entities.joins.KategorieXkartentext
import de.seleri.core.domain.entities.joins.SpielXkategorie
import de.seleri.core.domain.entities.singles.LokalisierungEntity
import de.seleri.core.domain.entities.singles.TranslationEntity
import de.seleri.core.domain.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.domain.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.domain.entities.singles.spielelemente.SpielEntity

data class DatenbankSlice(
	val translationen: MutableList<TranslationEntity> = mutableListOf(),
	val lokalisierungen: MutableList<LokalisierungEntity> = mutableListOf(),
	val kartentexte: MutableList<KartentextEntity> = mutableListOf(),
	val kategorien: MutableList<KategorieEntity> = mutableListOf(),
	val spiele: MutableList<SpielEntity> = mutableListOf(),

	val kategorieXkartentexte: MutableList<KategorieXkartentext> = mutableListOf(),
	val spielXkategorien: MutableList<SpielXkategorie> = mutableListOf(),
) {

	companion object {

		fun merged(
			others: List<DatenbankSlice>,
			translationen: MutableList<TranslationEntity> = mutableListOf(),
			lokalisierungen: MutableList<LokalisierungEntity> = mutableListOf(),
			kartentexte: MutableList<KartentextEntity> = mutableListOf(),
			kategorien: MutableList<KategorieEntity> = mutableListOf(),
			spiele: MutableList<SpielEntity> = mutableListOf(),
			kategorieXkartentexte: MutableList<KategorieXkartentext> = mutableListOf(),
			spielXkategorien: MutableList<SpielXkategorie> = mutableListOf(),
		): DatenbankSlice {
			val erg = others.first()
			val rest = others.drop(1)

			rest.forEach { slice ->
				erg.translationen.merge(slice.translationen)
				erg.lokalisierungen.merge(slice.lokalisierungen)
				erg.kartentexte.merge(slice.kartentexte)
				erg.kategorien.merge(slice.kategorien)
				erg.spiele.merge(slice.spiele)
				erg.kategorieXkartentexte.merge(slice.kategorieXkartentexte)
				erg.spielXkategorien.merge(slice.spielXkategorien)
			}

			erg.translationen.merge(translationen)
			erg.lokalisierungen.merge(lokalisierungen)
			erg.kartentexte.merge(kartentexte)
			erg.kategorien.merge(kategorien)
			erg.spiele.merge(spiele)
			erg.kategorieXkartentexte.merge(kategorieXkartentexte)
			erg.spielXkategorien.merge(spielXkategorien)

			return erg
		}

		private fun <T> MutableList<T>.merge(sliceElemente: List<T>) {
			if (sliceElemente.isNotEmpty()) this.addAll(sliceElemente)
		}
	}
}
