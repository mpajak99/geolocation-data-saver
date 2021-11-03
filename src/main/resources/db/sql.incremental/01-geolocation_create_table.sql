--liquibase formatted sql
--changeset mpajak:1

CREATE TABLE geolocation (
    id serial,
    device_id varchar(50) NOT NULL,
    latitude numeric NOT NULL,
    longitude numeric NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    last_update_date timestamp without time zone NOT NULL
);

ALTER TABLE geolocation ADD CONSTRAINT "PK_geolocation"
    PRIMARY KEY(id)
;

ALTER TABLE geolocation ADD CONSTRAINT "geolocation_device_id_unique"
    UNIQUE (device_id)
;