package de.seleri.core.domain.mapper.spielelemente

import de.seleri.core.common.entities.singles.spielelemente.SpielelementEntity
import de.seleri.core.domain.model.idEntity.Lokalisierung
import de.seleri.core.domain.model.idEntity.spielelemente.SpielelementDO

object SpielelementMapper {

	operator fun invoke(dies: SpielelementEntity, lokalisierung: Lokalisierung): SpielelementDO =
		SpielelementDO(
			lokalisierung = lokalisierung,
			selbstErstellt = dies.selbstErstellt,
			inaktiv = dies.inaktiv,
			favorisiert = dies.favorisiert
		)
}
