package de.seleri.core.domain.model.spielelemente.sammlungen


sealed class Fremdschluessel {

	abstract val fremdschluesselID: Int

	fun toInt(): Int =
		fremdschluesselID

	class LokalisierungID(override val fremdschluesselID: Int): Fremdschluessel()

	class KartentextID(override val fremdschluesselID: Int): Fremdschluessel()

	class KategorieID(override val fremdschluesselID: Int): Fremdschluessel()
}


