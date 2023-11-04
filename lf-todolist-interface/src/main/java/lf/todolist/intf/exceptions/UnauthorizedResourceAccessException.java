package lf.todolist.intf.exceptions;

public class UnauthorizedResourceAccessException extends BusinessException {
    public UnauthorizedResourceAccessException(String message) {
        super(message);
    }
}
