package de.seleri.core.common.id

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class TranslationID(override val id: Int): EntityID
