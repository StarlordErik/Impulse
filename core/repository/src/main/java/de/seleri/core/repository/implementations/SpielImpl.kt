package de.seleri.core.repository.implementations

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.daos.spielelemente.SpielDao
import de.seleri.core.data.entities.joins.SpielXKategorie
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.model.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaObjekt
import de.seleri.core.domain.repositories.KategorieRepo
import de.seleri.core.domain.repositories.LokalisierungRepo
import de.seleri.core.domain.repositories.SpielRepo
import de.seleri.core.repository.mapper.toDomain
import de.seleri.core.repository.mapper.toEntity
import de.seleri.core.repository.mapper.toMeta

class SpielImpl(
	private val dao: SpielDao,
	private val lokalisierungRepo: LokalisierungRepo,
	private val kategorieRepo: KategorieRepo
): SpielRepo {

	override suspend fun upsert(spielelement: Spiel) {
		val keyID = dao
			.upsert(spielelement.toEntity())
			.toInt()

		val spielID = SpielelementID.SpielID(keyID)

		spielelement.lokalisierungen.map { lokalisierung ->
			lokalisierungRepo.upsertForSpiel(spielID, lokalisierung)
		}
	}

	override suspend fun delete(spielelement: Spiel) =
		dao.delete(spielelement.toEntity())

	override suspend fun get(spielelementID: SpielelementID.SpielID): Spiel {
		val spielEntity = dao.get(spielelementID.toInt())

		val kategorien = dao
			.getKategorien(spielelementID.toInt())
			.map { kategorieEntity ->
				val kategorieID = SpielelementID.KategorieID(kategorieEntity.id)
				kategorieRepo.get(kategorieID)
			}

		val spielID = SpielelementID.SpielID(spielEntity.id)
		val lokalisierungen = lokalisierungRepo.getForSpiel(spielID)

		return spielEntity.toDomain(lokalisierungen, kategorien)
	}


	override suspend fun insertConnection(
		sammlung: Spiel, bestandteil: Kategorie
	) {
		val spielXKategorie = SpielXKategorie(
			spielID = sammlung.id, kategorieID = bestandteil.id
		)
		dao.insert(spielXKategorie)
	}

	override suspend fun deleteConnection(
		sammlung: Spiel, bestandteil: Kategorie
	) {
		val spielXKategorie = SpielXKategorie(
			spielID = sammlung.id, kategorieID = bestandteil.id
		)
		dao.delete(spielXKategorie)
	}


	override suspend fun getAllMetas(): Collection<SpielMetaObjekt> {
		val spielEntities = dao.getAll()

		return spielEntities.map { spielEntity ->
			val spielID = SpielelementID.SpielID(spielEntity.id)
			val lokalisierungen = lokalisierungRepo.getForSpiel(spielID)
			spielEntity.toMeta(lokalisierungen)
		}
	}
}
