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
import java.util.Objects;

@Entity
@Table(name = "country")
@Getter
@Setter
public class CountryEntity {

    private static final String ID_SEQUENCE = "country_id_seq";

    @SequenceGenerator(name = ID_SEQUENCE, sequenceName = ID_SEQUENCE, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = ID_SEQUENCE)
    @Id
    private Long id;

    /**
     * code ISO 3166-1 alpha-2
     */
    @Column(nullable = false, length = 2, unique = true)
    private String cca2;

    /**
     * code ISO 3166-1 numeric
     */
    @Column(nullable = false, length = 3, unique = true)
    private String ccn3;

    /**
     * code ISO 3166-1 alpha-3
     */
    @Column(nullable = false, length = 3, unique = true)
    private String cca3;

    @Embedded
    private CountryNameEmbeddable name;

    @Column(nullable = false, length = 150)
    private String capitalCity;

    @Column(nullable = false, length = 100)
    private String region;

    @Column(length = 100)
    private String subRegion;

    @Column(length = 10)
    private String phoneCode;

    @Column(nullable = false, length = 100)
    private String flagUri;

    @Column(name = "is_active", nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    @CreationTimestamp
    private ZonedDateTime creationDate;

    @Column(nullable = false)
    @UpdateTimestamp
    private ZonedDateTime lastModifiedDate;

    @OneToMany(mappedBy = "country")
    private List<CityEntity> cities;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CountryEntity that = (CountryEntity) o;

        return Objects.equals(cca2, that.cca2)
                && Objects.equals(ccn3, that.ccn3)
                && Objects.equals(cca3, that.cca3)
                && Objects.equals(name, that.name)
                && Objects.equals(capitalCity, that.capitalCity)
                && Objects.equals(region, that.region)
                && Objects.equals(subRegion, that.subRegion)
                && Objects.equals(phoneCode, that.phoneCode)
                && Objects.equals(flagUri, that.flagUri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cca2, ccn3, cca3, name, capitalCity, region, subRegion, phoneCode, flagUri);
    }
}
