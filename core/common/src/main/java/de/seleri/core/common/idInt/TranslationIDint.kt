package de.seleri.core.common.idInt

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class TranslationIDint(val translationID: Int): EntityIDint
