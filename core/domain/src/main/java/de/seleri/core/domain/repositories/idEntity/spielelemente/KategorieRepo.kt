package de.seleri.core.domain.repositories.idEntity.spielelemente

import de.seleri.core.common.entities.singles.spielelemente.KategorieEntity
import de.seleri.core.common.ids.spielelementID.KategorieID
import de.seleri.core.domain.model.idEntity.spielelemente.Kategorie

interface KategorieRepo: BestandteilRepo<Kategorie, KategorieEntity, KategorieID>
