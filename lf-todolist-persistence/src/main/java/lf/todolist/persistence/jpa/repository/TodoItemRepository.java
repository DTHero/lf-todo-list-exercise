package lf.todolist.persistence.jpa.repository;

import lf.todolist.persistence.jpa.entity.TodoItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TodoItemRepository extends CrudRepository<TodoItemEntity, UUID> {
}
