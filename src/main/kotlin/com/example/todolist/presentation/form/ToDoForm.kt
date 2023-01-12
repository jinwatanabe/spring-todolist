package com.example.todolist.presentation.form

import com.example.todolist.domain.model.ToDo

data class GetTodoListResponse(val todoList: List<ToDoInfo>)

data class ToDoInfo(
    val title: String,
    val done: Boolean
) {
    constructor(toDo: ToDo) : this(toDo.title, toDo.done)
}