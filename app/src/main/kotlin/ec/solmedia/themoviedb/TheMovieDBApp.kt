package ec.solmedia.themoviedb

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import ec.solmedia.themoviedb.di.*


class TheMovieDBApp : Application() {

    companion object {
        lateinit var mainComponent: MainComponent
        lateinit var nowPlayingComponent: NowPlayingComponent
        lateinit var upcomingComponent: UpcomingComponent

        val BASE_URL = "https://api.themoviedb.org/3/"
        val LOCALE_KEY = "LocaleKey"
        val SHARED_PREFERENCES_NAME = "UserPrefs"
    }

    override fun onCreate() {
        super.onCreate()

        setupLeakCanary()

        initMainComponent()
        initNowPlayingComponent()
        initUpcomingComponent()
    }

    private fun initMainComponent() {
        mainComponent = DaggerMainComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    private fun initNowPlayingComponent() {
        nowPlayingComponent = DaggerNowPlayingComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun initUpcomingComponent() {
        upcomingComponent = DaggerUpcomingComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}