package dev.levimaciell.chatAPI.user.exceptions;

public class UserCreationValidationException extends RuntimeException {
    public UserCreationValidationException(String msg) {
        super(msg);
    }
}
