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
				erg.translationen.addAll(slice.translationen)
				erg.lokalisierungen.addAll(slice.lokalisierungen)
				erg.kartentexte.addAll(slice.kartentexte)
				erg.kategorien.addAll(slice.kategorien)
				erg.spiele.addAll(slice.spiele)
				erg.kategorieXkartentexte.addAll(slice.kategorieXkartentexte)
				erg.spielXkategorien.addAll(slice.spielXkategorien)
			}

			erg.translationen.addAll(translationen)
			erg.lokalisierungen.addAll(lokalisierungen)
			erg.kartentexte.addAll(kartentexte)
			erg.kategorien.addAll(kategorien)
			erg.spiele.addAll(spiele)
			erg.kategorieXkartentexte.addAll(kategorieXkartentexte)
			erg.spielXkategorien.addAll(spielXkategorien)

			return erg
		}
	}
}
