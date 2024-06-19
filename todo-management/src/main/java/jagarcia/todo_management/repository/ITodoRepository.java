package jagarcia.todo_management.repository;

import jagarcia.todo_management.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITodoRepository extends JpaRepository<Todo, Long> {
}
