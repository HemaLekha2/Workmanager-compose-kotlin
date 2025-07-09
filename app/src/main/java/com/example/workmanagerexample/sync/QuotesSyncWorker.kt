package com.example.workmanagerexample.sync


import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanagerexample.data.repository.QuoteRepository

class QuotesSyncWorker(
    context: Context,
    workerParams: WorkerParameters,
    private val repository: QuoteRepository
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        Log.d("QuotesSyncWorker", "Worker manually triggered")

        return try {
            val quotes = repository.fetchFromApi()
            if (!quotes.isNullOrEmpty()) {
                repository.saveToRoom(quotes)
                Log.d("QuotesSyncWorker", "Saved ${quotes.size} quotes to Room")
                Result.success()
            } else {
                Log.d("QuotesSyncWorker", "Empty result from API, retrying...")
                Result.retry()
            }
        } catch (e: Exception) {
            Log.e("QuotesSyncWorker", "Failed syncing", e)
            Result.retry()
        }
    }
}