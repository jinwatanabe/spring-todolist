import com.example.todolist.domain.model.ToDo
import com.example.todolist.infrastructure.repository.ToDoModel

interface ToDoRepository {
    fun findAll(): List<ToDoModel>
    fun register(toDo: ToDo)
    fun update(toDo: ToDo)

    fun findById(id: Long): ToDoModel?
}
