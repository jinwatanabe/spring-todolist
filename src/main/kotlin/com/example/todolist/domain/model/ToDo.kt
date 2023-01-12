package com.example.todolist.domain.model

data class ToDo(
    val id: Long,
    val title: String,
    val done: Boolean
)