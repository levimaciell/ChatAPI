package dev.levimaciell.chatAPI.message.exception;

public class MessageDeletionValidationException extends RuntimeException {
    public MessageDeletionValidationException(String msg) {
        super(msg);
    }
}
