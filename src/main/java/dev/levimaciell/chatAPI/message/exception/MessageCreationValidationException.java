package dev.levimaciell.chatAPI.message.exception;

public class MessageCreationValidationException extends RuntimeException {
    public MessageCreationValidationException(String msg) {
        super(msg);
    }
}
