package de.seleri.core.domain.mapper

import de.seleri.core.common.Sprache
import de.seleri.core.common.entities.singles.LokalisierungEntity
import de.seleri.core.domain.modell.Translation
import de.seleri.core.domain.modell.idEntity.Lokalisierung

// @formatter:off
fun LokalisierungEntity.toDomain(translationen: Map<Sprache, Translation>): Lokalisierung =
	Lokalisierung(
		id = this.id,
		ogSprache = this.ogSprache,
		translationen = translationen
	)

typealias LokalisierungFactory<L> = (
	id: Int,
	ogSprache: Sprache,
) -> L

fun <E: LokalisierungEntity> Lokalisierung.toEntity(factory: LokalisierungFactory<E>): E =
	factory(
		this.id.value,
		this.ogSprache
	)
// @formatter:on
