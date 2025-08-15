package de.seleri.core.domain.useCases.create

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie
import de.seleri.core.domain.repositories.idEntity.spielelemente.KategorieRepo

class NewKategorieUC(private val kategorieRepo: KategorieRepo, private val newKartentextUC: NewKartentextUC) {

	suspend operator fun invoke(
		kategorieTranslationen: Map<Sprache, String>,
		ogSprache: Sprache,
		kartentextTexte: Collection<Map<Sprache, String>>
	): Kategorie {

		val lokalisierung = newLokalisierung(kategorieTranslationen, ogSprache)
		val kartentexte = kartentextTexte.map { kartentextTranslationen ->
			newKartentextUC(kartentextTranslationen, ogSprache)
		}
		val kategorie = Kategorie(lokalisierung, kartentexte)

		kategorieRepo.new(kategorie)
		return kategorie
	}
}
