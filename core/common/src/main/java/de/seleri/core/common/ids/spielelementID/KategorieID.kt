package de.seleri.core.common.ids.spielelementID

import kotlinx.serialization.Serializable

@JvmInline
@Serializable
value class KategorieID(override val value: Int): SpielelementID, BestandteilID, SammlungID
