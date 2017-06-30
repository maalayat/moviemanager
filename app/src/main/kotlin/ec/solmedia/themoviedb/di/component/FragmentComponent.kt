package ec.solmedia.themoviedb.di.component

import dagger.Subcomponent
import ec.solmedia.themoviedb.di.scope.FragmentScope
import ec.solmedia.themoviedb.view.fragment.MediaFragment

@FragmentScope
@Subcomponent()
interface FragmentComponent {
    fun inject(mediaFragment: MediaFragment)
}