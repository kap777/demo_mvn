package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trails")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class TrailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trail_id_seq")
    private Long id;

    private String name;

    private String place;

    private String date;
}
