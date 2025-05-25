package de.seleri.impulse

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.impulse.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    }
}
