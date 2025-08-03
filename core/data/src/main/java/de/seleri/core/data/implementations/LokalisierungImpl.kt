package de.seleri.core.data.implementations

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.data.daos.singles.LokalisierungDAO
import de.seleri.core.data.toRoom
import de.seleri.core.domain.mapper.toDomain
import de.seleri.core.domain.model.Translation
import de.seleri.core.domain.model.idEntity.Lokalisierung
import de.seleri.core.domain.repositories.TranslationRepo
import de.seleri.core.domain.repositories.idEntity.LokalisierungRepo

class LokalisierungImpl(private val dao: LokalisierungDAO, private val translationRepo: TranslationRepo):
	LokalisierungRepo {

	override suspend fun new(model: Lokalisierung): LokalisierungID {

		suspend fun ggfNeueTranslationInserten(
			ogBezeichnung: String, neueTranslation: Translation, neueSprache: Sprache, lokalisierungID: LokalisierungID
		) {
			if (neueSprache != model.ogSprache || neueTranslation.bezeichnung != ogBezeichnung) {
				translationRepo.new(lokalisierungID, neueTranslation, neueSprache)
			}
		}

		val entity = model.toRoom()
		val ogBezeichnung = model.translationen[Sprache.OG]!!.bezeichnung
		val ggfVorhandeneLokalisierungID = translationRepo.findLokalisierungIDByBezeichnung(ogBezeichnung)

		if (ggfVorhandeneLokalisierungID == null) {
			val newLokalisierungID = LokalisierungID(
				dao
					.insert(entity)
					.toInt()
			)

			model.translationen.entries.forEach { (neueSprache, neueTranslation) ->
				ggfNeueTranslationInserten(ogBezeichnung, neueTranslation, neueSprache, newLokalisierungID)
			}

			return newLokalisierungID
		} else {
			val vorhandeneTranslationen = translationRepo.getForLokalisierung(ggfVorhandeneLokalisierungID)

			model.translationen.entries.forEach { (neueSprache, neueTranslation) ->

				if (neueSprache !in vorhandeneTranslationen.keys) {
					ggfNeueTranslationInserten(ogBezeichnung, neueTranslation, neueSprache, ggfVorhandeneLokalisierungID)
				} else if (neueTranslation.bezeichnung != vorhandeneTranslationen[neueSprache]!!.bezeichnung) {

					// Was tun bei einem Konflikt?
					translationRepo.update(
						translation = neueTranslation, lokalisierungID = ggfVorhandeneLokalisierungID, sprache = neueSprache
					)
				}
			}
			return ggfVorhandeneLokalisierungID
		}
	}

	override suspend fun delete(model: Lokalisierung): Int {
		val entity = model.toRoom()

		return dao.delete(entity)
	}

	override suspend fun get(id: LokalisierungID): Lokalisierung {
		val entity = dao.get(id.value)

		val translationen = translationRepo.getForLokalisierung(id)

		return entity.toDomain(translationen)
	}
}
