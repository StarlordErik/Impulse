package de.seleri.core.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import de.seleri.core.data.entities.joins.KategorieXKartentext
import de.seleri.core.data.entities.singles.spielelemente.KartentextEntity
import de.seleri.core.data.entities.singles.spielelemente.KategorieEntity

data class KategorieMitKartentexten(
	@Embedded
	val kategorie: KategorieEntity,
	@Relation(
		parentColumn = "id", entityColumn = "id", associateBy = Junction(
			value = KategorieXKartentext::class, parentColumn = "kategorieID", entityColumn = "kartentextID"
		)
	)
	val kartentexte: List<KartentextEntity>,
)
