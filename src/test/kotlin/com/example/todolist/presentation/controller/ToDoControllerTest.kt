import com.example.todolist.ToDoController
import com.example.todolist.TodolistApplication
import com.example.todolist.application.service.ToDoService
import com.example.todolist.domain.model.ToDo
import com.example.todolist.presentation.form.RegisterToDoRequest
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [TodolistApplication::class])
class ToDoControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    private lateinit var toDoService: ToDoService

    @Test
    fun `hello returns a greeting with the name`() {
        mockMvc.perform(get("/hello?name=World"))
            .andExpect(status().isOk)
            .andExpect(content().string("Hello, World!"))
    }

    @Test
    fun `todos returns a list of todos`() {
        Mockito.`when`(toDoService.getToDos()).thenReturn(listOf(ToDo(1,"test", false)))
        mockMvc.perform(get("/todos"))
            .andExpect(status().isOk)
            .andExpect(content().json("""
                {
                    "todoList": [
                        {
                            "title": "test",
                            "done": false
                        }
                    ]
                }
            """.trimIndent()))
    }

    @Test
    fun `createToDo returns status 200`() {
        Mockito.`when`(toDoService.register(ToDo(1,"test", false))).then{ }
        mockMvc.perform(
                post("/todo")
                    .contentType("application/json")
                    .content("""
                        {
                            "title": "test"
                        }
                    """.trimIndent())
            )
            .andExpect(status().isOk)
            .andExpect(content().json("""
                {
                    "message": "作成しました",
                    "status": 200
                }
            """.trimIndent()))
    }

    @Test
    fun `createToDo returns status 400 when title is empty`() {
        mockMvc.perform(
                post("/todo")
                    .contentType("application/json")
                    .content("""
                        {
                            "title": ""
                        }
                    """.trimIndent())
            )
            .andExpect(status().isBadRequest)
            .andExpect(content().json("""
                {
                    "message": "タイトルを入力してください",
                    "status": 400
                }
            """.trimIndent()))
    }

    @Test
    fun `updateToDo returns status 200`() {
        Mockito.`when`(toDoService.findById(1)).thenReturn(ToDo(1,"test", false))
        Mockito.`when`(toDoService.update(ToDo(1,"test", true))).then{ }
        mockMvc.perform(
                patch("/todo/1")
                    .contentType("application/json")
                    .content("""
                        {
                            "title": "test",
                            "done": true
                        }
                    """.trimIndent())
            )
            .andExpect(status().isOk)
            .andExpect(content().json("""
                {
                    "message": "更新しました",
                    "status": 200
                }
            """.trimIndent()))
    }

    @Test
    fun `updateToDo return status 404 when todo is not exist`() {
        Mockito.`when`(toDoService.findById(10000)).thenReturn(null)
        mockMvc.perform(
                patch("/todo/10000")
                    .contentType("application/json")
                    .content("""
                        {
                            "done": true
                        }
                    """.trimIndent())
            )
            .andExpect(status().isNotFound)
            .andExpect(content().json("""
                {
                    "message": "存在しないIDです",
                    "status": 404
                }
            """.trimIndent()))
    }

    @Test
    fun `deleteToDo return status 200`() {
        Mockito.`when`(toDoService.findById(1)).thenReturn(ToDo(1, "test", true))
        Mockito.`when`(toDoService.delete(1)).then{ }
        mockMvc.perform(delete("/todo/1"))
            .andExpect(status().isOk)
            .andExpect(content().json("""
                {
                    "message": "削除しました",
                    "status": 200
                }
            """.trimIndent()))
    }

    @Test
    fun `deleteToDo return status 404 when todo is not exist`() {
        Mockito.`when`(toDoService.findById(10000)).thenReturn(null)
        mockMvc.perform(delete("/todo/10000"))
            .andExpect(status().isNotFound)
            .andExpect(content().json("""
                {
                    "message": "存在しないIDです",
                    "status": 404
                }
            """.trimIndent()))
    }
}