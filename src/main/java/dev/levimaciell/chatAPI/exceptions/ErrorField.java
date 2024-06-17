package dev.levimaciell.chatAPI.exceptions;

public record ErrorField(
        String field,
        String error
) {
}
