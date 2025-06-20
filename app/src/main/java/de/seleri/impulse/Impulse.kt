package de.seleri.impulse

import android.app.Application
import de.seleri.spielelemente.Datenbanksystem

class Impulse : Application() {
    lateinit var dbs: Datenbanksystem

    override fun onCreate() {
        super.onCreate()
        dbs = Datenbanksystem.generieren(this)
    }
}
