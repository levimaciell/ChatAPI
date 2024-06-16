package dev.levimaciell.chatAPI.message.service;

import dev.levimaciell.chatAPI.Validation;
import dev.levimaciell.chatAPI.message.dto.MessageCreationDto;
import dev.levimaciell.chatAPI.message.dto.MessageDto;
import dev.levimaciell.chatAPI.message.entity.Message;
import dev.levimaciell.chatAPI.message.repository.MessageRepository;
import dev.levimaciell.chatAPI.tokens.TokenService;
import dev.levimaciell.chatAPI.user.entity.User;
import dev.levimaciell.chatAPI.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private List<Validation<MessageCreationDto>> messageCreationValidations;

    public MessageDto createMessage(MessageCreationDto dto, HttpServletRequest req) {

        messageCreationValidations.forEach(v -> v.validate(dto));

        //After validation, get actual user and receiver user;

        var senderUser = getUserByToken(cleanToken(req.getHeader("Authorization")));
        var receiverUser = userRepository.findById(dto.receiverId())
                .orElseThrow(()-> new RuntimeException("Receiver user not found"));

        var message = new Message(dto.message(), senderUser, receiverUser);
        messageRepository.save(message);

        return new MessageDto(message);
    }
    
    private User getUserByToken(String token){
        var userName = tokenService.validateTokenAndGetSubject(token);
        return userRepository.getReferenceByUsername(userName);
    }

    private String cleanToken(String token){
        return token.replace("Bearer ", "");
    }
}
