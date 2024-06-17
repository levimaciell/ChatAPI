package dev.levimaciell.chatAPI.user.exceptions;

public class UserDeletionException extends RuntimeException {
    public UserDeletionException(String msg) {
        super(msg);
    }
}
