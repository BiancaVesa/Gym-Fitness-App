package dto;

import java.time.LocalDateTime;

public class ScheduleDto {

    private String className;

    private LocalDateTime temp;

    private String trainerName;

    private float ratingTrainer;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public LocalDateTime getTemp() {
        return temp;
    }

    public void setTemp(LocalDateTime temp) {
        this.temp = temp;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String toString() {
        return "Class Name: " + className + ", Timestamp: " + temp + ", Trainer: " + trainerName;
    }

    public float getRatingTrainer() {
        return ratingTrainer;
    }

    public void setRatingTrainer(float ratingTrainer) {
        this.ratingTrainer = ratingTrainer;
    }
}
