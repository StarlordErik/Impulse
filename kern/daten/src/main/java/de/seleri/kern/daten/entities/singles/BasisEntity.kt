package de.seleri.kern.daten.entities.singles

import de.seleri.kern.Sprache

data class BasisEntity(
  val ogSprache: Sprache = Sprache.DE,
  val inaktiv: Boolean = false,
  val selbstErstellt: Boolean = false,
  val favorisiert: Boolean = false,
)
