package entity;

import javax.persistence.*;

@Entity
@Table(name = "manager")
public class Manager {
    @Id
    private String idManager;

    @Column
    private String name;

    @Column
    private String phoneNr;

    @Column
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_credentials")
    private Login managerCredentials;


    public Manager() {
    }

    public void setManagerCredentials(String userName, String password) {
        this.managerCredentials = new Login(this.getIdManager(), userName, password, "manager");
    }

    public Login getManagerCredentials() {
        return managerCredentials;
    }

    public void setManagerCredentials(Login managerCredentials) {
        this.managerCredentials = managerCredentials;
    }

    public String getIdManager() {
        return idManager;
    }


    public void setIdManager(String idManager) {
        this.idManager = idManager;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPhoneNr() {
        return phoneNr;
    }


    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
