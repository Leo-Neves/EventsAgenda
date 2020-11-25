package com.santana.eventsagenda.di

import com.santana.eventsagenda.data.EventsApi
import com.santana.eventsagenda.data.repository.EventsRepositoryImpl
import com.santana.eventsagenda.domain.repository.EventsRepository
import com.santana.eventsagenda.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object CodewarsModule {

    @Singleton
    @Provides
    @Named("url")
    fun providesBaseURL() = "http://5f5a8f24d44d640016169133.mockapi.io/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, @Named("url") baseURL: String) =
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): EventsApi = retrofit.create(EventsApi::class.java)

    @Provides
    @Singleton
    fun providesCodewarsRepository(
        api: EventsApi
    ): EventsRepository =
        EventsRepositoryImpl(api)

    @Provides
    fun providesScheduler() = Schedulers.io()

    @Provides
    @Singleton
    fun providesFetchEventsUseCase(repository: EventsRepository) =
        FetchEventsUseCase(repository)

    @Provides
    @Singleton
    fun providesFetchEventDetailsUseCase(repository: EventsRepository) =
        FetchEventDetailsUseCase(repository)

    @Provides
    @Singleton
    fun providesCheckinUseCase(repository: EventsRepository) =
        CheckinUseCase(repository)
}