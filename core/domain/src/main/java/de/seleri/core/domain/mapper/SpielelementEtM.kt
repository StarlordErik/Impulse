package de.seleri.core.domain.mapper

import de.seleri.core.common.entities.singles.spielelemente.SpielelementEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

object SpielelementEtM {

	operator fun invoke(dies: SpielelementEntity, lokalisierung: Lokalisierung): SpielelementDaten {
		return SpielelementDaten(
			lokalisierung = lokalisierung,
			selbstErstellt = dies.selbstErstellt,
			inaktiv = dies.inaktiv,
			favorisiert = dies.favorisiert
		)
	}
}
