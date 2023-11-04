package lf.todolist.application.service.identity.strategy;

import lf.todolist.intf.dto.User;

import java.util.Optional;

public interface UserStrategy {
    Optional<User> findFor(Object principal);
}
