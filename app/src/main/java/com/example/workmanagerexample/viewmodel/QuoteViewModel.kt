package com.example.workmanagerexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.workmanagerexample.data.local.Quote
import com.example.workmanagerexample.data.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val repository: QuoteRepository
) : ViewModel() {

    private val _quotes = MutableStateFlow<List<Quote>>(emptyList())
    val quotes: StateFlow<List<Quote>> = _quotes

    fun loadQuotesFromDb() {
        viewModelScope.launch {
            _quotes.value = repository.loadFromRoom()
        }
    }

    fun syncQuotes() {
        viewModelScope.launch {
            val quotes = repository.fetchFromApi()
            repository.saveToRoom(quotes)
            _quotes.value = repository.loadFromRoom()
        }
    }
}