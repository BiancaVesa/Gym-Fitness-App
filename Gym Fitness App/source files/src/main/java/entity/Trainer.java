package entity;


import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainer")
public class Trainer {

    @Id
    private String idTrainer;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idProfile", nullable = false)
    private Profile profile;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_credentials")
    private Login trainerCredentials;

    @OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Schedule> schedules;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.REMOVE)
    private List<Rating> rating;


    public Trainer() {

    }


    public Trainer(String idTrainer) {
        this.idTrainer = idTrainer;
    }


    public Login getTrainerCredentials() {
        return trainerCredentials;
    }


    public void setTrainerCredentials(Login trainerCredentials) {
        this.trainerCredentials = trainerCredentials;
    }


    public String getTrainerIdCredentials() {
        return trainerCredentials.getIdCredentials();
    }


    public void setTrainerCredentials(String userName, String password) {
        this.trainerCredentials = new Login(this.getIdTrainer(), userName, password, "trainer");
    }


    public Profile getProfile() {
        return profile;
    }


    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    public String getIdTrainer() {
        return idTrainer;
    }


    public void setIdTrainer(String idTrainer) {
        this.idTrainer = idTrainer;
    }


    public String getIdProfile() {
        return profile.getId();
    }


    public List<Schedule> getSchedules() {
        return schedules;
    }


    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }


    public List<Rating> getRating() {
        return rating;
    }


    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public String toString() {
        return "ID: " + this.idTrainer;
    }
}
