package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ChatMessage;
import za.ac.cput.service.ChatService;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/messages")
public class ChatMessagesController {

    private final ChatService chatService;

    @Autowired
    public ChatMessagesController(ChatService chatService) {
        this.chatService = chatService;
    }

    // Create a new chat message
    @PostMapping("/create")
    public ResponseEntity<ChatMessage> createMessage(@RequestBody Map<String, Object> payload) {
        String question = (String) payload.get("question");
        String answer = (String) payload.get("answer");

        ChatMessage createdMessage = chatService.create(question, answer);
        return new ResponseEntity<>(createdMessage, HttpStatus.CREATED);
    }

    // Read a message by its ID
    @GetMapping("/read/{messageId}")
    public ChatMessage readMessage(@PathVariable int messageId) {
        return chatService.read(messageId);
    }

    // Delete a message by its ID
    @DeleteMapping("/delete/{messageId}")
    public void deleteMessage(@PathVariable int messageId) {
        chatService.delete(messageId);
    }

    // Update a message by its ID
    @PutMapping("/update/{messageId}")
    public ChatMessage updateMessage(@PathVariable int messageId, @RequestBody ChatMessage chatMessage) {
        return chatService.update(messageId, chatMessage);
    }

    // Get all messages
    @GetMapping("/getAll")
    public List<ChatMessage> getAllMessages() {
        return chatService.getAll();
    }

    // Get messages by question (optional, for an additional feature)
    /*@GetMapping("/getByQuestion")
    public ResponseEntity<List<ChatMessage>> getMessagesByQuestion(@RequestParam("question") String question) {
        try {
            List<ChatMessage> messages = chatService.getByQuestion(question);
            if (messages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(messages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

}
