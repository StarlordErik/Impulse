package de.seleri.core.domain.useCases.create

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.model.Translation
import de.seleri.core.domain.repositories.TranslationRepo

class CreateTranslationUC(private val translationRepo: TranslationRepo) {

	suspend operator fun invoke(
		bezeichnung: String,
		sprache: Sprache,
		lokalisierungID: LokalisierungID
	): Pair<Sprache, Translation> {

		val translation = Translation(bezeichnung)

		translationRepo.new(
			lokalisierungID = lokalisierungID, translation = translation, sprache = sprache
		)

		return sprache to translation
	}
}
