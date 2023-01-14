import com.example.todolist.application.service.ToDoService
import com.example.todolist.domain.model.ToDo
import com.example.todolist.infrastructure.repository.ToDoModel
import com.example.todolist.presentation.form.ToDoInfo
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.Mockito.atLeastOnce
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest(classes = [ToDoService::class])
class ToDoServiceTest() {

    @MockBean
    private lateinit var toDoRepository: ToDoRepository

    @Autowired
    private lateinit var toDoService: ToDoService

    @Test
    fun `getToDos returns a list of todos`() {
        Mockito.`when`(toDoRepository.findAll()).thenReturn(listOf(ToDoModel(1,"test", false)))
        val toDoList = toDoService.getToDos()
        assert(toDoList[0].title == "test")
        assert(!toDoList[0].done)
    }

    @Test
    fun `registerToDo registers a todo`() {
        val toDo = ToDo(0, "test", false)
        Mockito.`when`(toDoRepository.register(toDo)).then{}

        toDoService.register(toDo)
        Mockito.verify(toDoRepository, atLeastOnce()).register(toDo)
    }
}