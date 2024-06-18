package dev.levimaciell.chatAPI.message.controller;

import dev.levimaciell.chatAPI.message.dto.MessageCreationDto;
import dev.levimaciell.chatAPI.message.dto.MessageDto;
import dev.levimaciell.chatAPI.message.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(
        name = "Messages",
        description = "Endpoint for managing messages"
)
public class MessageController {

    @Autowired
    private MessageService service;

    @Transactional
    @PostMapping
    @Operation(
            summary = "Creates a message",
            description = "Creates a new message, as long as it's not blank and that you give the receiver id." +
                    "Endpoint requires authentication",
            tags = {"Messages"}
    )
    public ResponseEntity<MessageDto> createMessage(@RequestBody @Valid MessageCreationDto dto, HttpServletRequest req){
        var messageDto = service.createMessage(dto, req);
        return new ResponseEntity<MessageDto>(messageDto, HttpStatus.CREATED);
    }

    @Transactional
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletes a message",
            description = "Deletes a message, by giving the id of this message. " +
                    "Endpoint requires authentication",
            tags = {"Messages"}
    )
    public ResponseEntity deleteMessage(@PathVariable UUID id){

        service.deleteMessage(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    @Operation(
            summary = "Gets a page of messages",
            description = "Gets a page of messages of the user which is authenticated, whether he is the sender or receiver. " +
                    "Endpoint requires authentication",
            tags = {"Messages"}
    )
    public Page<MessageDto> getMessages(@PageableDefault(
            sort = "creationTime",
            size = 15,
            direction = Sort.Direction.DESC

    ) Pageable pageable, HttpServletRequest req){
        return service.getMessages(pageable, req);
    }
}
