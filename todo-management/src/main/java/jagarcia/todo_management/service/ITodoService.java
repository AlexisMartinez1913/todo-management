package jagarcia.todo_management.service;

import jagarcia.todo_management.dto.TodoDto;

import java.util.List;

public interface ITodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(Long id);
    List<TodoDto> getAllTodos();
    TodoDto updateTodo(TodoDto todoDto, Long id);
    void deleteTodo(Long id);

    TodoDto completedTodo(Long id);

}
