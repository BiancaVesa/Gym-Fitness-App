package repository;

import entity.Login;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LoginRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proiectFitnessApp");

    public void insertNewLogin(Login login) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(login);
        em.getTransaction().commit();
        em.close();
    }

    public Login findLoginById(String idCredentials) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Login login = em.find(Login.class, idCredentials);
        em.close();
        return login;
    }

    public Login findByUsername(String username) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Login log = em.createQuery("SELECT l FROM Login l where l.userName =: usrNm", Login.class)
                .setParameter("usrNm", username)
                .getSingleResult();
        em.close();
        return log;
    }

    public void updateLoginUsername(Login login, String userName) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Login l SET l.userName =:userName WHERE l.idUser =:idUser")
                .setParameter("idUser", login.getIdUser()).setParameter("userName", userName)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updateLoginPassword(Login login, String password) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Login l SET l.password =:pass WHERE l.idUser =:idUser")
                .setParameter("idUser", login.getIdUser())
                .setParameter("pass", password)
                .executeUpdate();
        em.getTransaction().commit();
        em.close();
    }


    public Login getLoginPasswordByLogin(Login login) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Login resultedLogin = (Login) em.createQuery("SELECT l FROM Login l WHERE l.idCredentials =:idLogin", Login.class)
                .setParameter("idLogin", login.getIdCredentials()).getResultList().get(0);
        em.getTransaction().commit();
        em.close();
        return resultedLogin;
    }

    public Login getLoginUsernameByLogin(Login login) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Login resultedLogin = (Login) em.createQuery("SELECT l FROM Login l WHERE l.userName =:usrName", Login.class)
                .setParameter("usrName", login.getUserName())
                .getResultList().get(0);
        em.getTransaction().commit();
        em.close();
        return resultedLogin;
    }

    public Login getLoginUserByLoginCredentials(String username, String password) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Login resultedLogin = (Login) em.createQuery("SELECT l " +
                "FROM Login l WHERE l.userName =:usrName AND l.password =: passwrd", Login.class)
                .setParameter("usrName", username)
                .setParameter("passwrd", password)
                .getResultList().get(0);
        em.getTransaction().commit();
        em.close();

        return resultedLogin;
    }
}