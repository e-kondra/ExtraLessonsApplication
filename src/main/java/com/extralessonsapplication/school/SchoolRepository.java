package com.extralessonsapplication.school;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends CrudRepository<SchoolEntity, Long> {
}
