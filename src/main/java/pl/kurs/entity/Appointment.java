package pl.kurs.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "veterinarian_id", nullable = false)
    private Veterinarian veterinarian;

    @Column(nullable = false)
    private LocalDateTime date;

    @Column(length = 1000)
    private String description;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;

    public Appointment(Animal animal, Veterinarian veterinarian, String description, Status status) {
        this.animal = animal;
        this.veterinarian = veterinarian;
        this.date = LocalDateTime.now();
        this.description = description;
        this.status = status;
    }
}
