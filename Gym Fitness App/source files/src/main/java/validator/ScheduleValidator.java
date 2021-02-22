package validator;


import entity.Schedule;
import exception.CustomExceptionMessages;
import exception.EntityNotExistsException;
import repository.ScheduleRepo;

import java.time.LocalDateTime;
import java.util.List;


public class ScheduleValidator {

    public static void validateScheduleById(String idSchedule) {
        if (idSchedule == null || idSchedule.equals("")) {
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_ID_MESSAGE);
        }
    }

    public static void validateSchedule(Schedule schedule) {
        if (schedule == null) {
            throw new EntityNotExistsException("No schedule found.");
        }
    }

    public static void validateUpdateClassName(Schedule schedule, String nameClass) {

        if (schedule == null || schedule.getIdClass() == null || schedule.getIdClass().equals("")) {
            throw new IllegalArgumentException("Schedule is null or it has an empty name.");
        }

        if (nameClass == null || nameClass.length() < 2) {
            throw new IllegalArgumentException("Schedule is null or its length is smaller than 2.");
        }

    }

    public static void validateUpdateTemp(Schedule schedule, LocalDateTime timeStamp) {

        if (schedule == null || schedule.getIdClass() == null || schedule.getIdClass().equals("")) {
            throw new IllegalArgumentException("Schedule is null or it has an empty name.");
        }

        if (timeStamp.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Time invalid.");
        }

    }

    //trebuie sa existe un id pentru actualul schedule incat sa fie sters
    public static void validateExistingId(Schedule schedule) {
        if (schedule == null || schedule.getIdClass() == null || schedule.getIdClass().equals("")) {
            throw new IllegalArgumentException("Schedule is null or it has an empty name.");
        }
        ScheduleRepo scheduleRepo = new ScheduleRepo();
        int ok = 0;
        List<Schedule> schedules = scheduleRepo.findAllSchedule();
        for (Schedule scheduleBuff : schedules) {
            if (scheduleBuff.getIdClass().equals(schedule.getIdClass())) {
                ok++;
                break;
            }
        }
        if (ok == 0)
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_ID_SCHEDULE);

    }

    public static void validateExistingTemp(LocalDateTime timeStamp) {
        ScheduleRepo scheduleRepo = new ScheduleRepo();
        Schedule orarOcupat = scheduleRepo.findScheduleByDate1(timeStamp);

        if (orarOcupat != null) {
            throw new IllegalArgumentException(CustomExceptionMessages.BUSY_DATE);
        }

    }

    public static void validateOneHourInterval(LocalDateTime timeStamp) {

        ScheduleRepo scheduleRepo = new ScheduleRepo();
        List<Schedule> orarOcupat = scheduleRepo.findScheduleBetweenDates(timeStamp);
        System.out.println("Orarul este ocupat " + orarOcupat);
        if (orarOcupat != null && !orarOcupat.isEmpty()) //avem deja ocupat programul la +- o ora de la noua data
        {
            throw new IllegalArgumentException(CustomExceptionMessages.ALREADY_CLASS);
        }

    }

    public static void validateCertificationWithSchedule(Schedule schedule, String nameSchedule) {
        if (!schedule.getTrainer().getProfile().getCertification().equalsIgnoreCase(nameSchedule)) {
            throw new IllegalArgumentException(CustomExceptionMessages.CERTIFICATION_INVALID);
        }
    }

}
