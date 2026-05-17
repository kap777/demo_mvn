package com.example.demo.repository;

import com.example.demo.domain.entity.TrailEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrailRepository extends CrudRepository<TrailEntity, Long>, PagingAndSortingRepository<TrailEntity, Long> {

}
