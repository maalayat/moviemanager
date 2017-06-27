package ec.solmedia.themoviedb.di

import dagger.Component
import ec.solmedia.themoviedb.view.activity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, AdapterModule::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}