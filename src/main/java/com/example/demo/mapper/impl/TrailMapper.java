package com.example.demo.mapper.impl;

import com.example.demo.domain.dto.TrailDto;
import com.example.demo.domain.entity.TrailEntity;
import com.example.demo.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TrailMapper implements Mapper<TrailEntity, TrailDto> {

    private ModelMapper modelMapper;

    public TrailMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TrailDto mapTo(TrailEntity trailEntity) {
        return modelMapper.map(trailEntity, TrailDto.class);
    }

    @Override
    public TrailEntity mapFrom(TrailDto trailDto) {
        return modelMapper.map(trailDto, TrailEntity.class);
    }
}
