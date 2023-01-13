package com.example.todolist.application.service

import ToDoRepository
import com.example.todolist.domain.model.ToDo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ToDoService(
    private val toDoRepository: ToDoRepository
){
    fun getToDos(): List<ToDo> {
        val list = toDoRepository.findAll()
        return list.map { ToDo(it.id, it.title, it.done) }
    }

    @Transactional
    fun register(toDo: ToDo) {
        toDoRepository.register(toDo)
    }
}