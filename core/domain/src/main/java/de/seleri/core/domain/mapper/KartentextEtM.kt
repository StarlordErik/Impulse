package de.seleri.core.domain.mapper

import de.seleri.core.common.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext

fun KartentextEntity.toDomain(lokalisierung: Lokalisierung): Kartentext =
	Kartentext(
		spielelementDaten = SpielelementEtM(this, lokalisierung),
		gesehen = this.gesehen,
		besprochen = this.besprochen
	)
