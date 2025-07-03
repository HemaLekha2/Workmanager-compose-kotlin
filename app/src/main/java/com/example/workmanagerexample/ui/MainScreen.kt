package com.example.workmanagerexample.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.workmanagerexample.viewmodel.QuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(viewModel: QuoteViewModel = hiltViewModel()) {
    val quotes by viewModel.quotes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadQuotesFromDb()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Quotes") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.syncQuotes() }) {
                Text("Sync")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(8.dp)
        ) {
            items(quotes) { quote ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text(
                        text = quote.text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable fun MainScreenPreview() {
    MainScreen()
}
