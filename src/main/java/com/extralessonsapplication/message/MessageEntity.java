package com.extralessonsapplication.message;

import com.extralessonsapplication.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class MessageEntity {
    @Id @GeneratedValue
    private Long id;
    private LocalDate date;
    private String subject;
    private String message;
    private String answer;
    @ManyToOne
    private UserEntity from;
    @ManyToOne
    private UserEntity to;
    private String fromName;
    private String fromEmail;
    private String fromPhone;
    private Boolean done;
}
