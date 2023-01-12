package com.example.todolist

import com.example.todolist.application.service.ToDoService
import com.example.todolist.presentation.form.GetTodoListResponse
import com.example.todolist.presentation.form.ToDoInfo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ToDoController(
    private val toDoService: ToDoService
) {
    @GetMapping("/hello")
    fun hello(@RequestParam("name") name: String): String {
        return "Hello, $name!"
    }

    @GetMapping("/todos")
    fun todos(): GetTodoListResponse {
        val toDoList = toDoService.getToDos().map { ToDoInfo(it) }
        return GetTodoListResponse(toDoList)
    }
}