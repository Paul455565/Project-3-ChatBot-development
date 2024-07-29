package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.ChatSession;
import za.ac.cput.service.ChatSessionService;

import java.util.List;

@RestController
@RequestMapping("/chatSession")
public class ChatSessionController {
    @Autowired
    private ChatSessionService chatSessionService;

    @PostMapping("/save")
    public ChatSession save(@RequestBody ChatSession chatSession) {
        return chatSessionService.create(chatSession);
    }

    @GetMapping("/read/{sessionId}")
    public ChatSession read(@PathVariable Long sessionId) {
        return chatSessionService.read(sessionId);
    }

    @PutMapping("/update")
    public ChatSession update(@RequestBody ChatSession chatSession) {
        return chatSessionService.update(chatSession);
    }

    @DeleteMapping("/delete/{sessionId}")
    public void delete(@PathVariable Long sessionId) {
        chatSessionService.delete(sessionId);
    }

    @GetMapping("/getAll")
    public List<ChatSession> getAll() {
        return chatSessionService.getAll();
    }
}
