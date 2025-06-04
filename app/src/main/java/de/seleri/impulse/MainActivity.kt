package de.seleri.impulse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

const val EIN_DRITTEL = 1.0 / 3.0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.hide() // ActionBar ausblenden

        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.button_screen1)
        val button2: Button = findViewById(R.id.button_screen2)

        button1.setOnClickListener {
            val intent = Intent(this, Spiel1Activity::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, Spiel2Activity::class.java)
            startActivity(intent)
        }

        // Titelhöhe dynamisch setzen
        val titleContainer = findViewById<FrameLayout>(R.id.title_container)
        val bildschirmhoehe = resources.displayMetrics.heightPixels
        val einDrittelBildschirmhoehe = (EIN_DRITTEL * bildschirmhoehe).toInt()

        titleContainer.layoutParams.height = einDrittelBildschirmhoehe
        titleContainer.requestLayout()
    }

}
