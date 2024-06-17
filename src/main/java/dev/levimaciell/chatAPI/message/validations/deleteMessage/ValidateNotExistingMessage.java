package dev.levimaciell.chatAPI.message.validations.deleteMessage;

import dev.levimaciell.chatAPI.Validation;
import dev.levimaciell.chatAPI.message.exception.MessageDeletionValidationException;
import dev.levimaciell.chatAPI.message.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ValidateNotExistingMessage implements Validation<UUID> {

    @Autowired
    private MessageRepository repository;

    @Override
    public void validate(UUID value) {
        if(!repository.existsById(value))
            throw new MessageDeletionValidationException("Message not found");
    }
}
