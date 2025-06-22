package de.seleri.impulse

import android.app.Application
import de.seleri.spielelemente.Datenbanksystem

/**
 * Die zentrale `Application`-Klasse der App.
 *
 * Beim Start der App wird das YAML-basierte [Datenbanksystem] initialisiert
 * und als globales Objekt namens [dbs] zur Verfügung gestellt.
 * Dadurch kann von überall innerhalb der App auf dieselbe Datenbankinstanz zugegriffen werden.
 */
class Impulse : Application() {

    /**
     * Instanz des [Datenbanksystem]s, das beim App-Start geladen wird.
     *
     * Wird über [onCreate] initialisiert und stellt die zentrale Datenquelle der App dar.
     */
    lateinit var dbs: Datenbanksystem

    /**
     * Wird beim Start der App aufgerufen und initialisiert das [Datenbanksystem].
     */
    override fun onCreate() {
        super.onCreate()
        dbs = Datenbanksystem.generieren(this)
    }
}
