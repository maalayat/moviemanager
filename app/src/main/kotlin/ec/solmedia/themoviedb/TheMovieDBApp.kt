package ec.solmedia.themoviedb

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import ec.solmedia.themoviedb.di.*


class TheMovieDBApp : Application() {

    companion object {
        lateinit var mainComponent: MainComponent
        lateinit var mediaComponent: MediaComponent

        val BASE_URL = "https://api.themoviedb.org/3/"
        val BASE_IMAGE = "https://image.tmdb.org/t/p/"
        val PATH_BACKDROP = "w780"
        val PATH_POSTER = "w342"
        val LOCALE_KEY = "LocaleKey"
        val SHARED_PREFERENCES_NAME = "UserPrefs"
    }

    override fun onCreate() {
        super.onCreate()

        setupLeakCanary()

        initMainComponent()
        initMediaComponent()
    }

    private fun setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    private fun initMainComponent() {
        mainComponent = DaggerMainComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun initMediaComponent() {
        mediaComponent = DaggerMediaComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}