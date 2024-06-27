package jagarcia.todo_management.repository;

import jagarcia.todo_management.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
