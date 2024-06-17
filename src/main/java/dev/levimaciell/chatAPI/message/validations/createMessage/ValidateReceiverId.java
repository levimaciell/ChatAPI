package dev.levimaciell.chatAPI.message.validations.createMessage;

import dev.levimaciell.chatAPI.Validation;
import dev.levimaciell.chatAPI.message.dto.MessageCreationDto;
import dev.levimaciell.chatAPI.message.exception.MessageCreationValidationException;
import dev.levimaciell.chatAPI.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateReceiverId implements Validation<MessageCreationDto> {

    @Autowired
    private UserRepository repository;

    @Override
    public void validate(MessageCreationDto value) {
        var user = repository.findById(value.receiverId());

        if(user.isEmpty())
            throw new MessageCreationValidationException("Receiver not found!");


    }
}
