package com.example.workmanagerexample.data.repository


import android.util.Log
import com.example.workmanagerexample.data.local.Quote
import com.example.workmanagerexample.data.local.QuoteDao
import com.example.workmanagerexample.data.remote.QuoteApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteApi,
    private val dao: QuoteDao
) {
    suspend fun fetchFromApi(): List<Quote>? {
        return try {
            api.getQuotes()
        } catch (e: Exception) {
            e.printStackTrace() // Log the error for debugging
            null // Could also return emptyList()
        }
    }
    suspend fun saveToRoom(quotes: List<Quote>) {
        Log.d("QuoteRepository", "Inserting ${quotes.size} quotes to DB")
        dao.insertAll(quotes)
    }
    fun loadFromRoom(): Flow<List<Quote>> = dao.getAllQuotes()
}