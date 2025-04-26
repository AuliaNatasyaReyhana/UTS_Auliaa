package com.example.todolist_aulia.data.model

import java.text.SimpleDateFormat
import java.util.*

data class Task (
    val id: Int,
    val title: String,
    val date: String = getCurrentDate(),
    val isDone: Boolean = false,
    val note: String = ""
)

fun getCurrentDate(): String {
    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return sdf.format(Date())
}