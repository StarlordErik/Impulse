package de.seleri.core.data.implementations

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.daos.singles.spielelemente.SpielDAO
import de.seleri.core.data.deprecatedMapper.toMeta
import de.seleri.core.data.entities.joins.SpielXKategorieRoom
import de.seleri.core.domain.deprecatedRepositories.KategorieRepo
import de.seleri.core.domain.deprecatedRepositories.LokalisierungRepo
import de.seleri.core.domain.deprecatedRepositories.SpielRepo
import de.seleri.core.domain.mapper.spielelemente.spiel.toDomain
import de.seleri.core.domain.mapper.spielelemente.spiel.toEntity
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.spielelemente.spiel.SpielMetaObjekt

class SpielImpl(
	private val dao: SpielDAO,
	private val lokalisierungRepo: LokalisierungRepo,
	private val kategorieRepo: KategorieRepo
): SpielRepo {

	override suspend fun upsert(spielelement: Spiel) {
		val keyID = dao
			.upsert(spielelement.toEntity())
			.toInt()

		val spielID = SpielelementID.SpielID(keyID)

		spielelement.lokalisierung.map { lokalisierung ->
			lokalisierungRepo.upsertForSpiel(spielID, lokalisierung)
		}

		updateConnections(spielelement, spielelement.bestandteile)
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
		insertConnectionByID(sammlung, SpielelementID.KategorieID(bestandteil.id))
	}

	override suspend fun deleteConnection(
		sammlung: Spiel, bestandteil: Kategorie
	) {
		val spielXKategorie = SpielXKategorieRoom(
			spielID = sammlung.id, kategorieID = bestandteil.id
		)
		dao.delete(spielXKategorie)
	}


	override suspend fun updateConnections(
		sammlung: Spiel, neueBestandteile: Collection<Kategorie>
	) {
		val alteConnections = dao.getAllConnections(sammlung.id)
		val neueKids = neueBestandteile
			.map { it.id }
			.toMutableSet()

		alteConnections.forEach { alteConnection ->
			val alteKid = alteConnection.kategorieID

			if (alteKid !in neueKids) {
				dao.delete(alteConnection)
				neueKids.remove(alteKid)
			}
		}

		neueKids.forEach { neueKTid ->
			insertConnectionByID(sammlung, SpielelementID.KategorieID(neueKTid))
		}
	}

	private suspend fun insertConnectionByID(sammlung: Spiel, bestandteilID: SpielelementID.KategorieID) {
		val spielXKategorie = SpielXKategorieRoom(
			spielID = sammlung.id, kategorieID = bestandteilID.toInt()
		)
		dao.insert(spielXKategorie)
	}

	override suspend fun getAllMetaObjekte(): List<SpielMetaObjekt> {
		val spielEntities = dao.getAll()

		return spielEntities.map { spielEntity ->
			val spielID = SpielelementID.SpielID(spielEntity.id)
			val lokalisierungen = lokalisierungRepo.getForSpiel(spielID)
			spielEntity.toMeta(lokalisierungen)
		}
	}
}
