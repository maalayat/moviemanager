package ec.solmedia.themoviedb.di

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import dagger.Module
import dagger.Provides
import ec.solmedia.themoviedb.R
import ec.solmedia.themoviedb.di.qualifier.movie.CategoriesMovieQualifier
import ec.solmedia.themoviedb.di.qualifier.movie.MovieQualifier
import ec.solmedia.themoviedb.di.qualifier.movie.TitlesMovieQualifier
import ec.solmedia.themoviedb.di.qualifier.movie.TypeMovieQualifier
import ec.solmedia.themoviedb.di.qualifier.tv.CategoriesTvQualifier
import ec.solmedia.themoviedb.di.qualifier.tv.TitlesTvQualifier
import ec.solmedia.themoviedb.di.qualifier.tv.TvQualifier
import ec.solmedia.themoviedb.di.qualifier.tv.TypeTvQualifier
import ec.solmedia.themoviedb.view.fragment.adapter.MediaPagerAdapter
import javax.inject.Singleton

@Module
class AdapterModule(val fragmentActivity: FragmentActivity, val context: Context) {

    @Provides
    @Singleton
    @TvQualifier
    fun providesTvMediaPageAdapter(
            fm: FragmentManager,
            @TypeTvQualifier mediaType: String,
            @CategoriesTvQualifier categories: List<String>,
            @TitlesTvQualifier titles: List<String>
    ): MediaPagerAdapter {
        return MediaPagerAdapter(fm, mediaType, categories, titles)
    }

    @Provides
    @Singleton
    @MovieQualifier
    fun providesMovieMediaPageAdapter(
            fm: FragmentManager,
            @TypeMovieQualifier mediaType: String,
            @CategoriesMovieQualifier categories: List<String>,
            @TitlesMovieQualifier titles: List<String>
    ): MediaPagerAdapter {
        return MediaPagerAdapter(fm, mediaType, categories, titles)
    }

    @Provides
    @Singleton
    @TypeTvQualifier
    fun providesTvMediaType(): String {
        return context.getString(R.string.tv_type)
    }

    @Provides
    @Singleton
    @CategoriesTvQualifier
    fun providesTvCategories(): List<String> {
        return listOf(
                context.getString(R.string.tv_category_popular),
                context.getString(R.string.tv_category_top_rated),
                context.getString(R.string.tv_category_on_the_air),
                context.getString(R.string.tv_category_airing_today)
        )
    }

    @Provides
    @Singleton
    @TitlesTvQualifier
    fun providesTvTitles(): List<String> {
        return listOf(
                context.getString(R.string.nav_item_tv_popular),
                context.getString(R.string.nav_item_tv_top_rated),
                context.getString(R.string.nav_item_tv_ontv),
                context.getString(R.string.nav_item_tv_airing_today)
        )
    }

    @Provides
    @Singleton
    fun providesFragmentManager(): FragmentManager {
        return fragmentActivity.supportFragmentManager
    }

    @Provides
    @Singleton
    @TypeMovieQualifier
    fun providesMovieMediaType(): String {
        return context.getString(R.string.movie_type)
    }

    @Provides
    @Singleton
    @CategoriesMovieQualifier
    fun providesMovieCategories(): List<String> {
        return listOf(
                context.getString(R.string.movie_category_now_playing),
                context.getString(R.string.movie_category_upcoming),
                context.getString(R.string.movie_category_popular),
                context.getString(R.string.movie_category_top_rated)
        )
    }

    @Provides
    @Singleton
    @TitlesMovieQualifier
    fun providesMovieTitles(): List<String> {
        return listOf(
                context.getString(R.string.nav_item_now_playing),
                context.getString(R.string.nav_item_upcoming),
                context.getString(R.string.nav_item_popular),
                context.getString(R.string.nav_item_top_rated)
        )
    }
}