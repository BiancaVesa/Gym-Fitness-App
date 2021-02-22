package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "membership")
public class Membership {

    @Id
    private String idMembership;

    @Column
    private LocalDate startDate;

    @Column
    private LocalDate expirationDate;

    @Column
    private String membershipType;

    @Column
    private int bookingsLeft;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private Client client;

    @OneToMany(mappedBy = "membership")
    private List<Booking> bookings;


    public Membership(String idMembership) {
        this.idMembership = idMembership;
    }


    public Membership() {
    }


    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }


    public String getIdMembership() {
        return idMembership;
    }


    public void setIdMembership(String idMembership) {
        this.idMembership = idMembership;
    }


    public LocalDate getStartDate() {
        return startDate;
    }


    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }


    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }


    public List<Booking> getBookings() {
        return bookings;
    }


    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }


    public String getMembershipType() {
        return membershipType;
    }


    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }


    public int getBookingsLeft() {
        return bookingsLeft;
    }


    public void setBookingsLeft(int bookingsLeft) {
        this.bookingsLeft = bookingsLeft;
    }
}
