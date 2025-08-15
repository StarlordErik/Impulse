package de.seleri.core.domain.useCases.create

class CreateDatabaseUC(
	private val createTranslationUC: CreateTranslationUC, private val createLokalisierungUC: CreateLokalisierungUC
) {

	suspend operator fun invoke() {
	}
}
