package de.seleri.core.domain.repositories

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.model.Translation

interface TranslationRepo: EntityRepo<Translation> {

	suspend fun new(translation: Translation, lokalisierungID: LokalisierungID, sprache: Sprache)

	suspend fun delete(translation: Translation, lokalisierungID: LokalisierungID, sprache: Sprache): Int

	suspend fun update(translation: Translation, lokalisierungID: LokalisierungID, sprache: Sprache): Int

	suspend fun getForLokalisierung(id: LokalisierungID): Map<Sprache, Translation>
}
