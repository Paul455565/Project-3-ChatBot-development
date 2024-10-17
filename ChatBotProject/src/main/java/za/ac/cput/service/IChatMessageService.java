package za.ac.cput.service;

import za.ac.cput.domain.ChatMessage;

import java.util.List;

public interface IChatMessageService extends IService<ChatMessage,Integer>{
    ChatMessage create(ChatMessage chatMessage);
    ChatMessage update(ChatMessage chatMessage);
    List<ChatMessage> getAll();
}
