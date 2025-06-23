package de.seleri.impulse

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import de.seleri.spielelemente.Sprachen
import de.seleri.spielelemente.findeElement
import androidx.core.graphics.toColorInt

const val KATEGORIE_TEXTGROESSE = 22f
const val PADDING = 32

class SpielActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_spiel)

        val dbs = (application as Impulse).dbs

        val spielName = intent.getStringExtra("SPIEL_NAME") ?: return
        val spiel = findeElement(spielName, dbs.spiele) ?: return

        val container = findViewById<LinearLayout>(R.id.container_buttons)

        for (kategorie in spiel.getAlleAktuellenKategorien()) {
            val karten = kategorie.getAlleAktuellenKarten().map {
                it.localizations[Sprachen.OG]
            }

            val button = Button(this).apply {
                text = kategorie.localizations[Sprachen.OG]
                textSize = KATEGORIE_TEXTGROESSE
                setTextColor(Color.WHITE)
                setBackgroundColor("#4E2A2A".toColorInt())
                isAllCaps = false
                setPadding(PADDING, PADDING, PADDING, PADDING) // links, oben, rechts, unten

                setOnClickListener {
                    val randomText = karten.randomOrNull()
                    if (randomText != null) {
                        text = randomText
                    }
                }

                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, PADDING)
                layoutParams = params
            }

            container.addView(button)
        }
    }

    fun klickEinstellungen() {
        TODO()
    }
    fun letzteKarte(){
        TODO()
    }
    fun entferneKarte() {
        TODO()
    }
}
