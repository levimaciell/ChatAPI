package dev.levimaciell.chatAPI.exceptions;

import java.time.LocalDateTime;

public record ErrorDto(
        LocalDateTime timestamp,
        Integer status_code,
        String error_name,
        String message,
        String path
) {
}
