package de.seleri.core.common.idInt

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class AbstractEntityIDint(val id: Int): EntityIDint
