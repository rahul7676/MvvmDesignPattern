package com.example.mvvmdesignpattern

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@OptIn(ExperimentalCoroutinesApi::class)
class TodoViewModelTest {

    private lateinit var viewModel: TodoViewModel
    private lateinit var repository: TodoRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        repository = Mockito.mock(TodoRepository::class.java)
        viewModel = TodoViewModel(repository)

        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun testFetchTodos() = runTest {
        val mockTodos = listOf(
            Todo(
                userId = 1,
                id = 1,
                title = "Test Todo",
                completed = false
            )
        )
      //  Mockito.`when`(repository.getTodos()).thenReturn(mockTodos)
        Mockito.`when`(repository.fetchTodos()).thenReturn(mockTodos)
        viewModel.loadTodos()
        testDispatcher.scheduler.advanceUntilIdle()

        assertEquals(mockTodos, viewModel.todos.first())
    }
}
