--liquibase formatted sql
--changeset VeterinaryClinic:2

INSERT INTO patients (first_name, last_name, phone_number, e_mail, address)
VALUES
    ('Anna', 'Kowalska', '123456789', 'anna.kowalska@example.com', 'ul. Lipowa 5'),
    ('Jan', 'Nowak', '987654321', 'jan.nowak@example.com', 'ul. Dębowa 12');

INSERT INTO animals (name, species, breed, age, patient_id)
VALUES
    ('Reksio', 'Dog', 'Labrador', 5, (SELECT id FROM patients WHERE first_name = 'Anna' AND last_name = 'Kowalska')),
    ('Mruczek', 'Cat', 'Persian', 3, (SELECT id FROM patients WHERE first_name = 'Jan' AND last_name = 'Nowak')),
    ('Fifi', 'Dog', 'Poodle', 2, (SELECT id FROM patients WHERE first_name = 'Anna' AND last_name = 'Kowalska'));

INSERT INTO specializations (specialization)
VALUES
    ('Surgery'),
    ('Dermatology');

INSERT INTO veterinarians (first_name, last_name, specialization_id, e_mail, phone_number)
VALUES
    ('Marta', 'Zielińska',
        (SELECT id FROM specializations WHERE specialization = 'Surgery'),
        'marta.z@example.com', '111222333'),
    ('Tomasz', 'Lis',
        (SELECT id FROM specializations WHERE specialization = 'Dermatology'),
        'tomasz.l@example.com', '444555666');

INSERT INTO statuses (status)
VALUES
    ('Scheduled'),
    ('Completed'),
    ('Canceled'),
    ('In Progress');

INSERT INTO appointments (animal_id, veterinarian_id, date, description, status_id)
VALUES
    (
        (SELECT a.id FROM animals a JOIN patients p ON a.patient_id = p.id WHERE a.name = 'Reksio' AND p.first_name = 'Anna'),
        (SELECT id FROM veterinarians WHERE first_name = 'Marta' AND last_name = 'Zielińska'),
        NOW(),
        'Check-up before surgery',
        (SELECT id FROM statuses WHERE status = 'Scheduled')
    ),
    (
        (SELECT a.id FROM animals a JOIN patients p ON a.patient_id = p.id WHERE a.name = 'Mruczek' AND p.first_name = 'Jan'),
        (SELECT id FROM veterinarians WHERE first_name = 'Tomasz' AND last_name = 'Lis'),
        NOW(),
        'Skin allergy consultation',
        (SELECT id FROM statuses WHERE status = 'Completed')
    ),
    (
        (SELECT a.id FROM animals a JOIN patients p ON a.patient_id = p.id WHERE a.name = 'Fifi' AND p.first_name = 'Anna'),
        (SELECT id FROM veterinarians WHERE first_name = 'Marta' AND last_name = 'Zielińska'),
        NOW(),
        'Vaccination',
        (SELECT id FROM statuses WHERE status = 'In Progress')
    ),
    (
        (SELECT a.id FROM animals a JOIN patients p ON a.patient_id = p.id WHERE a.name = 'Reksio' AND p.first_name = 'Anna'),
        (SELECT id FROM veterinarians WHERE first_name = 'Tomasz' AND last_name = 'Lis'),
        NOW(),
        'Post-surgery review',
        (SELECT id FROM statuses WHERE status = 'Canceled')
    );