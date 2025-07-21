package de.seleri.core.domain.model.spielelemente

import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.DEFAULT_BILD_DATEINAME
import de.seleri.core.domain.model.DEFAULT_FAVORISIERT
import de.seleri.core.domain.model.DEFAULT_ID
import de.seleri.core.domain.model.DEFAULT_INAKTIV
import de.seleri.core.domain.model.DEFAULT_OG_SPRACHE
import de.seleri.core.domain.model.DEFAULT_SELBST_ERSTELLT
import de.seleri.core.domain.model.ids.BestandteilID
import de.seleri.core.domain.model.metadaten.SpielMeta
import de.seleri.core.domain.model.metadaten.SpielMetaDaten
import de.seleri.core.domain.model.spielelemente.sammlungen.Sammlung
import de.seleri.core.domain.model.spielelemente.sammlungen.SammlungDaten

class Spiel(
	id: Int = DEFAULT_ID,

	lokalisierungen: Collection<BestandteilID.LokalisierungID>,

	ogSprache: Sprache = DEFAULT_OG_SPRACHE,
	selbstErstellt: Boolean = DEFAULT_SELBST_ERSTELLT,
	inaktiv: Boolean = DEFAULT_INAKTIV,
	favorisiert: Boolean = DEFAULT_FAVORISIERT,

	private val sammlungDaten: SammlungDaten<BestandteilID.KategorieID>,

	bildDateiname: String? = DEFAULT_BILD_DATEINAME,

	val anleitung: String? = null,
	val texteProKarte: Int = 1,
): Spielelement by SpielelementDaten(id, lokalisierungen, ogSprache, selbstErstellt, inaktiv, favorisiert),
	Sammlung<BestandteilID.KategorieID> by sammlungDaten, SpielMeta by SpielMetaDaten(bildDateiname)

