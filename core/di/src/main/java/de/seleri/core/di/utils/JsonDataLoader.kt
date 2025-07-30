package de.seleri.core.di.utils

import android.content.Context
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

class JsonDataLoader(val context: Context) {

	inline fun <reified T> loadList(fileName: String): List<T> {
		val input = context.assets
			.open(fileName)
			.bufferedReader()
			.use { it.readText() }
		val json = Json { ignoreUnknownKeys = true }
		return json.decodeFromString(ListSerializer(serializer()), input)
	}
}
