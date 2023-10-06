package com.extralessonsapplication.school;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SchoolRepository extends CrudRepository<SchoolEntity, Long> {

    ArrayList<SchoolEntity> findAllByIsActiveIsTrue();

}
