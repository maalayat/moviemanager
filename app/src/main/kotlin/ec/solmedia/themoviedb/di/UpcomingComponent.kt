package ec.solmedia.themoviedb.di

import dagger.Component
import ec.solmedia.themoviedb.view.fragment.UpcomingFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        MediaModule::class,
        NetworkModule::class)
)
interface UpcomingComponent {
    fun inject(upcomingFragment: UpcomingFragment)
}