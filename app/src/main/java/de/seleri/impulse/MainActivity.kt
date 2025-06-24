package de.seleri.impulse

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import de.seleri.spielelemente.Sprachen
import androidx.core.graphics.toColorInt

const val EIN_DRITTEL = 1.0 / 3.0
const val TEXTGROESSE = 24f

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val dbs = (application as Impulse).dbs
        val buttonContainer = findViewById<LinearLayout>(R.id.button_container)

        for (spiel in dbs.spiele) {

            // der Release veröffentlicht nur bestimmte Spiele
            if (spiel.id != 3 && spiel.id != 2) continue

            val button = Button(this).apply {
                text = spiel.localizations[Sprachen.OG]
                textSize = TEXTGROESSE
                setTextColor(resources.getColor(android.R.color.white, null))
                background = resources.getDrawable(R.drawable.rounded_corner, null)
                isAllCaps = false
                setOnClickListener {
                    val intent = Intent(this@MainActivity, SpielActivity::class.java)
                    intent.putExtra("SPIEL_NAME", spiel.localizations[Sprachen.OG])
                    startActivity(intent)
                }
                val params = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                params.setMargins(0, 0, 0, PADDING)
                layoutParams = params
            }
            buttonContainer.addView(button)
        }

        // Titelhöhe dynamisch setzen
        val titleContainer = findViewById<FrameLayout>(R.id.title_container)
        val bildschirmhoehe = resources.displayMetrics.heightPixels
        val einDrittelBildschirmhoehe = (EIN_DRITTEL * bildschirmhoehe).toInt()

        titleContainer.layoutParams.height = einDrittelBildschirmhoehe
        titleContainer.requestLayout()
    }
}
