package de.seleri.core.common.id.spielelementID

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class SpielID(override val id: Int): SpielelementID, SammlungID
