package de.seleri.core.domain.repositories

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.modell.Translation

interface TranslationRepo: EntityRepo<Translation>, UpdateableRepo<Translation> {

	suspend fun new(entity: Translation, lokalisierungID: LokalisierungID, sprache: Sprache)

	suspend fun getAll(id: LokalisierungID): Collection<Translation>
}
