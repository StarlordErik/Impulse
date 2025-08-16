package de.seleri.core.data.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import de.seleri.core.common.Sprache
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie
import de.seleri.core.domain.useCases.create.NewKategorieUC
import de.seleri.core.domain.useCases.create.NewSpielUC
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class Callback(
	private val context: Context, private val newSpielUC: NewSpielUC, private val newKategorieUC: NewKategorieUC
): RoomDatabase.Callback() {

	override fun onCreate(db: SupportSQLiteDatabase) {
		super.onCreate(db)

		CoroutineScope(Dispatchers.IO).launch {
			seed()
		}
	}

	@Serializable
	data class SpielMetadaten(val ogSprache: Sprache)

	private suspend fun seed() {
		// alle Ordner im Assets-Root -> Spiele
		val alleSpielOrdner = context.assets
			.list("")
			?.toList()
			?: emptyList()

		for (spielOrdner in alleSpielOrdner) {
			// prüfen ob es ein Ordner ist, der Spielmetadaten enthält
			val children = context.assets.list(spielOrdner)
				?: continue
			if (!children.contains("SpielMetadaten.json")) continue

			// Spielmetadaten laden
			val metadataJson = readJsonFile(context, "$spielOrdner/SpielMetadaten.json")
			val ogSprache = Json.decodeFromString<SpielMetadaten>(metadataJson).ogSprache

			val spielTranslationen = readJsonFile(context, "$spielOrdner/Lokalisierung.json")
				.let { Json.decodeFromString<Map<String, String>>(it) }
				.mapKeys { Sprache.valueOf(it.key) }

			val kategorien = mutableListOf<Kategorie>()

			// Kategorien-Ordner: alle Ordner innerhalb von spielOrdner außer den Metadaten-Dateien
			val kategorienOrdner = children.filterNot { it.endsWith(".json") }

			for (kategorieOrdner in kategorienOrdner) {
				val kategoriePfad = "$spielOrdner/$kategorieOrdner"
				val categoryChildren = context.assets.list(kategoriePfad)
					?: continue

				if (!categoryChildren.contains("Lokalisierung.json")) continue

				val kategorieTranslationen = readJsonFile(context, "$kategoriePfad/Lokalisierung.json")
					.let { Json.decodeFromString<Map<String, String>>(it) }
					.mapKeys { Sprache.valueOf(it.key) }

				// alle Sprachdateien (z.B. DE.json, EN.json)
				val kartentextTranslationen = categoryChildren.filter { it.endsWith(".json") && it != "Lokalisierung.json" }

				val sprachen = kartentextTranslationen
					.map { it.removeSuffix(".json") }
					.map { Sprache.valueOf(it) }

				val kartentexte = readKartentexte(context, kategoriePfad, sprachen)

				val kategorie = newKategorieUC(kategorieTranslationen, ogSprache, kartentexte)
				kategorien.add(kategorie)
			}

			newSpielUC(spielTranslationen, ogSprache, kategorien)
		}
	}

	suspend fun readJsonFile(context: Context, pfad: String): String {
		return withContext(Dispatchers.IO) {
			context.assets
				.open(pfad)
				.bufferedReader()
				.use { it.readText() }
		}
	}

	suspend fun readKartentexte(
		context: Context, kategoriePfad: String, sprachen: List<Sprache>
	): List<Map<Sprache, String>> {
		val translationenProSprache: Map<Sprache, List<String>> = sprachen.associateWith { sprache ->
			val json = readJsonFile(context, "$kategoriePfad/${sprache.name}.json")
			Json.decodeFromString<List<String>>(json)
		}

		val count = translationenProSprache.values.first().size
		return (0 until count).map { index ->
			sprachen.associateWith { lang -> translationenProSprache[lang]!![index] }
		}
	}
}
