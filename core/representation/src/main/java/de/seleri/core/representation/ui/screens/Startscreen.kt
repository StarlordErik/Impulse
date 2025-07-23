package de.seleri.core.representation.ui.screens

import androidx.compose.runtime.Composable
import de.seleri.core.domain.model.idTypes.SpielelementID
import de.seleri.core.representation.viewModels.StartscreenVM

@Composable
fun Startscreen(
	viewModel: StartscreenVM, onSpielMetaClicked: (SpielelementID.SpielID) -> Unit
) {
	val spielMetas = viewModel.spielMetaObjekte
}
