package repository;

import entity.Client;
import entity.Membership;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;

public class MembershipRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proiectFitnessApp");


    public void insertNewMembership(Membership membership) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(membership);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteMembership(Membership membership) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.detach(membership);
        em.getTransaction().commit();
        em.close();
    }

    public List<Membership> findMembershipsByIdClient(Client client) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Membership> memberships = em.createQuery("SELECT m FROM Membership m WHERE m.client =: client ", Membership.class)
                .setParameter("client", client).getResultList();
        em.close();
        return memberships;
    }

    public void updateMembershipNrOfBookings(Membership membership, int bookingsLeft) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Membership m SET m.bookingsLeft =: bookingsLeft WHERE m.idMembership =:idMembership")
                .setParameter("idMembership", membership.getIdMembership())
                .setParameter("bookingsLeft", bookingsLeft)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public List<Membership> findMembershipsByDate(LocalDate dateTime) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Membership> memberships = em.createQuery("SELECT m FROM Membership m WHERE m.startDate >=: strtDate and m.startDate <: endDate", Membership.class)
                .setParameter("strtDate", dateTime)
                .setParameter("endDate", dateTime.plusMonths(1))
                .getResultList();
        em.close();
        return memberships;
    }
}
