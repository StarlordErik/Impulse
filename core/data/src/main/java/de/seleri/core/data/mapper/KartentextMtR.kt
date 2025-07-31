package de.seleri.core.data.mapper

import de.seleri.core.data.entities.singles.spielelemente.KartentextRoom
import de.seleri.core.domain.modell.spielelemente.Kartentext

fun Kartentext.toRoom(): KartentextRoom =
	KartentextRoom(
		lokalisierungID = this.lokalisierung.id,
		selbstErstellt = this.selbstErstellt,
		inaktiv = this.inaktiv,
		favorisiert = this.favorisiert,
		gesehen = this.gesehen,
		besprochen = this.besprochen
	)
