package com.example.mvvmdesignpattern

class TodoRepository(private val apiService: ApiService) {
    suspend fun fetchTodos(): List<Todo> {
        return apiService.getTodos()
    }
   /* suspend fun getTodos(): Any {
          return apiService.getTodos()
      }*/
}
