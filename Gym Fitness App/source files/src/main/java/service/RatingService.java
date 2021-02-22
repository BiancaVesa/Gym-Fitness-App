package service;

import entity.Rating;
import entity.Trainer;
import repository.RatingRepo;

import java.util.UUID;

public class RatingService {
    private RatingRepo ratingRepo;

    public RatingService() {
        this.ratingRepo = new RatingRepo();
    }

    public void insertRating(float grade, String idClient, Trainer trainer) {
        Rating rating = new Rating(UUID.randomUUID().toString());
        rating.setGrade(grade);
        rating.setIdClient(idClient);
        rating.setTrainer(trainer);
        ratingRepo.insertNewRating(rating);
    }

    public int countTrainerRatingsByGrade(float grade, Trainer trainer) {
        return ratingRepo.findRatingsByGradeAndTrainer(grade, trainer).size();
    }

    public int countTrainerRatings(Trainer trainer) {
        return ratingRepo.findRatingsByTrainer(trainer).size();
    }

    public Rating getRatingOfClientToTrainer(String idClient, Trainer trainer) {
        return ratingRepo.findRatingsByIdClientAndTrainer(idClient, trainer);
    }
}
