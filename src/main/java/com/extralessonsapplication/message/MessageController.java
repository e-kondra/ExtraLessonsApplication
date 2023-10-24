package com.extralessonsapplication.message;

import com.extralessonsapplication.user.UserEntity;
import com.extralessonsapplication.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/contact")
    public String displayMessage(Model model){
        model.addAttribute("LoggedUser", null);
        return "message";
    }

    @PostMapping("/contact")
    public String handleSendingMessage(MessageEntity message){
        try {
            message.setDate(LocalDate.now());
            message.setTo(this.messageService.getActiveModerator());
            message.setDone(false);
            this.messageService.createMessage(message);
            return "redirect:/contact?status=MESSAGE_WAS_SEND_SUCCESSFULLY";
        } catch (Exception exception){
            return "redirect:/contact?status=SENDING_MESSAGE_FAILED&error=" + exception.getMessage();
        }
    }

    @GetMapping("/message_create")
    public String displayMessageCreation(Model model,
                                         @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId,
                                         @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId){
        try {
            UserEntity user = this.userService.findUserById(Long.parseLong(loggedInUserId));
            model.addAttribute("user", user);
            model.addAttribute("isModerator", this.userService.isUserModerator(user));
            model.addAttribute("isTeacher", this.userService.isUserTeacher(user));
            model.addAttribute("isParent", this.userService.isUserParent(user));
            if(!chosenSchoolId.equals("null")) {
                model.addAttribute("chosenSchoolId", chosenSchoolId);
            }
            return "userMessage";
        } catch (Exception e){
            return "redirect:/messagesListFromUser?status=MESSAGE_CREATING_FAILED " + e.getMessage();
        }
    }

    @PostMapping("/message_create")
    public String handleMessageCreating( MessageEntity message,
            @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId){
        try{
            UserEntity user = this.userService.findUserById(Long.parseLong(loggedInUserId));
            message.setDate(LocalDate.now());
            message.setFrom(user);
            message.setTo(this.messageService.getActiveModerator());
            message.setDone(false);
            this.messageService.createMessage(message);
            return "redirect:/messagesListFromUser?status=MESSAGE_WAS_CREATED_SUCCESSFULLY";
        } catch (Exception exception){
            return "redirect:/messagesListFromUser?status=MESSAGE_CREATING_FAILED " + exception.getMessage();
        }
    }

    @GetMapping("/messagesListFromUser")
    public String displayMessagesList( Model model,
                                       @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId,
                                       @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId){
        try{
            UserEntity user = this.userService.findUserById(Long.parseLong(loggedInUserId));
            model.addAttribute("messages", this.messageService.getAllMessegesFromUser(user));
            model.addAttribute("isModerator", this.userService.isUserModerator(user));
            model.addAttribute("isTeacher", this.userService.isUserTeacher(user));
            model.addAttribute("isParent", this.userService.isUserParent(user));
            if(!chosenSchoolId.equals("null")) {
                model.addAttribute("chosenSchoolId", chosenSchoolId);
            }
            return "messagesList";
        } catch (Exception exception){
            return "redirect:/messagesListFromUser?status=MESSAGES_LIST_FAILED " + exception.getMessage();
        }
    }

    @GetMapping("/messagesListToUser")
    public String displayMessagesForUser(Model model,
                                         @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId) {
        try {
            UserEntity user = this.userService.findUserById(Long.parseLong(loggedInUserId));
            model.addAttribute("messages", this.messageService.getAllMessegesToUser(user));
            model.addAttribute("isModerator", this.userService.isUserModerator(user));
            model.addAttribute("isTeacher", this.userService.isUserTeacher(user));
            model.addAttribute("isParent", this.userService.isUserParent(user));
            return "messagesList";
        } catch (Exception exception){
        return "redirect:/messagesListToUser?status=MESSAGES_LIST_FAILED " + exception.getMessage();
        }
    }


    @GetMapping("/handleMessage/{id}")
    public String displayMessageForHandling(Model model,
                                            @PathVariable() Long id,
                                            @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId,
                                            @CookieValue(name = "chosenSchoolId", defaultValue = "null") String chosenSchoolId){
        try {
            MessageEntity message = this.messageService.getMessageById(id);
            UserEntity user = this.userService.findUserById(Long.parseLong(loggedInUserId));
            model.addAttribute("message", message);
            model.addAttribute("isModerator", this.userService.isUserModerator(user));
            model.addAttribute("isTeacher", this.userService.isUserTeacher(user));
            model.addAttribute("isParent", this.userService.isUserParent(user));
            if(!chosenSchoolId.equals("null")) {
                model.addAttribute("chosenSchoolId", chosenSchoolId);
            }
            return "handleMessage";
        } catch (Exception exception){
            return "redirect:/messagesListToUser?status=MESSAGES_LIST_FAILED " + exception.getMessage();
        }
    }

    @PostMapping("/handleMessage/{id}")
    public String handleAnswer(MessageEntity message, @PathVariable() Long id,
                               @CookieValue(name = "loggedInUserId", defaultValue = "null") String loggedInUserId){
        try {
            MessageEntity messageForUpdate = this.messageService.getMessageById(id);
            message.setId(messageForUpdate.getId());
            message.setDate(messageForUpdate.getDate());
            message.setFrom(messageForUpdate.getFrom());
            message.setMessage(messageForUpdate.getMessage());
            message.setTo(messageForUpdate.getTo());
            this.messageService.updateMessage(message);
            return "redirect:/messagesListToUser?status=MESSAGE_WAS_HANDLED_SUCCESSFULLY ";
        } catch (Exception exception){
            return "redirect:/messagesListToUser?status=HANDLING_MESSAGE_FAILED " + exception.getMessage();
        }
    }
}
