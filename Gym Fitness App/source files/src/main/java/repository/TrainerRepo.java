package repository;

import entity.Trainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TrainerRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proiectFitnessApp");

    public void insertNewTrainer(Trainer trainer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(trainer);
        em.getTransaction().commit();
        em.close();
    }

    public Trainer findTrainerById(String idTrainer) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Trainer trainer = em.find(Trainer.class, idTrainer);
        em.close();
        return trainer;
    }
}
