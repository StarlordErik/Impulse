package de.seleri.core.domain.repositories

import de.seleri.core.common.ids.LokalisierungID
import de.seleri.core.domain.modell.Lokalisierung
import de.seleri.core.domain.repositories.base.GetByIDRepo

interface LokalisierungRepo: GetByIDRepo<Lokalisierung, LokalisierungID>
