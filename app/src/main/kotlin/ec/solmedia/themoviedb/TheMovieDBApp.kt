package ec.solmedia.themoviedb

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import ec.solmedia.themoviedb.di.*
import java.util.*


class TheMovieDBApp : Application() {

    companion object {
        lateinit var nowPlayingComponent: NowPlayingComponent
        lateinit var upcomingComponent: UpcomingComponent
        val BASE_URL = "https://api.themoviedb.org/3/"
        lateinit var sDefSystemLanguage: String
    }

    override fun onCreate() {
        super.onCreate()

        initLanguage()
        initLeakCanary()
        initNowPlayingComponent()
        initUpcomingComponent()
    }

    private fun initLanguage() {
        sDefSystemLanguage = Locale.getDefault().language
    }

    private fun initLeakCanary() {
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