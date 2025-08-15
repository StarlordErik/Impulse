package de.seleri.core.domain.useCases.create

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.Spiel
import de.seleri.core.domain.repositories.idEntity.spielelemente.SpielRepo

class NewSpielUC(private val spielRepo: SpielRepo) {

	suspend operator fun invoke(
		spielTranslationen: Map<Sprache, String>, ogSprache: Sprache, kategorien: Collection<Kategorie>
	): Spiel {

		val lokalisierung = newLokalisierung(spielTranslationen, ogSprache)
		val spiel = Spiel(lokalisierung, kategorien)

		spielRepo.new(spiel)
		return spiel
	}
}
