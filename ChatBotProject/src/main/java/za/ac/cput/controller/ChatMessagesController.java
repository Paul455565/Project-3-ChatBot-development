package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.service.ChatService;

import java.util.List;

@RestController
@RequestMapping("/api/chat/messages")
@CrossOrigin(origins = "http://localhost:5173")
public class ChatMessagesController {

    @Autowired
    private ChatService chatService;

    @Autowired
    public ChatMessagesController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/messages")
    public ResponseEntity<ChatMessage> saveMessage(@RequestBody ChatMessage chatMessage) {
        ChatMessage savedMessage = chatService.saveMessage(chatMessage);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
    }

    @GetMapping("/messages/session/{sessionId}")
    public ResponseEntity<List<ChatMessage>> getMessagesBySessionId(@PathVariable("sessionId") Long sessionId) {
        List<ChatMessage> messages = chatService.getMessagesBySessionId(sessionId);
        if (messages == null || messages.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void> deleteMessageById(@PathVariable("id") int id) {
        chatService.deleteMessageById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

