package ec.solmedia.themoviedb.di

import dagger.Module
import dagger.Provides
import ec.solmedia.themoviedb.api.TheMovieDBAPI
import ec.solmedia.themoviedb.api.TheMovieDBRestAPI
import ec.solmedia.themoviedb.api.TheMovieDBService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class MediaModule {

    @Provides
    @Singleton
    fun provideMediaRestAPI(theMovieDBService: TheMovieDBService): TheMovieDBAPI {
        return TheMovieDBRestAPI(theMovieDBService)
    }

    @Provides
    @Singleton
    fun provideRetrofit(retrofit: Retrofit): TheMovieDBService {
        return retrofit.create(TheMovieDBService::class.java)
    }
}