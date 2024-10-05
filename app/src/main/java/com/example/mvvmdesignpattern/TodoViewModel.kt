package com.example.mvvmdesignpattern

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    var todos by mutableStateOf<List<Todo>>(emptyList())
        private set

    fun loadTodos() {
        viewModelScope.launch {
            todos = repository.fetchTodos()
        }
    }

   /* fun fetchTodos() {
        viewModelScope.launch {
            todos = repository.fetchTodos()
        }
        TODO("Not yet implemented")
    }*/
}
