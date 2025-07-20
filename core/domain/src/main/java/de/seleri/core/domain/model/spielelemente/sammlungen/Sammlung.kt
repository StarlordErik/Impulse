package de.seleri.core.domain.model.spielelemente.sammlungen

import de.seleri.core.common.DatenbankObjektID

interface Sammlung {

	fun karte(texteProKarte: Int): List<DatenbankObjektID.KartentextID>
}
