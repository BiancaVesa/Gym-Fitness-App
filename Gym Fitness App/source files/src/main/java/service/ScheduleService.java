package service;

import dto.ScheduleDto;
import entity.Schedule;
import exception.CustomExceptionMessages;
import mappers.ScheduleMapper;
import repository.ScheduleRepo;
import validator.DateFormatValidator;
import validator.ScheduleValidator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ScheduleService {

    private ScheduleRepo scheduleRepo;
    private DateFormatValidator dateFormatValidator;
    private TrainerService trainerService;
    private DateTimeFormatter formatter;

    public ScheduleService() {
        this.scheduleRepo = new ScheduleRepo();
        this.trainerService = new TrainerService();
        this.dateFormatValidator = new DateFormatValidator("yyyy-MM-dd HH:mm");
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    public ScheduleDto getSchedule(String idSchedule) {

        ScheduleValidator.validateScheduleById(idSchedule);

        Schedule schedule = scheduleRepo.findScheduleById(idSchedule);
        ScheduleValidator.validateSchedule(schedule);

        return ScheduleMapper.entityToDto(schedule);
    }

    public Schedule getScheduleNotDTO(String idSchedule) {

        ScheduleValidator.validateScheduleById(idSchedule);

        Schedule schedule = scheduleRepo.findScheduleById(idSchedule);
        ScheduleValidator.validateSchedule(schedule);

        return schedule;
    }

    public void insertScheduleFromGUI(String newClass, String newTemp, String idTrainer) {
        if (!dateFormatValidator.isValid(newTemp)) {
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_DATE_FORMAT);
        }
        LocalDateTime dateTime = LocalDateTime.parse(newTemp, formatter);
        Schedule schedule = new Schedule();
        schedule.setIdClass(UUID.randomUUID().toString());
        schedule.setClassName(newClass);
        schedule.setTemp(dateTime);

        if(trainerService.getTrainer(idTrainer) == null)
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_TRAINER);
        else
            schedule.setTrainer(trainerService.getTrainer(idTrainer));
        this.insertSchedule(schedule, schedule.getTemp());

    }

    public void insertSchedule(Schedule schedule, LocalDateTime time) {
        ScheduleValidator.validateUpdateTemp(schedule, time);
        ScheduleValidator.validateOneHourInterval(time);
        ScheduleValidator.validateCertificationWithSchedule(schedule, schedule.getClassName());
        scheduleRepo.insertNewSchedule(schedule);
    }

    public void updateScheduleClass(Schedule schedule, String className) {
        ScheduleValidator.validateScheduleById(schedule.getIdClass());
        ScheduleValidator.validateUpdateClassName(schedule, className);
        scheduleRepo.updateScheduleClassName(className, schedule);
    }

    public void updateScheduleProgram(Schedule schedule, LocalDateTime temp) {
        ScheduleValidator.validateScheduleById(schedule.getIdClass());
        ScheduleValidator.validateUpdateTemp(schedule, temp);
        scheduleRepo.updateScheduleTemp(temp, schedule);
    }

    public void delSchedule(Schedule schedule) {
        ScheduleValidator.validateScheduleById(schedule.getIdClass());
        ScheduleValidator.validateExistingId(schedule);
        scheduleRepo.deleteSchedule(schedule);
    }

    public List<Schedule> getScheduleByDate() {
        return scheduleRepo.findScheduleByDate();
    }


}
