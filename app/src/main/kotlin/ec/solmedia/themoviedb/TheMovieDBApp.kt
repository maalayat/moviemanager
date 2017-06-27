package ec.solmedia.themoviedb

import android.app.Application
import android.support.v4.app.FragmentActivity
import com.squareup.leakcanary.LeakCanary
import ec.solmedia.themoviedb.di.*


class TheMovieDBApp : Application() {

    companion object {
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
        initMediaComponent()
    }

    private fun setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }

    fun getMainComponent(fragmentActivity: FragmentActivity): MainComponent {
        return DaggerMainComponent.builder()
                .appModule(AppModule(this))
                .adapterModule(AdapterModule(fragmentActivity, this))
                .build()
    }

    private fun initMediaComponent() {
        mediaComponent = DaggerMediaComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}