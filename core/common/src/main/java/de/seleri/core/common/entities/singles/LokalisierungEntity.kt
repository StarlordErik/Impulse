package de.seleri.core.common.entities.singles

import de.seleri.core.common.Sprache
import de.seleri.core.common.ids.LokalisierungID

interface LokalisierungEntity: SingleEntity {

	override val id: LokalisierungID get() = LokalisierungID(lokalisierungID)

	val lokalisierungID: Int // als einziges Mal ein normaler Int aufgrund der autoGenerate-Funktion von Room

	val ogSprache: Sprache
}
