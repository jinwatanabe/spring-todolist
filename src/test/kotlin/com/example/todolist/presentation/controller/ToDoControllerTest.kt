import com.example.todolist.ToDoController
import com.example.todolist.TodolistApplication
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [TodolistApplication::class])
class ToDoControllerTest(@Autowired val mockMvc: MockMvc) {
    @Test
    fun `hello returns a greeting with the name`() {
        mockMvc.perform(get("/hello?name=World"))
            .andExpect(status().isOk)
            .andExpect(content().string("Hello, World!"))
    }

//    @Test
//    fun `todos returns a list of todos`() {
//        mockMvc.perform(get("/todos"))
//            .andExpect(status().isOk)
//            .andExpect(content().json("""
//                {
//                    "toDoList": [
//                        {
//                            "title": "title1",
//                            "done": true
//                        },
//                        {
//                            "title": "title2",
//                            "done": false
//                        }
//                    ]
//                }
//            """.trimIndent()))
//    }
}