package repository;

import entity.Manager;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManagerRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proiectFitnessApp");


    public void insertManager(Manager manager) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(manager);
        em.getTransaction().commit();
        em.close();
    }

    public void updateManagerName(String name, Manager manager) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Manager m SET m.name =:nm WHERE m.idManager =:id")
                .setParameter("id", manager.getIdManager()).setParameter("nm", name).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updateManagerPhone(String phone, Manager manager) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Manager m SET m.phoneNr =:nr WHERE m.idManager =:id")
                .setParameter("id", manager.getIdManager()).setParameter("nr", phone).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updateManagerEmail(String email, Manager manager) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Manager m SET m.email =:mail WHERE m.idManager =:id")
                .setParameter("id", manager.getIdManager()).setParameter("mail", email).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }
}
