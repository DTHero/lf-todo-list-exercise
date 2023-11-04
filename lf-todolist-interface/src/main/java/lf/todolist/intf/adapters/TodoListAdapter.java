package lf.todolist.intf.adapters;

import lf.todolist.intf.dto.TodoList;

import java.util.List;
import java.util.UUID;

public interface TodoListAdapter {

    List<TodoList> findAllByUserUuid(UUID userUuid);

    void createTodoList(UUID userUuid, List<TodoList> todoLists);
}
