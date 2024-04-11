CREATE TABLE IF NOT EXISTS country
(
    id                 BIGSERIAL PRIMARY KEY,
    cca2               VARCHAR(2)                  NOT NULL,
    ccn3               VARCHAR(3)                  NOT NULL,
    cca3               VARCHAR(3)                  NOT NULL,
    name_common        VARCHAR(100)                NOT NULL,
    name_official      VARCHAR(150)                NOT NULL,
    name_common_native VARCHAR(150)                NOT NULL,
    capital_city       VARCHAR(150)                NOT NULL,
    region             VARCHAR(100)                NOT NULL,
    sub_region         VARCHAR(100)                NULL,
    phone_code         VARCHAR(10)                 NULL,
    flag_uri           VARCHAR(100)                NOT NULL,
    is_active          BOOLEAN                     NOT NULL DEFAULT TRUE,
    creation_date      TIMESTAMP(3) WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    last_modified_date TIMESTAMP(3) WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    CONSTRAINT UK_country_cca2 UNIQUE (cca2),
    CONSTRAINT UK_country_ccn3 UNIQUE (ccn3),
    CONSTRAINT UK_country_cca3 UNIQUE (cca3),
    CONSTRAINT UK_country__name_common UNIQUE (name_common)
);

CREATE TABLE IF NOT EXISTS city
(
    id                 BIGSERIAL PRIMARY KEY,
    geo_id             VARCHAR(50) UNIQUE          NOT NULL,
    name               VARCHAR(150)                NOT NULL,
    state_or_region    VARCHAR(150)                NULL,
    latitude           DOUBLE PRECISION            NOT NULL,
    longitude          DOUBLE PRECISION            NOT NULL,
    country_id         BIGINT                      NOT NULL,
    is_active          BOOLEAN                     NOT NULL DEFAULT TRUE,
    creation_date      TIMESTAMP(3) WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    last_modified_date TIMESTAMP(3) WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    CONSTRAINT FK_city_country FOREIGN KEY (country_id) REFERENCES country (id),
    CONSTRAINT UK_city__country_id__name UNIQUE (country_id, name)
);
