package com.example.demo.mapper.impl;

import com.example.demo.domain.dto.TrailDto;
import com.example.demo.domain.entity.TrailEntity;
import com.example.demo.mapper.Mapper;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TrailMapper implements Mapper<TrailEntity, TrailDto> {

    private final ModelMapper modelMapper;

    private static final GeometryFactory factory = new GeometryFactory();

    public TrailMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public TrailDto mapTo(TrailEntity entity) {

        Double originLat = null;
        Double originLng = null;
        Double destinationLat = null;
        Double destinationLng = null;

        if (entity.getOriginPoint() != null) {
            originLat = entity.getOriginPoint().getY();
            originLng = entity.getOriginPoint().getX();
        }

        if (entity.getDestinationPoint() != null) {
            destinationLat = entity.getDestinationPoint().getY();
            destinationLng = entity.getDestinationPoint().getX();
        }

        return TrailDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .place(entity.getPlace())
                .date(entity.getDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .originLatitude(originLat)
                .originLongitude(originLng)
                .destinationLatitude(destinationLat)
                .destinationLongitude(destinationLng)
                .build();
    }

    @Override
    public TrailEntity mapFrom(TrailDto dto) {

        Point originPoint = null;
        Point destinationPoint = null;

        if (dto.getOriginLatitude() != null && dto.getOriginLongitude() != null) {
            originPoint = factory.createPoint(
                new Coordinate(dto.getOriginLongitude(), dto.getOriginLatitude())
            );
        }

        if (dto.getDestinationLatitude() != null && dto.getDestinationLongitude() != null) {
            destinationPoint = factory.createPoint(
                new Coordinate(dto.getDestinationLongitude(), dto.getDestinationLatitude())
            );
        }

        return TrailEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .place(dto.getPlace())
                .date(dto.getDate())
                .originPoint(originPoint)
                .destinationPoint(destinationPoint)
                .build();
    }
}
