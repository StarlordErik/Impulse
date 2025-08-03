package de.seleri.core.data.implementations

import de.seleri.core.common.ids.spielelementID.SpielID
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.Spiel
import de.seleri.core.domain.model.idEntity.spielelemente.spiel.SpielMetaDO
import de.seleri.core.domain.repositories.idEntity.spielelemente.SpielRepo

class SpielImpl: SpielRepo {

	override suspend fun new(model: Spiel): SpielID {
		TODO("Not yet implemented")
	}

	override suspend fun delete(model: Spiel): Int {
		TODO("Not yet implemented")
	}

	override suspend fun get(id: SpielID): Spiel {
		TODO("Not yet implemented")
	}

	override suspend fun find(id: SpielID): Spiel? {
		TODO("Not yet implemented")
	}

	override suspend fun update(model: Spiel): Int {
		TODO("Not yet implemented")
	}

	override suspend fun getAllMetas(): Collection<SpielMetaDO> {
		TODO("Not yet implemented")
	}
}
