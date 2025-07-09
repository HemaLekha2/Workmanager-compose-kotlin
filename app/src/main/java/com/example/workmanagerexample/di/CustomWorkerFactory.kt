package com.example.workmanagerexample.di

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.example.workmanagerexample.sync.QuotesSyncWorker
import com.example.workmanagerexample.data.repository.QuoteRepository
import javax.inject.Inject

class CustomWorkerFactory @Inject constructor(
    private val repository: QuoteRepository
) : WorkerFactory() {
    override fun createWorker(
        context: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? {
        return when (workerClassName) {
            QuotesSyncWorker::class.qualifiedName ->
                QuotesSyncWorker(context, workerParameters, repository)
            else -> null
        }
    }
}
