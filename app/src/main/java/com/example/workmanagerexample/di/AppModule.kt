package com.example.workmanagerexample.di

import android.content.Context
import androidx.room.Room
import com.example.workmanagerexample.data.local.QuoteDatabase
import com.example.workmanagerexample.data.remote.QuoteApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuoteDatabase(app: Context): QuoteDatabase =
        Room.databaseBuilder(app, QuoteDatabase::class.java, "quote_db").build()

    @Provides
    fun provideQuoteDao(db: QuoteDatabase) = db.quoteDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://686511545b5d8d03397f8e5f.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideQuoteApi(retrofit: Retrofit): QuoteApi =
        retrofit.create(QuoteApi::class.java)
}
