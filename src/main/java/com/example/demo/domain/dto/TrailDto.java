package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrailDto { // itinerary, track, trail, trip, route

    // server controlled
    private Long id;

    private String name;

    private String place;

    private String date;

    // server controlled, not handled in mapFrom
    private OffsetDateTime createdAt;

    // server controlled, not handled in mapFrom
    private OffsetDateTime updatedAt;

    private Double originLatitude;

    private Double originLongitude;

    private Double destinationLatitude;

    private Double destinationLongitude;
}
