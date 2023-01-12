package com.example.todolist.application.service

import ToDoRepository
import com.example.todolist.domain.model.ToDo
import com.example.todolist.presentation.form.ToDoInfo
import org.springframework.stereotype.Service

@Service
class ToDoService(
    private val toDoRepository: ToDoRepository
){
    fun getToDos(): List<ToDo> {
        val list = toDoRepository.findAll()
        return list.map { ToDo(it.id, it.title, it.done) }
    }
}