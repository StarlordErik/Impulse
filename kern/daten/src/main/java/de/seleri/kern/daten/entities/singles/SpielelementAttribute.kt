package de.seleri.kern.daten.entities.singles

import de.seleri.kern.Herkunft

data class SpielelementAttribute(
  val herkunft: Herkunft = Herkunft.OG,
  val inaktiv: Boolean = false,
)
