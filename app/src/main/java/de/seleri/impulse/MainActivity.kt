package de.seleri.impulse

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import de.seleri.spielelemente.Datenbanksystem
import javax.inject.Inject
import javax.inject.Singleton


@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    @Inject
    lateinit var dbs: Datenbanksystem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImpulseTheme {
                Navigation(dbs)
            }
        }
    }
}


@HiltAndroidApp
class Impulse: Application()

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatenbanksystem(@ApplicationContext context: Context): Datenbanksystem {
        return Datenbanksystem.generieren(context)
    }
}

@HiltViewModel
class MainViewModel @Inject constructor(
    val dbs: Datenbanksystem
): ViewModel()

