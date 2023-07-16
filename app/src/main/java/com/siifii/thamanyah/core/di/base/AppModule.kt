package com.siifii.thamanyah.core.di.base

import android.app.Application
import android.content.Context
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import com.google.gson.Gson
import com.siifii.thamanyah.BuildConfig
import com.siifii.thamanyah.core.util.FlipperNetworkInterceptor
import com.siifii.thamanyah.data.datasource.resources.preferences.PreferencesDataSource
import com.siifii.thamanyah.data.datasource.resources.preferences.SharedPreferencesDataSource
import com.siifii.thamanyah.data.datasource.rest.PublicApiFunctions
import com.siifii.thamanyah.data.datasource.rest.interceptors.AuthInterceptor
import com.siifii.thamanyah.data.datasource.rest.interceptors.PublicInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    fun providePreferenceDataSource(context: Context, gson: Gson): PreferencesDataSource {
        return SharedPreferencesDataSource(context, gson)
    }

    @Provides
    fun provideBaseUrl() = BuildConfig.APP_URL

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

//    @Provides
//    @Singleton
//    fun provideThamanyahMediaPlayer(): com.siifii.thamanyah.presentation.mediaplayer.ThamanyahMediaPlayer {
//        return com.siifii.thamanyah.presentation.mediaplayer.ThamanyahMediaPlayer()
//    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return loggingInterceptor
    }


    @Provides
    @Singleton
    fun provideAuthInterceptor(preferencesDataSource: PreferencesDataSource): AuthInterceptor {
        return AuthInterceptor(preferencesDataSource)
    }

    @Provides
    @Singleton
    fun providePublicInterceptor(context: Context): PublicInterceptor {
        return PublicInterceptor(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
        publicInterceptor: PublicInterceptor,
    ) = if (BuildConfig.DEBUG) {

        val httpClient = OkHttpClient.Builder()

        httpClient.connectTimeout(60, TimeUnit.SECONDS)
            .addNetworkInterceptor(FlipperOkhttpInterceptor(FlipperNetworkInterceptor.getFlipperNetworkPlugin()))
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        httpClient.addInterceptor(loggingInterceptor)
        httpClient.addInterceptor(authInterceptor)
        httpClient.addInterceptor(publicInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(publicInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): PublicApiFunctions =
        retrofit.create(PublicApiFunctions::class.java)

}