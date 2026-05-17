package com.example.demo.service.impl;

import com.example.demo.domain.entity.TrailEntity;
import com.example.demo.repository.TrailRepository;
import com.example.demo.service.TrailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TrailServiceImpl implements TrailService {

    private TrailRepository trailRepository;

    public TrailServiceImpl(TrailRepository repository) {trailRepository = repository;}

    @Override
    public Page<TrailEntity> findAll(Pageable pageable) {
        return trailRepository.findAll(pageable);
    }

    @Override
    public TrailEntity create(TrailEntity trail) {
        return trailRepository.save(trail);
    }
}
