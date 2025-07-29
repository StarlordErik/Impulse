package de.seleri.core.common.idInt

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class LokalisierungIDint(val lokalisierungID: Int): EntityIDint
