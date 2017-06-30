package ec.solmedia.themoviedb.di.module

import dagger.Module
import dagger.Provides
import ec.solmedia.themoviedb.TheMovieDBApp
import ec.solmedia.themoviedb.api.TheMovieDBAPI
import ec.solmedia.themoviedb.api.TheMovieDBRestAPI
import ec.solmedia.themoviedb.api.TheMovieDBService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMediaRestAPI(theMovieDBService: TheMovieDBService): TheMovieDBAPI {
        return TheMovieDBRestAPI(theMovieDBService)
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): TheMovieDBService {
        return retrofit.create(TheMovieDBService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(TheMovieDBApp.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}