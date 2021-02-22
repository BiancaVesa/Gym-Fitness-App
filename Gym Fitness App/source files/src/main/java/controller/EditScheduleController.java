package controller;

import entity.Schedule;
import repository.ManagerRepo;
import repository.ScheduleRepo;
import repository.TrainerRepo;
import service.ManagerService;
import start.ApplicationStart;
import utils.ControllerUtils;

import java.time.LocalDateTime;
import java.util.logging.Logger;

public class EditScheduleController {
    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());
    private final ManagerService managerService = new ManagerService(new ManagerRepo(), new TrainerRepo());
    private final ScheduleRepo scheduleRepo = new ScheduleRepo();

    public LocalDateTime executeUpdateForSchedule(int newDay, int newMonth, int newYear, int newHour, int newMinute, LocalDateTime selectedDate) {
        try {
            if (!selectedDate.toString().equals("") && (newMinute >= 0 && newMinute <= 59)) {
                //caut clasa pt care se doreste modificarea
                LocalDateTime localDateTime = LocalDateTime.of(newYear, newMonth, newDay, newHour, newMinute);
                System.out.println(localDateTime);

                Schedule sch = scheduleRepo.findScheduleByDate1(selectedDate); //obtinem idClasa selectata dupa data selectata(ea este unica)
                managerService.modifyScheduleInfo(sch.getIdClass(), localDateTime);
                ControllerUtils.createSwingSuccessMessage("Schedule change successfully");
                log.info("Schedule change successfully");
                return localDateTime;
            } else {
                ControllerUtils.createSwingErrorMessage("Invalid Data");
                log.severe("Error while trying to change schedule.");
            }
        } catch (NullPointerException e) {
            ControllerUtils.createSwingErrorMessage("Invalid Data");
            log.severe("Error while trying to change schedule.");
        } catch (IllegalArgumentException e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Error while trying to change schedule.");
        } catch (ArrayIndexOutOfBoundsException e) {
            ControllerUtils.createSwingErrorMessage("No Class selected");
            log.severe("Error while trying to change schedule. No schedule selected.");
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage("There isn't that day in this month!");
        }
        return null;
    }
}
