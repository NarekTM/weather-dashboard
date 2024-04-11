package com.narektm.weatherdashboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Objects;

@Entity
@Table(name = "city")
@Getter
@Setter
public class CityEntity {

    private static final String ID_SEQUENCE = "city_id_seq";

    @SequenceGenerator(name = ID_SEQUENCE, sequenceName = ID_SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE)
    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String geoId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 150)
    private String stateOrRegion;

    @Column(nullable = false)
    private double latitude;

    @Column(nullable = false)
    private double longitude;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    @CreationTimestamp
    private ZonedDateTime creationDate;

    @Column(nullable = false)
    @UpdateTimestamp
    private ZonedDateTime lastModifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id", updatable = false)
    private CountryEntity country;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CityEntity that = (CityEntity) o;

        return Double.compare(latitude, that.latitude) == 0
                && Double.compare(longitude, that.longitude) == 0
                && Objects.equals(geoId, that.geoId)
                && Objects.equals(name, that.name)
                && Objects.equals(stateOrRegion, that.stateOrRegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(geoId, name, stateOrRegion, latitude, longitude);
    }
}
