package de.seleri.core.common.entities

import de.seleri.core.common.Sprache

interface TranslationEntity: Entity, LokSternEntity {

	val sprache: Sprache
	val bezeichnung: String
	val bearbeitet: Boolean
}
