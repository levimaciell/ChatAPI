package dev.levimaciell.chatAPI.user.exceptions;

public class UserUpdateValidationException extends RuntimeException {

    public UserUpdateValidationException(String msg) {
        super(msg);
    }

}
