package de.seleri.core.domain.repositories

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.model.Translation

interface TranslationRepo: EntityRepo<Translation>, UpdateableRepo<Translation> {

	suspend fun new(translation: Translation, lokalisierungID: LokalisierungID, sprache: Sprache)

	suspend fun getForLokalisierung(id: LokalisierungID): Collection<Translation>
}
