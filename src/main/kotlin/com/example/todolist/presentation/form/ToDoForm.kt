package com.example.todolist.presentation.form

import com.example.todolist.domain.model.ToDo

data class GetTodoListResponse(val todoList: List<ToDoInfo>)

data class ToDoInfo(
    val id: Long,
    val title: String,
    val done: Boolean
) {
    constructor(toDo: ToDo) : this(toDo.id, toDo.title, toDo.done)
}