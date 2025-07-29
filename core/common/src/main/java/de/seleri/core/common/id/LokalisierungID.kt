package de.seleri.core.common.id

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class LokalisierungID(override val id: Int): EntityID
