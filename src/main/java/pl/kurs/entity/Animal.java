package pl.kurs.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "animals")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String species;

    private String breed;

    @Min(0)
    @Max(100)
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Animal(String name, String species, String breed, Integer age, Patient patient) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.age = age;
        this.patient = patient;
    }
}
