package de.seleri.kern.daten.entities.singles

import de.seleri.kern.Sprache

data class SpielelementAttribute(
  val ogSprache: Sprache = Sprache.DE,
  val inaktiv: Boolean = false,
  val selbstErstellt: Boolean = false,
)
