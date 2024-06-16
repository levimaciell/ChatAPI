package dev.levimaciell.chatAPI.message.controller;

import dev.levimaciell.chatAPI.message.dto.MessageCreationDto;
import dev.levimaciell.chatAPI.message.dto.MessageDto;
import dev.levimaciell.chatAPI.message.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService service;

    @Transactional
    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageCreationDto dto, HttpServletRequest req){
        var messageDto = service.createMessage(dto, req);
        return new ResponseEntity<MessageDto>(messageDto, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMessage(@PathVariable UUID id){

        service.deleteMessage(id);

        return ResponseEntity.ok().build();
    }


}
