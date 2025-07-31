package de.seleri.core.common.ids.spielelementID

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class SpielID(override val value: Int): SpielelementID, SammlungID
