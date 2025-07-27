package de.seleri.core.data.implementations

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.daos.spielelemente.KategorieDao
import de.seleri.core.data.entities.joins.KategorieXKartentext
import de.seleri.core.data.mapper.toDomain
import de.seleri.core.data.mapper.toEntity
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.repositories.KartentextRepo
import de.seleri.core.domain.repositories.KategorieRepo
import de.seleri.core.domain.repositories.LokalisierungRepo

class KategorieImpl(
	private val dao: KategorieDao,
	private val lokalisierungRepo: LokalisierungRepo,
	private val kartentextRepo: KartentextRepo
): KategorieRepo {

	override suspend fun upsert(spielelement: Kategorie) {
		val keyID = dao
			.upsert(spielelement.toEntity())
			.toInt()

		val kategorieID = SpielelementID.KategorieID(keyID)

		spielelement.lokalisierungen.map { lokalisierung ->
			lokalisierungRepo.upsertForKategorie(kategorieID, lokalisierung)
		}

		updateConnections(spielelement, spielelement.bestandteile)
	}

	override suspend fun delete(spielelement: Kategorie) =
		dao.delete(spielelement.toEntity())

	override suspend fun get(spielelementID: SpielelementID.KategorieID): Kategorie {
		val kategorieEntity = dao.get(spielelementID.toInt())

		val kartentexte = dao
			.getKartentexte(spielelementID.toInt())
			.map { kartentextEntity ->
				val kartentextID = SpielelementID.KartentextID(kartentextEntity.id)
				kartentextRepo.get(kartentextID)
			}

		val kategorieID = SpielelementID.KategorieID(kategorieEntity.id)
		val lokalisierungen = lokalisierungRepo.getForKategorie(kategorieID)

		return kategorieEntity.toDomain(lokalisierungen, kartentexte)
	}


	override suspend fun insertConnection(
		sammlung: Kategorie, bestandteil: Kartentext
	) {
		insertConnectionByID(sammlung, SpielelementID.KartentextID(bestandteil.id))
	}

	override suspend fun deleteConnection(
		sammlung: Kategorie, bestandteil: Kartentext
	) {
		val kategorieXKartentext = KategorieXKartentext(
			kategorieID = sammlung.id, kartentextID = bestandteil.id
		)
		dao.delete(kategorieXKartentext)
	}

	override suspend fun updateConnections(
		sammlung: Kategorie, neueBestandteile: Collection<Kartentext>
	) {
		val alteConnections = dao.getAllConnections(sammlung.id)
		val neueKTids = neueBestandteile
			.map { it.id }
			.toMutableSet()

		alteConnections.forEach { alteConnection ->
			val alteKTid = alteConnection.kartentextID

			if (alteKTid !in neueKTids) {
				dao.delete(alteConnection)
				neueKTids.remove(alteKTid)
			}
		}

		neueKTids.forEach { neueKTid ->
			insertConnectionByID(sammlung, SpielelementID.KartentextID(neueKTid))
		}
	}

	private suspend fun insertConnectionByID(sammlung: Kategorie, bestandteilID: SpielelementID.KartentextID) {
		val kategorieXKartentext = KategorieXKartentext(
			kategorieID = sammlung.id, kartentextID = bestandteilID.toInt()
		)
		dao.insert(kategorieXKartentext)
	}
}
