package com.example.todolist_aulia.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.todolist_aulia.data.model.Task

class TaskViewModel : ViewModel() {
    private val _tasks = mutableStateListOf<Task>()
    val tasks: List<Task> get() = _tasks

    fun addTask(title: String) {
        val newTask = Task(id = _tasks.size + 1, title = title)
        _tasks.add(newTask)
    }

    fun toggleTask(id: Int) {
        val index = _tasks.indexOfFirst { it.id == id }
        if (index != -1) {
            _tasks[index] = _tasks[index].copy(isDone = !_tasks[index].isDone)
        }
    }
    fun deleteTask(id: Int) {
        _tasks.removeAll { it.id == id }
    }
    fun updateNote(id: Long?, newNote: String) {
        val index = _tasks.indexOfFirst { it.id.toLong() == id }
        if (index != -1) {
            val updatedTask = _tasks[index].copy(note = newNote)
            _tasks[index] = updatedTask
        }
    }

}