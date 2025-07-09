package com.example.workmanagerexample.sync


import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun triggerQuoteSync(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
    val request = OneTimeWorkRequestBuilder<QuotesSyncWorker>()
        .setConstraints(constraints)
        .setBackoffCriteria(
            BackoffPolicy.EXPONENTIAL,
            10_000L, // Use hardcoded value for DEFAULT_BACKOFF_DELAY (10 seconds)
            TimeUnit.MILLISECONDS
        )
        .build()
    WorkManager.getInstance(context).enqueue(request)
}

fun setupPeriodicSync(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .setRequiresBatteryNotLow(true) // Optional: Add more constraints
        .build()

    val syncRequest = PeriodicWorkRequestBuilder<QuotesSyncWorker>(
        repeatInterval = 15, // Sync every 15 minutes
        TimeUnit.MINUTES
    )
        .setConstraints(constraints)
        .setBackoffCriteria(
            BackoffPolicy.EXPONENTIAL,
            10_000L, // Use hardcoded value for DEFAULT_BACKOFF_DELAY (10 seconds)
            TimeUnit.MILLISECONDS
        )
        .addTag("quote_sync_tag") // Add a unique tag for identification
        .build()

    WorkManager.getInstance(context).enqueueUniquePeriodicWork(
        "QuoteSyncWork", // Unique name for the work
        ExistingPeriodicWorkPolicy.KEEP, // Keep existing work if already enqueued
        syncRequest
    )
}

