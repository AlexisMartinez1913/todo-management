package jagarcia.todo_management.controller;

import jagarcia.todo_management.dto.TodoDto;
import jagarcia.todo_management.service.ITodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
    private ITodoService iTodoService;

    //Build addTodo Rest Api
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {

        TodoDto savedTodo = iTodoService.addTodo(todoDto);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    //build Get Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long id) {
        TodoDto todoDto = iTodoService.getTodo(id);
        return new ResponseEntity<>(todoDto, HttpStatus.OK);
    }
    //build Get All Todos

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>>getAllTodos() {

        List<TodoDto> todos = iTodoService.getAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);

    }

    //build update Todo Rest Api
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
        TodoDto updateTodo = iTodoService.updateTodo(todoDto, todoId);

        return new ResponseEntity<>(updateTodo, HttpStatus.OK);

    }
    //Build delete Todo
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable("id") Long id) {
        iTodoService.deleteTodo(id);
        return new ResponseEntity<>("Todo deleted succesfully", HttpStatus.OK);
    }

    //build complete Todo REST API

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long todoId) {
        TodoDto updatedTodo = iTodoService.completeTodo(todoId);
        return ResponseEntity.ok(updatedTodo);
    }

    //build incomplete Todo Rest Api
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("{id}/in-complete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id) {
        TodoDto updatedTodo = iTodoService.inCompleteTodo(id);
        return ResponseEntity.ok(updatedTodo);
    }

}
