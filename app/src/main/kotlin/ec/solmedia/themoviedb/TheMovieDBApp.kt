package ec.solmedia.themoviedb

import android.app.Application
import com.squareup.leakcanary.LeakCanary
import ec.solmedia.themoviedb.di.*


class TheMovieDBApp : Application() {

    companion object {
        lateinit var nowPlayingComponent: NowPlayingComponent
        lateinit var upcomingComponent: UpcomingComponent
        val BASE_URL = "https://api.themoviedb.org/3/"
    }

    override fun onCreate() {
        super.onCreate()

        initLeakCanary()
        initNowPlayingComponent()
        initUpcomingComponent()
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