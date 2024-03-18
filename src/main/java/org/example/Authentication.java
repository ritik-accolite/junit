package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Authentication {
    private Map<String, User> users = new HashMap();
    private EmailService emailService;
    private String lastMessage;

    public Authentication() {
        this.users.put("user@example.com", new User("user@example.com", "password123"));
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public String getLastMessage() {
        return this.lastMessage;
    }

    public void forgetPassword(String email) {
        if (this.users.containsKey(email)) {
            String newPassword = UUID.randomUUID().toString();

            (this.users.get(email)).setPassword(newPassword);

            this.emailService.sendPasswordResetEmail(email);
            this.lastMessage = "Password reset email sent to " + email + " with new password.";
            System.out.println(this.lastMessage);
        } else {
            this.lastMessage = "Email address not found.";
            System.out.println(this.lastMessage);
        }

    }

    public boolean login(String email, String password) {
        if (this.users.containsKey(email) && ((User)this.users.get(email)).getPassword().equals(password)) {
            this.lastMessage = "User logged in successfully. Redirecting to the dashboard...";
            System.out.println(this.lastMessage);
            return true;
        } else {
            this.lastMessage = "Invalid email address or password.";
            System.out.println(this.lastMessage);
            return false;
        }
    }
}
