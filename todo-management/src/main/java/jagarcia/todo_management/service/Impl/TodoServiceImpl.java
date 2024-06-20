package jagarcia.todo_management.service.Impl;

import jagarcia.todo_management.dto.TodoDto;
import jagarcia.todo_management.entity.Todo;
import jagarcia.todo_management.exception.ResourceNotFoundException;
import jagarcia.todo_management.repository.ITodoRepository;
import jagarcia.todo_management.service.ITodoService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = iTodoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        return modelMapper.map(todo, TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {

        List<Todo> todos = iTodoRepository.findAll();

        return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).
                collect(Collectors.toList());

    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {

        //Todo todo = iTodoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: "+ id));
        Todo todo = iTodoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());

        Todo updateTodo = iTodoRepository.save(todo);

        return modelMapper.map(updateTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        Todo todo = iTodoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        iTodoRepository.delete(todo);
    }

    @Override
    public TodoDto completeTodo(Long id) {

        Todo todo = iTodoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = iTodoRepository.save(todo);

        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = iTodoRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: " + id));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = iTodoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }
}
