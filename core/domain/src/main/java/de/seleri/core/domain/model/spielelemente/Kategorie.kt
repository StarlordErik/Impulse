package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.Bestandteil
import de.seleri.core.domain.model.DEFAULT_FAVORISIERT
import de.seleri.core.domain.model.DEFAULT_ID
import de.seleri.core.domain.model.DEFAULT_INAKTIV
import de.seleri.core.domain.model.DEFAULT_OG_SPRACHE
import de.seleri.core.domain.model.DEFAULT_SELBST_ERSTELLT
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

class Kategorie(
	id: Int = DEFAULT_ID,

	lokalisierungen: Collection<BestandteilID.LokalisierungID>,

	ogSprache: Sprache = DEFAULT_OG_SPRACHE,
	selbstErstellt: Boolean = DEFAULT_SELBST_ERSTELLT,
	inaktiv: Boolean = DEFAULT_INAKTIV,
	favorisiert: Boolean = DEFAULT_FAVORISIERT,

	private val sammlungDaten: SammlungDaten<BestandteilID.KartentextID>,
): Spielelement by SpielelementDaten(id, lokalisierungen, ogSprache, selbstErstellt, inaktiv, favorisiert),
	Sammlung<BestandteilID.KartentextID> by sammlungDaten,
	Bestandteil
