package ru.test.rest.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordTests {

    @Test
    public void HashPasswordCheck() {
        String salt = Password.generateSalt(512).get();
        String password = "password";
        String key = Password.hashPassword(password, salt).get();

        assertTrue(Password.verifyPassword(password, key, salt));
        assertFalse(Password.verifyPassword("", key, salt));
        assertFalse(Password.verifyPassword(password, "", key));
        assertFalse(Password.verifyPassword(password, key, Password.generateSalt(512).get()));
    }
}
