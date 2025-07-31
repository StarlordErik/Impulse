package de.seleri.core.domain.mapper

import de.seleri.core.common.entities.singles.spielelemente.SpielEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.spiel.SpielMetaDaten

object SpielMetaMapper {

	operator fun invoke(dies: SpielEntity, lokalisierung: Lokalisierung): SpielMetaDaten =
		SpielMetaDaten(
			spielelementDaten = SpielelementMapper(dies, lokalisierung), bildDateiname = dies.bildDateiname
		)
}
