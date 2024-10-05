package com.example.mvvmdesignpattern

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val apiService = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

        val repository = TodoRepository(apiService)
        val viewModel = TodoViewModel(repository)

        setContent {
            TodoListScreen(viewModel.apply { loadTodos() })
        }
    }

    @Composable
    fun TodoListScreen(viewModel: TodoViewModel) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            LazyColumn {
                items(viewModel.todos) { todo ->
                    TodoItem(todo)
                }
            }
        }
    }

    @Composable
    fun TodoItem(todo: Todo) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            elevation = CardDefaults.cardElevation(8.dp) // Adjust the elevation value to make it noticeable
        ) {
            Column(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
               /* BasicText(
                    text = "User ID: ${todo.userId}",
                    style = MaterialTheme.typography.bodyMedium
                )*/
                BasicText(text = "ID: ${todo.id}", style = MaterialTheme.typography.bodyMedium)
                BasicText(
                    text = "Title: ${todo.title}",
                    style = MaterialTheme.typography.bodyMedium
                )
                BasicText(
                    text = "Completed: ${todo.completed}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(4.dp)) // Adding space between items
            }
        }
    }
}
