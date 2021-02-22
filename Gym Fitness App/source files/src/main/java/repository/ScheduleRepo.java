package repository;


import entity.Schedule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ScheduleRepo {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("proiectFitnessApp");

    public void insertNewSchedule(Schedule schedule) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(schedule);
        em.getTransaction().commit();
        em.close();
    }

    public Schedule findScheduleById(String idSchedule) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Schedule schedule = em.find(Schedule.class, idSchedule);
        em.close();
        return schedule;
    }

    public void deleteSchedule(Schedule schedule) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.detach(schedule);
        em.getTransaction().commit();
        em.close();
    }

    public void updateScheduleClassName(String className, Schedule schedule) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Schedule s SET s.ClassName =:className WHERE s.idClass =:id")
                .setParameter("id", schedule.getIdClass()).setParameter("className", className).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void updateScheduleTemp(LocalDateTime temp, Schedule schedule) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Schedule s SET s.Temp =:temp WHERE s.idClass =:id")
                .setParameter("id", schedule.getIdClass()).setParameter("temp", temp).executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public List<Schedule> findAllSchedule() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Schedule> schedules = em.createQuery("SELECT s FROM Schedule s ", Schedule.class)
                .getResultList();
        em.close();
        return schedules;
    }

    public List<Schedule> findScheduleByDate() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Schedule> schedules = em.createQuery("SELECT s FROM Schedule s WHERE s.Temp > NOW()", Schedule.class)
                .getResultList();
        em.close();
        return schedules;
    }

    public Schedule findScheduleByDate1(LocalDateTime date) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Schedule schedule = em.createQuery("SELECT s FROM Schedule s where s.Temp =: tmp", Schedule.class)
                .setParameter("tmp", date)
                .getSingleResult();
        em.close();
        return schedule;

    }

    public List<Schedule> findScheduleBetweenDates(LocalDateTime date) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        List<Schedule> schedules = em.createQuery("SELECT s FROM Schedule s WHERE s.Temp >: strtDate and s.Temp <: endDate", Schedule.class)
                .setParameter("strtDate", date.minusHours(1))
                .setParameter("endDate", date.plusHours(1))
                .getResultList();
        em.close();
        return schedules;

    }
}
