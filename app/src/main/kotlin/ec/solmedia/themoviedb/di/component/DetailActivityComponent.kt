package ec.solmedia.themoviedb.di.component

import dagger.Subcomponent
import ec.solmedia.themoviedb.di.scope.ActivityScope
import ec.solmedia.themoviedb.view.activity.MediaDetailActivity

@ActivityScope
@Subcomponent
interface DetailActivityComponent {
    fun inject(detailActivity: MediaDetailActivity)
}