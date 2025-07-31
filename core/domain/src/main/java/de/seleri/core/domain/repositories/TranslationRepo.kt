package de.seleri.core.domain.repositories

import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.modell.Translation
import de.seleri.core.domain.repositories.base.UpdateableRepo

interface TranslationRepo: UpdateableRepo<Translation> {

	suspend fun getAll(id: LokalisierungID): Collection<Translation>
}
