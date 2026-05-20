package com.example.demo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;
import org.locationtech.jts.geom.Point;

import java.time.OffsetDateTime;

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
//    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "trail_id_seq")
    @SequenceGenerator(
        name = "trail_id_seq",
        sequenceName = "trail_id_name",
        allocationSize = 1 ) // todo так нельзя делать в продакшене когда частые обращения к этой таблице
    private Long id;

    private String name;

    private String place;

    private String date;

    @CreationTimestamp // server controlled. not handled in mapFrom() DTO -> Entity
    private OffsetDateTime createdAt;

    @UpdateTimestamp // server controlled. not handled in mapFrom
    private OffsetDateTime updatedAt;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(columnDefinition = "geometry(Point,4326)")
    private Point originPoint;

    @JdbcTypeCode(SqlTypes.GEOMETRY)
    @Column(columnDefinition = "geometry(Point,4326)")
    private Point destinationPoint;
}
