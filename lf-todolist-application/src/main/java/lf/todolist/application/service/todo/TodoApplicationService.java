package lf.todolist.application.service.todo;

import lf.todolist.application.service.identity.UserApplicationService;
import lf.todolist.intf.adapters.TodoListAdapter;
import lf.todolist.intf.dto.TodoList;
import lf.todolist.intf.dto.User;
import lf.todolist.intf.enums.RoleEnum;
import lf.todolist.intf.exceptions.UnauthorizedResourceAccessException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class TodoApplicationService {

    UserApplicationService userApplicationService;
    TodoListAdapter todoListAdapter;

    public void createNewTodoList(Authentication authentication, List<TodoList> todoLists) {
        User user = userApplicationService.getProfileFor(authentication);
        if (user.getRole() == RoleEnum.GUEST) {
            throw new UnauthorizedResourceAccessException("Unauthorized administrator operation!");
        }
        todoListAdapter.createTodoList(user.getUuid(), todoLists);
    }

    public List<TodoList> adminGetTodoListOfUser(Authentication authentication, UUID userUuid) {
        if (!userApplicationService.isAdminUser(authentication)) {
            throw new UnauthorizedResourceAccessException("Unauthorized administrator access!");
        }
        return todoListAdapter.findAllByUserUuid(userUuid);
    }

    public List<TodoList> getTodoList(Authentication authentication) {
        User user = userApplicationService.getProfileFor(authentication);
        return todoListAdapter.findAllByUserUuid(user.getUuid());
    }
}
