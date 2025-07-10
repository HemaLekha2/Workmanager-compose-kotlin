package com.example.workmanagerexample.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workmanagerexample.data.local.Quote
import com.example.workmanagerexample.data.repository.QuoteRepository
import com.example.workmanagerexample.sync.NetworkObserver
import com.example.workmanagerexample.sync.triggerQuoteSync
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val repository: QuoteRepository,
    application: Application // Inject Application context
) : ViewModel() {

    private val _quotes = MutableStateFlow<List<Quote>>(emptyList())
    val quotes: StateFlow<List<Quote>> = _quotes

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val networkObserver = NetworkObserver(application.applicationContext)

    init {
        // Observe quotes from DB in real-time
        viewModelScope.launch {
            repository.loadFromRoom().collectLatest { quotes ->
                _quotes.value = quotes
            }
        }

        // Observe network connectivity
        viewModelScope.launch {
            networkObserver.observeNetworkConnectivity().collectLatest { isConnected ->
                if (isConnected) {
                    // Trigger WorkManager sync when network is available
                    // This will enqueue a one-time sync. Periodic sync will be handled by MyApp.
                    triggerQuoteSync(application.applicationContext)
                    _error.value = null // Clear error when network is back
                } else {
                    _error.value = "No internet connection. Displaying cached data."
                }
            }
        }
    }

    // This function is now redundant as quotes are observed via Flow in init block
    // However, keeping it for initial load if needed or for manual refresh of UI from DB
    fun loadQuotesFromDb() {
        viewModelScope.launch {
            repository.loadFromRoom()
                .catch { e ->
                    _error.value = e.localizedMessage
                    Log.e("QuoteViewModel", "Room load failed", e)
                }
                .collect {
                    _quotes.value = it
                    Log.d("QuoteViewModel", "Quotes from Room: ${it.size}")
                }
        }
    }

    // This function is now primarily handled by WorkManager for background sync.
    // If a direct foreground sync is still desired, it can be kept, but consider its implications.
    // For this solution, we will rely on WorkManager for sync.
    fun syncQuotes() {
        // This function can be removed or repurposed if WorkManager handles all syncs.
        // For now, we'll keep it but note its reduced importance.
        viewModelScope.launch {
            val fetchedQuotes = repository.fetchFromApi()
            if (fetchedQuotes != null) {
                repository.saveToRoom(fetchedQuotes)
                // UI will update automatically via Flow observation
                _error.value = null
            } else {
                _error.value = "Could not update quotes (API fetch failed)."
            }
        }
    }
}