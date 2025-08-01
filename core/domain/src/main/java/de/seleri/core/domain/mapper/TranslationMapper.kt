package de.seleri.core.domain.mapper

import de.seleri.core.common.Sprache
import de.seleri.core.common.entities.TranslationEntity
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.model.Translation

// @formatter:off
fun TranslationEntity.toDomain(): Translation =
	Translation(
		bezeichnung = this.bezeichnung,
		bearbeitet = this.bearbeitet
	)

typealias TranslationFactory<T> = (
	lokalisierungID: LokalisierungID,
	sprache: Sprache,
	bezeichnung: String,
	bearbeitet: Boolean
	) -> T

fun <E: TranslationEntity> Translation.toEntity(
	lokalisierungID: LokalisierungID,
	sprache: Sprache,
	factory: TranslationFactory<E>
): E =
	factory(
		lokalisierungID,
		sprache,
		this.bezeichnung,
		this.bearbeitet
	)
// @formatter:on
