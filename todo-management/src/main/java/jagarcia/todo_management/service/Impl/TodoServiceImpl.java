package jagarcia.todo_management.service.Impl;

import jagarcia.todo_management.dto.TodoDto;
import jagarcia.todo_management.entity.Todo;
import jagarcia.todo_management.repository.ITodoRepository;
import jagarcia.todo_management.service.ITodoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements ITodoService {

    private ITodoRepository iTodoRepository;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //Convert TodoDto into Todo Jpa Entity
        Todo todo = new Todo();
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        //Todo Jpa Entity
        Todo savedTodo = iTodoRepository.save(todo);

        //Convert saved todo jpa entity into TodoDto object
        TodoDto savedTodoDto = new TodoDto();
        savedTodoDto.setId(savedTodo.getId());
        savedTodoDto.setTitle(savedTodo.getTitle());
        savedTodoDto.setDescription(savedTodo.getDescription());
        savedTodoDto.setCompleted(savedTodo.isCompleted());

        return savedTodoDto;
    }
}
