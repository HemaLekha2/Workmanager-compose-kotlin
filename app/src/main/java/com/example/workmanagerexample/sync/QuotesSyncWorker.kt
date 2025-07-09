package com.example.workmanagerexample.sync


import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanagerexample.data.repository.QuoteRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class QuotesSyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: QuoteRepository
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        Log.d("QuotesSyncWorker", "Worker manually triggered")

        return try {
            val quotes = repository.fetchFromApi()
            if (quotes != null) {
                repository.saveToRoom(quotes)
                Log.d("QuotesSyncWorker", "Saved ${quotes.size} quotes to Room")
                Result.success()
            } else {
                Result.retry() // Try again later
            }
        } catch (e: Exception) {
            Log.e("QuotesSyncWorker", "Failed syncing", e)
            Result.retry()
        }
    }
}