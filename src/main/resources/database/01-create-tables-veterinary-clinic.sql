--liquibase formatted sql
--changeset VeterinaryClinic:1

CREATE TABLE patients (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    phone_number VARCHAR(9) UNIQUE,
    e_mail VARCHAR(255) UNIQUE,
    address VARCHAR(1000)
);

CREATE TABLE specializations (
    id BIGSERIAL PRIMARY KEY,
    specialization VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE statuses (
    id BIGSERIAL PRIMARY KEY,
    status VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE veterinarians (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    specialization_id BIGINT NOT NULL,
    e_mail VARCHAR(255) UNIQUE,
    phone_number VARCHAR(9) UNIQUE,
    CONSTRAINT fk_specialization
        FOREIGN KEY (specialization_id)
        REFERENCES specializations(id)
        ON DELETE RESTRICT
);

CREATE TABLE animals (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    species VARCHAR(255),
    breed VARCHAR(255),
    age INTEGER CHECK (age >= 0 AND age <= 100),
    patient_id BIGINT NOT NULL,
    CONSTRAINT fk_animals_patient
        FOREIGN KEY (patient_id) REFERENCES patients(id)
);

CREATE TABLE appointments (
    id BIGSERIAL PRIMARY KEY,
    animal_id BIGINT NOT NULL,
    veterinarian_id BIGINT NOT NULL,
    date TIMESTAMP NOT NULL,
    description VARCHAR(1000),
    status_id BIGINT NOT NULL,

    CONSTRAINT fk_appointment_animal
        FOREIGN KEY (animal_id) REFERENCES animals(id),
    CONSTRAINT fk_appointment_veterinarian
        FOREIGN KEY (veterinarian_id) REFERENCES veterinarians(id),
    CONSTRAINT fk_appointment_status
        FOREIGN KEY (status_id) REFERENCES statuses(id)
);
