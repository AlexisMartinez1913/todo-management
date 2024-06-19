package jagarcia.todo_management.service.Impl;

import jagarcia.todo_management.dto.TodoDto;
import jagarcia.todo_management.entity.Todo;
import jagarcia.todo_management.repository.ITodoRepository;
import jagarcia.todo_management.service.ITodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements ITodoService {

    private ITodoRepository iTodoRepository;

    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto) {

        //Convert TodoDto into Todo Jpa Entity using modelMapper
        Todo todo = modelMapper.map(todoDto, Todo.class);
        //Todo Jpa Entity
        Todo savedTodo = iTodoRepository.save(todo);
        //Convert saved todo jpa entity into TodoDto object
        //using modelMapper
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);

        return savedTodoDto;
    }
}
