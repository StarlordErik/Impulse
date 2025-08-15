package de.seleri.core.domain.useCases.create

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.idEntity.spielelemente.Kartentext
import de.seleri.core.domain.repositories.idEntity.spielelemente.KartentextRepo

class NewKartentextUC(private val kartentextRepo: KartentextRepo) {

	suspend operator fun invoke(kartentextTranslationen: Map<Sprache, String>, ogSprache: Sprache): Kartentext {

		val lokalisierung = newLokalisierung(kartentextTranslationen, ogSprache)
		val kartentext = Kartentext(lokalisierung)

		kartentextRepo.new(kartentext)
		return kartentext
	}
}
