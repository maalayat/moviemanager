package ec.solmedia.themoviedb

import android.app.Application
import com.devs.acr.AutoErrorReporter
import com.squareup.leakcanary.LeakCanary
import ec.solmedia.themoviedb.di.component.ApplicationComponent
import ec.solmedia.themoviedb.di.component.DaggerApplicationComponent
import ec.solmedia.themoviedb.di.module.ApplicationModule


class VimoApp : Application() {

    companion object {
        lateinit var graph: ApplicationComponent

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
        setupCrashReports()
        setupDagger()
    }

    private fun setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    private fun setupCrashReports() {
        AutoErrorReporter.get(this)
                .setEmailAddresses("alejo.ay@gmail.com")
                .setEmailSubject("VimoApp Crash Report")
                .start()
    }

    private fun setupDagger() {
        graph = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}