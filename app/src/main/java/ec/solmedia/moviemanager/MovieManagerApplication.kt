package ec.solmedia.moviemanager

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class MovieManagerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if(LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)
    }
}