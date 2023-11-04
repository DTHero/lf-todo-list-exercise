package lf.todolist.rest.controllers.identity;

import lf.todolist.application.service.identity.AuthApplicationService;
import lf.todolist.intf.dto.LoginOut;
import lf.todolist.rest.controllers.AuthController;
import lf.todolist.intf.requests.LoginEmailRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    AuthApplicationService authApplicationService;
    @InjectMocks
    AuthController authController;

    @Test
    void login_should_be_OK() {
        // Given
        LoginEmailRequest dto = mock(LoginEmailRequest.class);
        LoginOut out = mock(LoginOut.class);
        String email = "thiennguyen.kt@gmail.com";
        String password = "12345678x@X";

        doReturn(email).when(dto).getEmail();
        doReturn(password).when(dto).getPassword();
        doReturn(out).when(authApplicationService).loginWithEmail(email, password);

        // When
        LoginOut actual = authController.loginWithEmail(dto);

        // Then
        verify(dto).getEmail();
        verify(dto).getPassword();
        verify(authApplicationService).loginWithEmail(email, password);
        assertThat(actual).isSameAs(out);
    }
}
