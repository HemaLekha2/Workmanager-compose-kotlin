package com.example.workmanagerexample.data.remote

import com.example.workmanagerexample.data.local.Quote
import retrofit2.http.GET


interface QuoteApi {
    @GET("api/v1/quotes") // Your MockAPI endpoint
    suspend fun getQuotes(): List<Quote>
}