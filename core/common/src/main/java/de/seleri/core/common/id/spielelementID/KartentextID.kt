package de.seleri.core.common.id.spielelementID

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class KartentextID(override val id: Int): SpielelementID, BestandteilID
