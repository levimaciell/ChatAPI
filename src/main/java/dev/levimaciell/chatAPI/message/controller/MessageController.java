package dev.levimaciell.chatAPI.message.controller;

import dev.levimaciell.chatAPI.message.dto.MessageCreationDto;
import dev.levimaciell.chatAPI.message.dto.MessageDto;
import dev.levimaciell.chatAPI.message.service.MessageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<MessageDto> createMessage(@RequestBody @Valid MessageCreationDto dto, HttpServletRequest req){
        var messageDto = service.createMessage(dto, req);
        return new ResponseEntity<MessageDto>(messageDto, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteMessage(@PathVariable UUID id){

        service.deleteMessage(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<MessageDto> getMessages(@PageableDefault(
            sort = "creationTime",
            size = 15,
            direction = Sort.Direction.DESC

    ) Pageable pageable, HttpServletRequest req){
        return service.getMessages(pageable, req);
    }
}
