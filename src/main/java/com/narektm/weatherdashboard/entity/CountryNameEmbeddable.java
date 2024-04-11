package com.narektm.weatherdashboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Embeddable
@Getter
@Setter
public class CountryNameEmbeddable {

    @Column(name = "name_common", nullable = false, length = 100)
    private String common;

    @Column(name = "name_official", nullable = false, length = 150)
    private String official;

    @Column(name = "name_common_native", nullable = false, length = 150)
    private String commonNative;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CountryNameEmbeddable that = (CountryNameEmbeddable) o;

        return Objects.equals(common, that.common)
                && Objects.equals(official, that.official)
                && Objects.equals(commonNative, that.commonNative);
    }

    @Override
    public int hashCode() {
        return Objects.hash(common, official, commonNative);
    }
}
