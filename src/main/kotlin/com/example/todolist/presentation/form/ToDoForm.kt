package com.example.todolist.presentation.form

import com.example.todolist.domain.model.ToDo
import javax.validation.Valid
import javax.validation.constraints.NotBlank

data class GetTodoListResponse(val todoList: List<ToDoInfo>)

data class ToDoInfo(
    val title: String,
    val done: Boolean
) {
    constructor(toDo: ToDo) : this(toDo.title, toDo.done)
}

data class RegisterToDoRequest(
    val id: Long,

    @field:Valid
    @field:NotBlank
    val title: String,

    val done: Boolean
)