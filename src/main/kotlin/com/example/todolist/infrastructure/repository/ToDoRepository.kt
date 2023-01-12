package com.example.todolist.infrastructure.repository

import ToDoRepository
import com.example.todolist.domain.model.ToDo
import org.springframework.stereotype.Repository

@Repository
class ToDoRepositoy: ToDoRepository {
    override fun findAll(): List<ToDo> {
        return listOf(
            ToDo(1, "title1", true),
            ToDo(2, "title2", false),
            ToDo(3, "title3", true)
        )
    }
}