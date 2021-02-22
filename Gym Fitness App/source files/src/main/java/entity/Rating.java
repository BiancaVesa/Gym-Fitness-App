package entity;

import javax.persistence.*;

/**
 * The type Rating.
 */
@Entity
@Table(name = "rating")
public class Rating {

    @Id
    private String idRating;

    @ManyToOne
    @JoinColumn(name = "id_trainer")
    private Trainer trainer;

    @Column(nullable = false)
    private String idClient;

    @Column
    private float grade;


    public Rating() {
    }


    public Rating(String idRating) {
        this.idRating = idRating;
    }


    public String getIdRating() {
        return idRating;
    }


    public void setIdRating(String idRating) {
        this.idRating = idRating;
    }


    public float getGrade() {
        return grade;
    }


    public void setGrade(float grade) {
        this.grade = grade;
    }


    public Trainer getTrainer() {
        return trainer;
    }


    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }


    public String getIdClient() {
        return idClient;
    }


    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }
}
