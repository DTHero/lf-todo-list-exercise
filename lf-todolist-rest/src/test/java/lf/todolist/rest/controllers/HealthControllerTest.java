package lf.todolist.rest.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HealthControllerTest {

    @InjectMocks
    HealthController healthController;

    @Test
    void test_ping_should_be_OK() {
        // When
        healthController.ping();
        // Then
        Assertions.assertTrue(true);
    }
}
