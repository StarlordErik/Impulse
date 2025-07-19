package de.seleri.kern.daten.entities.singles

import de.seleri.kern.daten.utils.Herkunft

data class SpielelementAttribute(
  val herkunft: Herkunft = Herkunft.OG,
  val inaktiv: Boolean = false,
)
