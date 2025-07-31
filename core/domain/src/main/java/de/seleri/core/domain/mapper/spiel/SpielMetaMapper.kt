package de.seleri.core.domain.mapper.spiel

import de.seleri.core.common.entities.singles.spielelemente.SpielEntity
import de.seleri.core.domain.mapper.SpielelementMapper
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDO

object SpielMetaMapper {

	operator fun invoke(dies: SpielEntity, lokalisierung: Lokalisierung): SpielMetaDO =
		SpielMetaDO(
			spielelementDaten = SpielelementMapper(dies, lokalisierung), bildDateiname = dies.bildDateiname
		)
}
