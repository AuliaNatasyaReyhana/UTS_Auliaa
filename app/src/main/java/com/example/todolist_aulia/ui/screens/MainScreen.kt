package com.example.todolist_aulia.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todolist_aulia.ui.components.TaskItem
import com.example.todolist_aulia.viewmodel.TaskViewModel
import com.example.todolist_aulia.ui.theme.LobsterFontFamily
import androidx.navigation.NavController

@Composable
fun MainScreen(
    viewModel: TaskViewModel,
    navController: NavController
) {
    var newTask by remember { mutableStateOf("") }
    var showNoteDialog by remember { mutableStateOf(false) }
    var noteInput by remember { mutableStateOf("") }
    var editingTaskId by remember { mutableStateOf<Long?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Catatan Anda",
                        fontFamily = LobsterFontFamily,
                        fontSize = 20.sp
                    )
                },
                backgroundColor = Color.White,
                contentColor = Color.Black,
                navigationIcon = {
                    IconButton(onClick = {
                        // Navigasi kembali ke LoginScreen
                        navController.navigate("login") {
                            popUpTo("main") { inclusive = true }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Kembali"
                        )
                    }
                },
                elevation = 4.dp
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFFFBE3BB),
                                Color(0xFFF8B694),
                                Color(0xFFF68284)
                            )
                        )
                    )
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                TextField(
                    value = newTask,
                    onValueChange = { newTask = it },
                    placeholder = { Text("Masukkan judul...", color = Color.LightGray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        backgroundColor = Color.White,
                        placeholderColor = Color.Gray,
                        cursorColor = Color.Black,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Tombol Tambah
                Button(
                    onClick = {
                        if (newTask.isNotBlank()) {
                            viewModel.addTask(newTask)
                            newTask = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Tambah")
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Daftar Task
                viewModel.tasks.forEach { task ->
                    TaskItem(
                        task = task,
                        onToggle = { viewModel.toggleTask(task.id) },
                        onDelete = { viewModel.deleteTask(task.id) },
                        onEditNote = {
                            editingTaskId = task.id.toLong()
                            noteInput = task.note
                            showNoteDialog = true
                        }
                    )
                }

                // Dialog untuk edit catatan
                // Dialog untuk edit catatan
                if (showNoteDialog && editingTaskId != null) {
                    AlertDialog(
                        onDismissRequest = { showNoteDialog = false },
                        title = { Text("Tambahkan Catatan") },
                        text = {
                            TextField(
                                value = noteInput,
                                onValueChange = { noteInput = it },
                                label = { Text("Catatan") },
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White
                                )
                            )
                        },
                        confirmButton = {
                            Button(
                                onClick = {
                                    viewModel.updateNote(editingTaskId, noteInput)
                                    showNoteDialog = false
                                },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.White,  // latar putih
                                    contentColor = Color.Black      // teks hitam
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Simpan")
                            }
                        },
                        dismissButton = {
                            Button(   // gunakan Button, bukan OutlinedButton
                                onClick = { showNoteDialog = false },
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = Color.White,  // latar putih
                                    contentColor = Color.Black      // teks hitam
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Batal")
                            }
                        }
                    )
                }

            }
        }
    )
}
