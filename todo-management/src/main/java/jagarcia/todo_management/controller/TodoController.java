package jagarcia.todo_management.controller;

import jagarcia.todo_management.dto.TodoDto;
import jagarcia.todo_management.service.ITodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
    private ITodoService iTodoService;

    //Build addTodo Rest Api
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

        TodoDto savedTodo = iTodoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //build Get Todo REST API
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id) {
        TodoDto todoDto = iTodoService.getTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }
    //build Get All Todos
    @GetMapping
    public ResponseEntity<List<TodoDto>>getAllTodos() {

        List<TodoDto> todos = iTodoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);

    }

    //build update Todo Rest Api
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
        TodoDto updateTodo = iTodoService.updateTodo(todoDto, todoId);

        return new ResponseEntity<>(updateTodo, HttpStatus.OK);

    }

}
