package entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    private String idClient;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_profile")
    private Profile profile;

    @OneToOne(mappedBy = "client")
    private Membership membership;

    @OneToMany(mappedBy = "client", cascade = CascadeType.REMOVE)
    private List<Booking> bookings;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_credentials")
    private Login clientCredentials;



    public Client(String id) {
        this.idClient = id;
    }


    public Client() {
    }


    public void setClientCredentials(String userName, String password) {
        this.clientCredentials = new Login(this.getIdClient(), userName, password, "client");
    }


    public String getIdClient() {
        return idClient;
    }


    public void setIdClient(String idClient) {
        this.idClient = idClient;
    }


    public Profile getProfile() {
        return profile;
    }


    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    public Membership getMembership() {
        return membership;
    }


    public void setMembership(Membership membership) {
        this.membership = membership;
    }


    public List<Booking> getBookings() {
        return bookings;
    }


    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }


    public Login getClientCredentials() {
        return clientCredentials;
    }


    public void setClientCredentials(Login clientCredentials) {
        this.clientCredentials = clientCredentials;
    }


    public String getIdProfile() {
        return profile.getId();
    }


    public String getIdClientCredentials() {
        return clientCredentials.getIdCredentials();
    }

}
