package ec.solmedia.themoviedb.di.component

import dagger.Subcomponent
import ec.solmedia.themoviedb.di.module.AdapterModule
import ec.solmedia.themoviedb.di.scope.ActivityScope
import ec.solmedia.themoviedb.view.activity.MainActivity

@ActivityScope
@Subcomponent(modules = arrayOf(AdapterModule::class))
interface AdapterComponent {
    fun inject(mainActivity: MainActivity)
}