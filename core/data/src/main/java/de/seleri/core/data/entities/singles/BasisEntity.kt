package de.seleri.core.data.entities.singles

import de.seleri.core.Sprache

data class BasisEntity(
  val ogSprache: Sprache = Sprache.DE,
  val inaktiv: Boolean = false,
  val selbstErstellt: Boolean = false,
  val favorisiert: Boolean = false,
)
