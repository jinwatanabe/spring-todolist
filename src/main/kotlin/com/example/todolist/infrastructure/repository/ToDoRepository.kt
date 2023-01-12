package com.example.todolist.infrastructure.repository

import ToDoRepository
import com.example.todolist.domain.model.ToDo
import com.example.todolist.presentation.form.ToDoInfo
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class ToDoRepositoy: ToDoRepository {
    override fun findAll(): List<ToDoModel> {
        createSessionFactory()
        return transaction {
            ToDoEntity.all().map { ToDoModel(it) }
        }
    }
}

fun createSessionFactory() {
    Database.connect(
        url = "jdbc:mysql://127.0.0.1:3306/todo",
        driver = "com.mysql.jdbc.Driver",
        user = "root",
        password = "mysql"
    )
}

object ToDoTable : LongIdTable("todo") {
    val title = varchar("title", 255)
    val done = bool("done")
}

class ToDoEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ToDoEntity>(ToDoTable)

    var title by ToDoTable.title
    var done by ToDoTable.done
}

data class ToDoModel(val id: Long, val title: String, val done: Boolean) {
    constructor(entity: ToDoEntity) : this(entity.id.value, entity.title, entity.done)
}