package ec.solmedia.themoviedb.di.component

import dagger.Component
import ec.solmedia.themoviedb.di.module.AdapterModule
import ec.solmedia.themoviedb.di.module.ApplicationModule
import ec.solmedia.themoviedb.di.module.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class), (NetworkModule::class)])
interface ApplicationComponent {
    fun plus(module: AdapterModule): AdapterComponent
    fun plus(): FragmentComponent
    fun plusDetail(): DetailActivityComponent
}