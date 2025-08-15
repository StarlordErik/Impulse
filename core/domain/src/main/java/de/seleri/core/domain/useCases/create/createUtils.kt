package de.seleri.core.domain.useCases.create

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.model.Translation
import de.seleri.core.domain.model.idEntity.Lokalisierung

internal suspend fun newLokalisierung(translationenTexte: Map<Sprache, String>, ogSprache: Sprache): Lokalisierung {
	val translationen = translationenTexte.mapValues { (_, bezeichnung) ->
		Translation(bezeichnung)
	}
	return Lokalisierung(
		id = LokalisierungID(0), ogSprache = ogSprache, translationen = translationen
	)
}
