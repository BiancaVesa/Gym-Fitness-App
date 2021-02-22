package repository;

import entity.Rating;
import entity.Trainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RatingRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proiectFitnessApp");

    public void insertNewRating(Rating rating) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(rating);
        em.getTransaction().commit();
        em.close();
    }

    public List<Rating> findRatingsByGradeAndTrainer(float grade, Trainer trainer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Rating> ratings = em.createQuery("SELECT r FROM Rating r WHERE r.grade =: grade AND r.trainer =: trainer", Rating.class)
                .setParameter("grade", grade)
                .setParameter("trainer", trainer)
                .getResultList();
        em.close();
        return ratings;
    }

    public List<Rating> findRatingsByTrainer(Trainer trainer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Rating> ratings = em.createQuery("SELECT r FROM Rating r WHERE r.trainer =: trainer", Rating.class)
                .setParameter("trainer", trainer)
                .getResultList();
        em.close();
        return ratings;
    }

    public Rating findRatingsByIdClientAndTrainer(String idClient, Trainer trainer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Rating rating = em.createQuery("SELECT r FROM Rating r WHERE r.idClient =: idClient and r.trainer =: trainer", Rating.class)
                .setParameter("idClient", idClient)
                .setParameter("trainer", trainer)
                .getSingleResult();
        em.close();
        return rating;
    }
}
