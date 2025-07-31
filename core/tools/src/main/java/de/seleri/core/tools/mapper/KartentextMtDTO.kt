package de.seleri.core.tools.mapper

import de.seleri.core.domain.modell.spielelemente.Kartentext
import de.seleri.core.tools.dtos.singles.spielelemente.KartentextDTO

fun Kartentext.toDTO(): KartentextDTO =
	KartentextDTO(
		lokalisierungID = this.lokalisierung.id,
		selbstErstellt = this.selbstErstellt,
		inaktiv = this.inaktiv,
		favorisiert = this.favorisiert,
		gesehen = this.gesehen,
		besprochen = this.besprochen
	)
