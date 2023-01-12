import com.example.todolist.domain.model.ToDo
import com.example.todolist.infrastructure.repository.ToDoModel

interface ToDoRepository {
    fun findAll(): List<ToDoModel>
}
