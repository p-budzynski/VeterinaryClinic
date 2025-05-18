package pl.kurs.data;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.kurs.entity.*;
import pl.kurs.repository.AnimalRepository;
import pl.kurs.repository.AppointmentRepository;
import pl.kurs.repository.PatientRepository;
import pl.kurs.repository.VeterinarianRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class VeterinaryClinicInitializer {
    private final AnimalRepository animalRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final VeterinarianRepository veterinarianRepository;

    @Transactional
    @PostConstruct
    public void init() {
        Patient patient1 = new Patient("Anna", "Kowalska", "123456789", "anna.kowalska@example.com", "ul. Lipowa 5");
        Patient patient2 = new Patient("Jan", "Nowak", "987654321", "jan.nowak@example.com", "ul. Dębowa 12");

        Animal animal1 = new Animal("Reksio", "Dog", "Labrador", 5, patient1);
        Animal animal2 = new Animal("Mruczek", "Cat", "Persian", 3, patient2);
        Animal animal3 = new Animal("Fifi", "Dog", "Poodle", 2, patient1);

        patient1.getAnimals().addAll(List.of(animal1, animal3));
        patient2.getAnimals().add(animal2);

        patientRepository.save(patient1);
        patientRepository.save(patient2);
        animalRepository.save(animal1);
        animalRepository.save(animal2);
        animalRepository.save(animal3);


        Specialization specialization1 = new Specialization("Surgery");
        Specialization specialization2 = new Specialization("Dermatology");

        Veterinarian vet1 = new Veterinarian("Marta", "Zielińska", specialization1, "marta.z@example.com", "111222333");
        Veterinarian vet2 = new Veterinarian("Tomasz", "Lis", specialization2, "tomasz.l@example.com", "444555666");

        veterinarianRepository.save(vet1);
        veterinarianRepository.save(vet2);


        Status status1 = new Status("Scheduled");
        Status status2 = new Status("Completed");
        Status status3 = new Status("Canceled");
        Status status4 = new Status("In Progress");

        Appointment appointment1 = new Appointment(animal1, vet1, "Check-up before surgery", status1);
        Appointment appointment2 = new Appointment(animal2, vet2, "Skin allergy consultation", status2);
        Appointment appointment3 = new Appointment(animal3, vet1, "Vaccination", status4);
        Appointment appointment4 = new Appointment(animal1, vet2, "Post-surgery review", status3);

        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
        appointmentRepository.save(appointment3);
        appointmentRepository.save(appointment4);

    }
}
