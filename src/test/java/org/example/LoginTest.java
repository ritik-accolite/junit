package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginTest {
    private static Authentication system;

    public LoginTest() {
    }

    @BeforeAll
    public static void setUp() {
        system = new Authentication();
    }

    @Test
    public void testLogin_Valid() {
        String email = "user@example.com";
        String password = "password123";
        Assertions.assertTrue(system.login(email, password));
        Assertions.assertEquals("User logged in successfully. Redirecting to the dashboard...", system.getLastMessage());
    }

    @Test
    public void testLogin_InvalidEmail() {
        String email = "invalid@example.com";
        String password = "password123";
        Assertions.assertFalse(system.login(email, password));
        Assertions.assertEquals("Invalid email address or password.", system.getLastMessage());
    }

    @Test
    public void testLogin_InvalidPassword() {
        String email = "user@example.com";
        String wrongPassword = "wrongPassword";
        Assertions.assertFalse(system.login(email, wrongPassword));
        Assertions.assertEquals("Invalid email address or password.", system.getLastMessage());
    }
}
