package lf.todolist.persistence.jpa.repository;

import lf.todolist.persistence.jpa.entity.TodoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TodoRepository extends CrudRepository<TodoEntity, UUID> {
    List<TodoEntity> findAllByUserUuid(UUID userUuid);
}
