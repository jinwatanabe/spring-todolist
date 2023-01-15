package com.example.todolist

import com.example.todolist.application.service.ToDoService
import com.example.todolist.domain.model.ToDo
import com.example.todolist.presentation.form.*
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

    @PatchMapping("/todo/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateToDo(
        @PathVariable("id") id: Long,
        @RequestBody request: UpdateToDoRequest,
    ): ResponseEntity<ResponseForm> {
        val toDo = toDoService.findById(id) ?: return ResponseEntity(ResponseForm(404, "存在しないIDです"), HttpStatus.NOT_FOUND)
        toDoService.update(
            ToDo(
                toDo.id,
                request.title ?: toDo.title,
                request.done ?: toDo.done
            )
        )

        return ResponseEntity(ResponseForm(200,"更新しました"), HttpStatus.OK)
    }

    @DeleteMapping("/todo/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteToDo(
        @PathVariable("id") id: Long,
    ): ResponseEntity<ResponseForm> {
        val toDo = toDoService.findById(id) ?: return ResponseEntity(ResponseForm(404, "存在しないIDです"), HttpStatus.NOT_FOUND)
        toDoService.delete(id)

        return ResponseEntity(ResponseForm(200,"削除しました"), HttpStatus.OK)
    }
}