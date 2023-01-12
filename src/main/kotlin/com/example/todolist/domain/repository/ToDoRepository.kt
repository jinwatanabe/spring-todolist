import com.example.todolist.domain.model.ToDo

interface ToDoRepository {
    fun findAll(): List<ToDo>
}
