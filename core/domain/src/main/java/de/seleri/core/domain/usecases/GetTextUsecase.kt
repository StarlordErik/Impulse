package de.seleri.core.domain.usecases

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.ids.SpielelementID
import de.seleri.core.domain.repositories.LokalisierungRepo

class GetTextUsecase(
	private val lokalisierungRepo: LokalisierungRepo, private val fallbackSprache: Sprache = Sprache.OG
) {

	suspend operator fun invoke(spielelementID: SpielelementID, inSprache: Sprache): String {
		val lokalisierung = lokalisierungRepo.getByIDinSprache(spielelementID, inSprache)
			?: lokalisierungRepo.getByIDinSprache(spielelementID, fallbackSprache)!!

		return lokalisierung.bezeichnung
	}

	suspend operator fun invoke(spielelementIDs: Collection<SpielelementID>, inSprache: Sprache): Collection<String> {
		return spielelementIDs.map { this(it, inSprache) }
	}
}
