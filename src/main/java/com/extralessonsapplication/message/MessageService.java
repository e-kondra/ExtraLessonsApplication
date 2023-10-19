package com.extralessonsapplication.message;

import com.extralessonsapplication.user.UserEntity;
import com.extralessonsapplication.user.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserService userService;

    public MessageService(MessageRepository messageRepository, UserService userService) {
        this.messageRepository = messageRepository;
        this.userService = userService;
    }

    public UserEntity getActiveModerator() throws Exception{
        return this.userService.findActiveModerator();
    }

    public void createMessage(MessageEntity message) {
        this.messageRepository.save(message);
    }

    public ArrayList<MessageEntity> getAllMessegesFromUser(UserEntity user) {
        return (ArrayList<MessageEntity>) this.messageRepository.findAllByFromEquals(user);
    }

    public ArrayList<MessageEntity> getAllMessegesToUser(UserEntity user) {
        return (ArrayList<MessageEntity>) this.messageRepository.findAllByToEquals(user);
    }

    public MessageEntity getMessageById(Long id) throws Exception{
        return this.messageRepository.findById(id).orElseThrow();
    }

    public void updateMessage(MessageEntity message)  {
        this.messageRepository.save(message);
    }
}
