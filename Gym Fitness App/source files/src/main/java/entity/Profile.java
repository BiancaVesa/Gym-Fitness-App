package entity;

import javax.persistence.*;


@Entity
@Table(name = "profile")

public class Profile {

    @Id
    private String id;

    @Column
    private String type;

    @Column
    private String name;

    @Column
    private String birthday;

    @Column
    private int Progress;

    @Column
    private String sex;

    @Column
    private float rating;

    @Column
    private String certification;

    @OneToOne(mappedBy = "profile")
    private Trainer trainer;

    @OneToOne(mappedBy = "profile")
    private Client client;


    public Profile() {

    }


    public Profile(String id) {
        this.id = id;
    }


    public String getBirthday() {
        return birthday;
    }


    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public int getProgress() {
        return Progress;
    }


    public void setProgress(int progress) {
        Progress = progress;
    }


    public String getSex() {
        return sex;
    }


    public void setSex(String sex) {
        this.sex = sex;
    }


    public float getRating() {
        return rating;
    }


    public void setRating(float rating) {
        this.rating = rating;
    }


    public String getCertification() {
        return certification;
    }


    public void setCertification(String certification) {
        this.certification = certification;
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Trainer getTrainer() {
        return trainer;
    }


    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
}

