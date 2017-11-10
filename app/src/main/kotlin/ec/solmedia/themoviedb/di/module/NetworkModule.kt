package ec.solmedia.themoviedb.di.module

import dagger.Module
import dagger.Provides
import ec.solmedia.themoviedb.VimoApp
import ec.solmedia.themoviedb.api.VimoAPI
import ec.solmedia.themoviedb.api.VimoRestAPI
import ec.solmedia.themoviedb.api.VimoService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideVimoRestAPI(vimoService: VimoService): VimoAPI = VimoRestAPI(vimoService)

    @Provides
    @Singleton
    fun provideVimoService(retrofit: Retrofit) =
            retrofit.create(VimoService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(VimoApp.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}