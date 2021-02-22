package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    private String idClass;

    @Column(unique = true, nullable = false)
    private LocalDateTime Temp;

    @Column(nullable = false)
    private String ClassName;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private Trainer trainer;

    @OneToOne(mappedBy = "bookedTraining")
    private Booking booking;


    public Schedule() {
    }


    public Schedule(String idClass) {
        this.idClass = idClass;
    }


    public String getIdClass() {
        return idClass;
    }


    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }


    public LocalDateTime getTemp() {
        return Temp;
    }


    public void setTemp(LocalDateTime temp) {
        Temp = temp;
    }


    public String getClassName() {
        return ClassName;
    }


    public void setClassName(String className) {
        ClassName = className;
    }


    public Booking getBooking() {
        return booking;
    }


    public void setBooking(Booking booking) {
        this.booking = booking;
    }


    public Trainer getTrainer() {
        return trainer;
    }


    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

}
