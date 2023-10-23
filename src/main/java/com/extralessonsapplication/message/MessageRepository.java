package com.extralessonsapplication.message;

import com.extralessonsapplication.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface MessageRepository extends CrudRepository<MessageEntity, Long> {
    ArrayList<MessageEntity> findAllByFromEquals(UserEntity userEntity);

    ArrayList<MessageEntity> findAllByToEquals(UserEntity userEntity);
    MessageEntity findByIdEquals(Long id);
}
