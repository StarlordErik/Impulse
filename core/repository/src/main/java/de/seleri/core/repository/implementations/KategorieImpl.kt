package de.seleri.core.repository.implementations

import de.seleri.core.common.idTypes.SpielelementID
import de.seleri.core.data.daos.spielelemente.KategorieDao
import de.seleri.core.data.entities.joins.KategorieXKartentext
import de.seleri.core.domain.model.spielelemente.Kartentext
import de.seleri.core.domain.model.spielelemente.Kategorie
import de.seleri.core.domain.repositories.KartentextRepo
import de.seleri.core.domain.repositories.KategorieRepo
import de.seleri.core.domain.repositories.LokalisierungRepo
import de.seleri.core.repository.mapper.toDomain
import de.seleri.core.repository.mapper.toEntity
import javax.inject.Inject

class KategorieImpl @Inject constructor(
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
		val kategorieXKartentext = KategorieXKartentext(
			kategorieID = sammlung.id, kartentextID = bestandteil.id
		)
		dao.insert(kategorieXKartentext)
	}

	override suspend fun deleteConnection(
		sammlung: Kategorie, bestandteil: Kartentext
	) {
		val kategorieXKartentext = KategorieXKartentext(
			kategorieID = sammlung.id, kartentextID = bestandteil.id
		)
		dao.delete(kategorieXKartentext)
	}
}
