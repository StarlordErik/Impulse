package de.seleri.core.domain.model.sammlungen

import de.seleri.core.domain.model.ids.BestandteilID

interface Sammlung<ID: BestandteilID> {

	val bestandteile: Collection<ID>
}
