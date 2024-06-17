package dev.levimaciell.chatAPI.exceptions;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorDtoMultipleErrors(
        LocalDateTime timestamp,
        Integer status_code,
        String message,
        String path,
        List<ErrorField> errors
) {
}
