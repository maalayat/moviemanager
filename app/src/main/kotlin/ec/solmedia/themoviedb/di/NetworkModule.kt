package ec.solmedia.themoviedb.di

import dagger.Module
import dagger.Provides
import ec.solmedia.themoviedb.TheMovieDBApp
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(TheMovieDBApp.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}