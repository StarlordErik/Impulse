package de.seleri.core.domain.repositories

import de.seleri.core.domain.model.spielelemente.Kartentext

interface KartentextRepo: DatenbankObjektRepo<Kartentext>, BestandteilRepo<Kartentext>
