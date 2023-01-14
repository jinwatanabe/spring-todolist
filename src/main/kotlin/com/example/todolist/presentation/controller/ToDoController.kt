package com.example.todolist

import com.example.todolist.application.service.ToDoService
import com.example.todolist.domain.model.ToDo
import com.example.todolist.presentation.form.GetTodoListResponse
import com.example.todolist.presentation.form.RegisterToDoRequest
import com.example.todolist.presentation.form.ResponseForm
import com.example.todolist.presentation.form.ToDoInfo
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.management.BadAttributeValueExpException

private val logger = LoggerFactory.getLogger(ToDoController::class.java)

@RestController
class ToDoController(
    private val toDoService: ToDoService
) {

    @GetMapping("/hello")
    fun hello(@RequestParam("name") name: String): String {
        return "Hello, $name!"
    }
    @GetMapping("/todos")
    fun todos(): GetTodoListResponse {
        val toDoList = toDoService.getToDos().map { ToDoInfo(it) }
        return GetTodoListResponse(toDoList)
    }

    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.OK)
    fun createToDo(@RequestBody @Validated request: RegisterToDoRequest, bindingResult: BindingResult):ResponseEntity<ResponseForm> {
        if (bindingResult.hasErrors()) {
            return ResponseEntity(ResponseForm(400, "タイトルを入力してください"), HttpStatus.BAD_REQUEST)
        }
        toDoService.register(
            ToDo(
                0,
                request.title,
                false,
            )
        )

        return ResponseEntity(ResponseForm(200,"作成しました"), HttpStatus.OK)
    }
}