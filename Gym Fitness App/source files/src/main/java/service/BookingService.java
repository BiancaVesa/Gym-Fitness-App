package service;

import entity.Booking;
import entity.Client;
import entity.Membership;
import entity.Schedule;
import repository.BookingRepo;

import java.util.List;
import java.util.UUID;

public class BookingService {

    private BookingRepo bookingRepo;

    public BookingService() {
        this.bookingRepo = new BookingRepo();
    }

    public void insertBooking(Client client, Schedule schedule, Membership membership) {
        Booking booking = new Booking(UUID.randomUUID().toString());
        booking.setBookedTraining(schedule);
        booking.setClient(client);
        booking.setMembership(membership);
        bookingRepo.insertNewBooking(booking);
    }

    public void deleteBooking(Booking booking) {
        bookingRepo.deleteBooking(booking);
    }

    public List<Booking> getBookingsByClientId(Client client) {
        return bookingRepo.findBookingsByIdClient(client);
    }
}
