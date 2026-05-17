package com.example.demo.service;

import com.example.demo.domain.entity.TrailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TrailService {

    Page<TrailEntity> findAll(Pageable pageable);

    TrailEntity create(TrailEntity trail);
}
