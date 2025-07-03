package com.example.workmanagerexample.sync

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanagerexample.data.repository.QuoteRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class QuotesSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val repository: QuoteRepository
) : CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        return try {
            val quotes = repository.fetchFromApi()
            repository.saveToRoom(quotes)
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}