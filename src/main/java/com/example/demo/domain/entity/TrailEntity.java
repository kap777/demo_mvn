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
    @SequenceGenerator(
            name = "trail_id_seq",
            sequenceName = "trail_id_name",
            allocationSize = 1
    )
    private Long id;

    private String name;

    private String place;

    private String date;
}
