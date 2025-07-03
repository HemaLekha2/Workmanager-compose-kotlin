package com.example.workmanagerexample.data.remote

import com.example.workmanagerexample.data.local.Quote
import retrofit2.http.GET


interface QuoteApi {
    @GET("Quotes/quotes")// Your MockAPI endpoint
    suspend fun getQuotes(): List<Quote>
}