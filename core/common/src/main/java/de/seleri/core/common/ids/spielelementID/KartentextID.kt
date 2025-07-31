package de.seleri.core.common.ids.spielelementID

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class KartentextID(override val value: Int): SpielelementID, BestandteilID
