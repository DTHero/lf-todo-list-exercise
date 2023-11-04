package lf.todolist.security.exception;

import org.springframework.security.core.AuthenticationException;

public class MobileNotFoundException extends AuthenticationException {
    public MobileNotFoundException(String msg) {
        super(msg);
    }
}
