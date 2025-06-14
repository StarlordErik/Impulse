package de.seleri.impulse

import android.app.Application
import de.seleri.spielelemente.Datenbanksystem
import de.seleri.spielelemente.datenbanksystemGenerieren

class Impulse : Application() {
    lateinit var dbs: Datenbanksystem

    override fun onCreate() {
        super.onCreate()
        dbs = datenbanksystemGenerieren(this)
    }
}
