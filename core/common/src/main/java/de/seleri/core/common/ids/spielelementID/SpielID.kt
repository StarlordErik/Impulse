package de.seleri.core.common.ids.spielelementID

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class SpielID(override val id: Int): SpielelementID, SammlungID
