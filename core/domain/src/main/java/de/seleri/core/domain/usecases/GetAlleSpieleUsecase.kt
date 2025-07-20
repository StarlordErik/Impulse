package de.seleri.core.domain.usecases

import de.seleri.core.domain.model.spielelemente.Spiel
import de.seleri.core.domain.repositories.SpielRepo

class GetAlleSpieleUsecase(private val spielRepo: SpielRepo) {

	suspend operator fun invoke(): List<Spiel> {
		return spielRepo.getAlleSpiele()
	}
}
