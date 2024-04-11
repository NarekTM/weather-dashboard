package com.narektm.weatherdashboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "country")
@Getter
@Setter
public class Country {

    private static final String ID_SEQUENCE = "country_id_seq";

    @SequenceGenerator(name = ID_SEQUENCE, sequenceName = ID_SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE)
    @Id
    private Long id;

    /**
     * code ISO 3166-1 alpha-2
     */
    @Column(nullable = false, length = 2)
    private String cca2;

    /**
     * code ISO 3166-1 numeric
     */
    @Column(nullable = false, length = 3)
    private String ccn3;

    /**
     * code ISO 3166-1 alpha-3
     */
    @Column(nullable = false, length = 3)
    private String cca3;

    @Embedded
    private CountryNameEmbeddable name;

    @Column(nullable = false, length = 150)
    private String capitalCity;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(nullable = false, length = 100)
    private String subRegion;

    @Column(length = 10)
    private String phoneCode;

    @Column(nullable = false)
    private boolean isActive = true;

    @Column(nullable = false)
    @CreationTimestamp
    private ZonedDateTime creationDate;

    @Column(nullable = false)
    @UpdateTimestamp
    private ZonedDateTime lastModifiedDate;

    @OneToMany(mappedBy = "country")
    private List<City> cities;
}
