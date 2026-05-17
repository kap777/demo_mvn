package com.example.demo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrailDto { // itinerary, track, trail, trip, route

    private Long id;

    private String name;

    private String place;

    private String date;
}
