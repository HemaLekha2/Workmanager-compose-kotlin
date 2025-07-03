package com.example.workmanagerexample.data.repository


import com.example.workmanagerexample.data.local.Quote
import com.example.workmanagerexample.data.local.QuoteDao
import com.example.workmanagerexample.data.remote.QuoteApi
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteApi,
    private val dao: QuoteDao
) {
    suspend fun fetchFromApi(): List<Quote> = api.getQuotes()
    suspend fun saveToRoom(quotes: List<Quote>) = dao.insertAll(quotes)
    suspend fun loadFromRoom(): List<Quote> = dao.getAllQuotes()
}