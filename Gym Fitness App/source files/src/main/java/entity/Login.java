package entity;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "login",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"userName"})
        })
public class Login {

    @Id
    private String idCredentials;

    @Column(unique = true)
    private String idUser;

    @Column
    private String type;

    @Column
    private String userName;

    @Column
    private String password;

    @OneToOne(mappedBy = "clientCredentials")
    private Client client;

    @OneToOne(mappedBy = "trainerCredentials")
    private Trainer trainer;

    @OneToOne(mappedBy = "managerCredentials")
    private Manager manager;


    public Login() {
    }

    ;


    public Login(String userId, String username, String password, String type) {
        this.setCredentials(username, password);
        this.setIdCredentials(UUID.randomUUID().toString());
        this.setIdUser(userId);
        this.setType(type);
    }


    public String getIdCredentials() {
        return idCredentials;
    }

    private void setIdCredentials(String idCredentials) {
        this.idCredentials = idCredentials;
    }


    public String getIdUser() {
        return idUser;
    }

    private void setIdUser(String idUser) {
        this.idUser = idUser;
    }


    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String pass) {
        this.password = pass;
    }

    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public Trainer getTrainer() {
        return trainer;
    }


    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }


    public Manager getManager() {
        return manager;
    }


    public void setManager(Manager manager) {
        this.manager = manager;
    }

    private void setCredentials(String userName, String password) {
        setUserName(userName);
        setPassword(password);
    }
}
