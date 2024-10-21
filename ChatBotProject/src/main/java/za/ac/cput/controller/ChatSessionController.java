package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.service.ChatSessionService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/chatSession")
public class ChatSessionController {

    @Autowired
    private ChatSessionService chatSessionService;

    @PostMapping("/create")
    public ChatSession create(@RequestParam String email,
                                         @RequestParam String question,
                                         @RequestParam String answer) {
        return chatSessionService.create(email, question, answer);
    }

    @GetMapping("/read/{sessionId}")
    public ChatSession read(@PathVariable int sessionId) {
        return chatSessionService.read(sessionId);
    }

    @PutMapping("/update")
    public ChatSession update(@RequestBody ChatSession chatSession) {
        return chatSessionService.update(chatSession);
    }

    @DeleteMapping("/delete/{sessionId}")
    public void delete(@PathVariable int sessionId) {
        chatSessionService.delete(sessionId);
    }

    @GetMapping("/getAll")
    public List<ChatSession> getAll() {
        return chatSessionService.getAll();
    }
}
