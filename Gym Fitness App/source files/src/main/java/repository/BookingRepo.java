package repository;

import entity.Booking;
import entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class BookingRepo {
    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proiectFitnessApp");


    public void insertNewBooking(Booking booking) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(booking);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteBooking(Booking booking) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Booking bookingToDelete = em.find(Booking.class, booking.getIdBooking());
        em.remove(bookingToDelete);
        em.getTransaction().commit();
        em.close();
    }

    public List<Booking> findAllBookings() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Booking> bookings = em.createQuery("SELECT b FROM Booking b ", Booking.class).getResultList();
        em.close();
        return bookings;
    }

    public List<Booking> findBookingsByIdClient(Client client) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Booking> bookings = em.createQuery("SELECT b FROM Booking b WHERE b.client =: client ", Booking.class).setParameter("client", client).getResultList();
        em.close();
        return bookings;
    }

}
