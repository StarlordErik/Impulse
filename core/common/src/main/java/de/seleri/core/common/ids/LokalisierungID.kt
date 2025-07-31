package de.seleri.core.common.ids

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class LokalisierungID(override val value: Int): EntityID
