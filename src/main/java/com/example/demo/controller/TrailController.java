package com.example.demo.controller;

import com.example.demo.domain.dto.TrailDto;
import com.example.demo.domain.entity.TrailEntity;
import com.example.demo.mapper.Mapper;
import com.example.demo.service.TrailService;
import com.example.demo.util.Util;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class TrailController {

    private Mapper<TrailEntity, TrailDto> trailMapper;

    private TrailService trailService;

    @GetMapping(path = "/trails") // query parameters: ?size=5&page=0
    public Page<TrailDto> listTrails(@PageableDefault(value = 10) Pageable pageable) {
        Page<TrailEntity> trails = trailService.findAll(pageable);
        return trails.map(trailMapper::mapTo);
//        return books.stream()
//                .map(bookMapper::mapTo)
//                .collect(Collectors.toList());
    }

    @PostMapping(path = "/trails")
    public ResponseEntity<TrailDto> createAuthor(@RequestBody TrailDto trailDto) {
        TrailEntity trailEntity = trailMapper.mapFrom(trailDto);
        TrailEntity savedTrailEntity = trailService.create(trailEntity);
        return new ResponseEntity<>(trailMapper.mapTo(savedTrailEntity), HttpStatus.CREATED);
    }

    @GetMapping(path = "/trails/add")
    public ResponseEntity<?> getAuthor(
            @RequestParam(required = false) Boolean post,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String place,
            @RequestParam(required = false) String date
    ) {
        TrailDto newTrailDto = new TrailDto(null, name, place, date);

        if (Boolean.TRUE == post && Util.notNullOrBlank(place) && Util.notNullOrBlank(date)) {
            final TrailEntity trailEntity = trailMapper.mapFrom(newTrailDto);
            final TrailEntity savedTrailEntity = trailService.create(trailEntity);

            if (savedTrailEntity != null) {
                return new ResponseEntity<>(
                        trailMapper.mapTo(savedTrailEntity),
                        HttpStatus.OK
                );
            }
        }

        return new ResponseEntity<>("Bad request. Trail hasn't been saved to DB", HttpStatus.BAD_REQUEST);
    }

}
