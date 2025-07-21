package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.Bestandteil
import de.seleri.core.domain.model.DEFAULT_FAVORISIERT
import de.seleri.core.domain.model.DEFAULT_ID
import de.seleri.core.domain.model.DEFAULT_INAKTIV
import de.seleri.core.domain.model.DEFAULT_OG_SPRACHE
import de.seleri.core.domain.model.DEFAULT_SELBST_ERSTELLT
import de.seleri.core.domain.model.ids.BestandteilID

class Kartentext(
	id: Int = DEFAULT_ID,

	lokalisierungen: Collection<BestandteilID.LokalisierungID>,

	ogSprache: Sprache = DEFAULT_OG_SPRACHE,
	selbstErstellt: Boolean = DEFAULT_SELBST_ERSTELLT,
	inaktiv: Boolean = DEFAULT_INAKTIV,
	favorisiert: Boolean = DEFAULT_FAVORISIERT,

	val gesehen: Boolean = false,
	val besprochen: Boolean = false,
): Spielelement by SpielelementDaten(id, lokalisierungen, ogSprache, selbstErstellt, inaktiv, favorisiert),
	Bestandteil
