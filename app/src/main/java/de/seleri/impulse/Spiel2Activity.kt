package de.seleri.impulse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.impulse.R

class Spiel2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContentView(R.layout.activity_spiel2)
    }
}
