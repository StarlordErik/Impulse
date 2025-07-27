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
)
