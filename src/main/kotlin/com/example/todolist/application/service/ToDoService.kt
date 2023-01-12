package com.example.todolist.application.service

import ToDoRepository
import com.example.todolist.domain.model.ToDo
import org.springframework.stereotype.Service

@Service
class ToDoService(
    private val toDoRepository: ToDoRepository
){
    fun getToDos(): List<ToDo> {
        return toDoRepository.findAll()
    }
}