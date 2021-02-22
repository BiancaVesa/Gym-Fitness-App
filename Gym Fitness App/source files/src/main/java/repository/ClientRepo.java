package repository;

import entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClientRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proiectFitnessApp");

    public void insertNewClient(Client client) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteClient(Client client) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.detach(client);
        em.getTransaction().commit();
        em.close();
    }

    public Client findClientById(String idClient) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Client client = em.find(Client.class, idClient);
        em.close();
        return client;
    }
}
