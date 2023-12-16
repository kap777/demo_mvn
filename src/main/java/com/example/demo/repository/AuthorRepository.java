package com.example.demo.repository;

import com.example.demo.domain.entity.AuthorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    // understands sql query from method name
//    Iterable<AuthorEntity> ageLessThan(int age);
//
//    @Query("SELECT a from Author a where a.age > ?1")
//    Iterable<AuthorEntity> findAuthorsWithAgeGreaterThan(int age);
}
