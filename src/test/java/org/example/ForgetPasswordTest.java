package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ForgetPasswordTest {
    private static Authentication system;
    private static EmailServiceMock emailService;

    public ForgetPasswordTest() {
    }

    @BeforeAll
    public static void setUp() {
        system = new Authentication();
        emailService = (EmailServiceMock)Mockito.mock(EmailServiceMock.class);
        system.setEmailService(emailService);
    }

    @Test
    public void testForgetPassword_ValidEmail() {
        String email = "user@example.com";
        system.forgetPassword(email);
        ((EmailServiceMock)Mockito.verify(emailService, Mockito.times(1))).sendPasswordResetEmail((String)Mockito.eq(email));
        Assertions.assertEquals("Password reset email sent to " + email + " with new password.", system.getLastMessage());
    }

    @Test
    public void testForgetPassword_InValidEmail() {
        String email = "user1@example.com";
        system.forgetPassword(email);
        Assertions.assertEquals("Email address not found.", system.getLastMessage());
    }
}
