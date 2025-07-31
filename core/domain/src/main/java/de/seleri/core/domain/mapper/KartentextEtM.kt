package de.seleri.core.domain.mapper

import de.seleri.core.common.entities.singles.spielelemente.SpielelementEntity
import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.domain.modell.spielelemente.SpielelementDaten

fun KartentextRoom.toDomain(lokalisierung: Lokalisierung): Kartentext =
	Kartentext(
		spielelementDaten = this.toDomainL(lokalisierung: Lokalisierung),
		gesehen = this.gesehen,
		besprochen = this.besprochen
	)

fun SpielelementEntity.toDomainL(lokalisierung: Lokalisierung): SpielelementDaten =
	SpielelementDaten(
		lokalisierung = lokalisierung,
		selbstErstellt = this.selbstErstellt,
		inaktiv = this.inaktiv,
		favorisiert = this.favorisiert
	)
