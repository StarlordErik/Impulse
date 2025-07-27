package de.seleri.core.domain.mapper

import de.seleri.core.domain.entities.joins.KategorieXkartentext
import de.seleri.core.domain.entities.joins.SpielXkategorie
import de.seleri.core.domain.entities.singles.LokalisierungEntity
import de.seleri.core.domain.entities.singles.TranslationEntity
import de.seleri.core.domain.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.domain.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.domain.entities.singles.spielelemente.SpielEntity

data class DatenbankSlice(
	val translationen: Collection<TranslationEntity>? = null,
	val lokalisierungen: Collection<LokalisierungEntity>? = null,
	val kartentexte: Collection<KartentextEntity>? = null,
	val kategorien: Collection<KategorieEntity>? = null,
	val spiele: Collection<SpielEntity>? = null,

	val kategorieXkartentexte: Collection<KategorieXkartentext>? = null,
	val spielXkategorien: Collection<SpielXkategorie>? = null,
) {

	companion object {

		fun merged(
			others: Collection<DatenbankSlice>,
			translationen: Collection<TranslationEntity>? = null,
			lokalisierungen: Collection<LokalisierungEntity>? = null,
			kartentexte: Collection<KartentextEntity>? = null,
			kategorien: Collection<KategorieEntity>? = null,
			spiele: Collection<SpielEntity>? = null,
			kategorieXkartentexte: Collection<KategorieXkartentext>? = null,
			spielXkategorien: Collection<SpielXkategorie>? = null,
		): DatenbankSlice {
			// Schritt 1: initialer Slice aus den neuen Daten
			val initial = DatenbankSlice(
				translationen = translationen,
				lokalisierungen = lokalisierungen,
				kartentexte = kartentexte,
				kategorien = kategorien,
				spiele = spiele,
				kategorieXkartentexte = kategorieXkartentexte,
				spielXkategorien = spielXkategorien
			)
			// Schritt 2: Fold über alle anderen Slices und merge jeweils mit den bereits akkumulierten
			return others.fold(initial) { akkumulierteSlices, slice ->
				DatenbankSlice(
					translationen = merge(akkumulierteSlices.translationen, slice.translationen),
					lokalisierungen = merge(akkumulierteSlices.lokalisierungen, slice.lokalisierungen),
					kartentexte = merge(akkumulierteSlices.kartentexte, slice.kartentexte),
					kategorien = merge(akkumulierteSlices.kategorien, slice.kategorien),
					spiele = merge(akkumulierteSlices.spiele, slice.spiele),
					kategorieXkartentexte = merge(akkumulierteSlices.kategorieXkartentexte, slice.kategorieXkartentexte),
					spielXkategorien = merge(akkumulierteSlices.spielXkategorien, slice.spielXkategorien)
				)
			}
		}

		private fun <T> merge(
			a: Collection<T>?, b: Collection<T>?
		): Collection<T>? =
			when {
				a == null && b == null -> null
				a == null -> b
				b == null -> a
				else -> a + b
			}
	}
}
