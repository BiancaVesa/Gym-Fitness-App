package entity;

import javax.persistence.*;


@Entity
@Table(name = "booking")
public class Booking {

    @Id
    private String idBooking;

    @OneToOne
    @JoinColumn(name = "bookedTrainingId")
    private Schedule bookedTraining;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private Client client;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "membership_id")
    private Membership membership;


    public Booking() {
    }


    public Booking(String id) {
        this.idBooking = id;
    }


    public String getIdBooking() {
        return idBooking;
    }


    public void setIdBooking(String idBooking) {
        this.idBooking = idBooking;
    }


    public Schedule getBookedTraining() {
        return bookedTraining;
    }


    public void setBookedTraining(Schedule bookedTraining) {
        this.bookedTraining = bookedTraining;
    }


    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }


    public Membership getMembership() {
        return membership;
    }


    public void setMembership(Membership mem) {
        this.membership = mem;
    }
}
