package repository;

import entity.Profile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProfileRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proiectFitnessApp");

    public void insertNewProfile(Profile profile) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(profile);
        em.getTransaction().commit();
        em.close();
    }

    public Profile findProfileById(String idProfile) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Profile profile = em.find(Profile.class, idProfile);
        em.close();
        return profile;
    }

    public void updateProfileName(String name, Profile profile) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Profile p SET p.name =:name WHERE p.id =:id")
                .setParameter("id", profile.getId())
                .setParameter("name", name)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updateProfileCertification(String certification, Profile profile) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Profile p SET p.certification =:certification WHERE p.id =:id")
                .setParameter("id", profile.getId())
                .setParameter("certification", certification)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updateProfileBirthday(String birthday, Profile profile) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Profile p SET p.birthday =:birthday WHERE p.id =:id")
                .setParameter("id", profile.getId())
                .setParameter("birthday", birthday)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updateProfileRating(float rating, Profile profile) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Profile p SET p.rating =:rating WHERE p.id =:id")
                .setParameter("id", profile.getId())
                .setParameter("rating", rating)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updateProfileProgress(int progress, Profile profile) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Profile p SET p.Progress =:progress WHERE p.id =:id")
                .setParameter("id", profile.getId())
                .setParameter("progress", progress)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

}
