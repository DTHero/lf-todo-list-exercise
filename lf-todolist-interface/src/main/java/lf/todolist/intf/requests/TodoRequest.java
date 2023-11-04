package lf.todolist.intf.requests;

import lf.todolist.intf.dto.TodoList;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TodoRequest {
    List<TodoList> todoLists;
}
