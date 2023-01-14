package com.example.todolist.presentation.form

import com.example.todolist.domain.model.ToDo
import org.hibernate.validator.constraints.NotBlank

data class GetTodoListResponse(val todoList: List<ToDoInfo>)

data class ToDoInfo(
    val title: String,
    val done: Boolean
) {
    constructor(toDo: ToDo) : this(toDo.title, toDo.done)
}

data class RegisterToDoRequest(
    @field:NotBlank
    val title: String,
    val done: Boolean = false
)

data class UpdateToDoRequest(
    val title: String ?= null,
    val done: Boolean ?= null
)
