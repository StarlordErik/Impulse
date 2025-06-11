package de.seleri.impulse

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import de.seleri.spielelemente.Sprachen
import de.seleri.spielelemente.findeElement
import kotlin.random.Random

class Spiel1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_spiel1)

        val dbs = (application as Impulse).dbs

        val spiel = findeElement("We're not really strangers", dbs.spiele)!!
        val perception = findeElement(
            "Level 1: Perception", spiel.getAlleAktuellenKategorien()
        )!!.getAlleAktuellenKarten().map { it.localizations[Sprachen.OG] }
        val connection = findeElement(
            "Level 2: Connection", spiel.getAlleAktuellenKategorien()
        )!!.getAlleAktuellenKarten().map { it.localizations[Sprachen.OG] }
        val reflection = findeElement(
            "Level 3: Reflection", spiel.getAlleAktuellenKategorien()
        )!!.getAlleAktuellenKarten().map { it.localizations[Sprachen.OG] }

        val btnSpiel11 = findViewById<Button>(R.id.btnSpiel11)
        val btnSpiel12 = findViewById<Button>(R.id.btnSpiel12)
        val btnSpiel13 = findViewById<Button>(R.id.btnSpiel13)

        // Klick-Listener für btnSpiel1
        btnSpiel11.setOnClickListener {
            val randomIndex = Random.nextInt(perception.size)
            btnSpiel11.text = perception[randomIndex]
        }

        // Klick-Listener für btnSpiel2
        btnSpiel12.setOnClickListener {
            val randomIndex = Random.nextInt(connection.size)
            btnSpiel12.text = connection[randomIndex]
        }

        // Klick-Listener für btnSpiel3
        btnSpiel13.setOnClickListener {
            val randomIndex = Random.nextInt(reflection.size)
            btnSpiel13.text = reflection[randomIndex]
        }
    }
}
