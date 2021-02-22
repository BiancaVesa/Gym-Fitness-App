package controller;

import dto.TrainerDto;
import dto.TrainerProfileDto;
import service.LoginService;
import service.ProfileService;
import service.ScheduleService;
import service.TrainerService;
import start.ApplicationStart;
import utils.ControllerUtils;
import view.*;

import javax.swing.*;
import java.util.logging.Logger;

public class TrainerController {
    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());
    private final TrainerService trainerService = new TrainerService();
    private final ScheduleService scheduleService = new ScheduleService();
    private final LoginService loginService = new LoginService();

    public void viewProfile(JFrame trainerFrame, String idTrainer) {
        try {
            TrainerProfileView trainerProfileView = new TrainerProfileView(idTrainer);
            log.info("Trainer profile view initialized.");

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Error while trying to view trainer profile.");
        }
    }

    public void viewEditTrainerProfile(JFrame trainerFrame, String idTrainer) {
        try {
            EditTrainerProfileView editTrainerProfile = new EditTrainerProfileView(idTrainer);
            log.info("Edit trainer profile view initialized.");

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Error while trying to edit trainer profile.");
        }
    }

    public void viewAddSchedule(JFrame trainerFrame, String idTrainer) {
        try {
            AddScheduleView addScheduleView = new AddScheduleView();
            log.info("Add schedule view initialized.");

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Error while trying to add schedule.");
        }
    }

    public void viewEditSchedule(JFrame frame, String idTrainer) {
        try {
            EditScheduleView editScheduleView = new EditScheduleView(idTrainer);
            log.info("Edit schedule view initialized.");

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Error while trying to edit schedule.");
        }
    }

    public void addNewSchedule(String newClass, String newTemp, String idTrainer) {
        try {
            scheduleService.insertScheduleFromGUI(newClass, newTemp, idTrainer);
            ControllerUtils.createSwingSuccessMessage("Schedule added successfully!");
            log.info("Schedule added successfully!");
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Error while trying to add schedule.");
        }
    }

    public String[][] getTrainerProfileData(String idTrainer) {
        String[][] data = new String[1][4];
        TrainerProfileDto trainerProfileDto = trainerService.viewProfile(idTrainer);
        data[0][0] = idTrainer;
        data[0][1] = trainerProfileDto.getName();
        data[0][2] = trainerProfileDto.getCertification();
        data[0][3] = String.valueOf(trainerProfileDto.getRating());

        return data;
    }

    public String[] getTrainerProfileColumn() {
        return new String[]{"Id", "Name", "Certification", "Rating"};
    }

    public void executeUpdatesNameAndCertification(String idTrainer, String newName, String
            newCertification, String newUserName, String newPass, String reNewPass, String oldPass) {
        try {
            if (!newName.equals("")) {
                trainerService.modifyNameTrainer(idTrainer, newName);
                ControllerUtils.createSwingSuccessMessage("Name change successfully!");
                log.info("Name change successfully!");
            }
            if (!newCertification.equals("")) {
                trainerService.modifyCertification(idTrainer, newCertification);
                ControllerUtils.createSwingSuccessMessage("Certification change successfully!");
                log.info("Certification change successfully!");
            }
            if (!newUserName.equals("")) {
                trainerService.modifyUserName(idTrainer, newUserName);
                ControllerUtils.createSwingSuccessMessage("User name change successfully!");
                log.info("User name change successfully!");
            }
            if (!newPass.equals("") && !reNewPass.equals("") && !oldPass.equals("")) {
                if (!loginService.getLogin(trainerService.getTrainer(idTrainer).getTrainerIdCredentials()).getPassword().equals(oldPass)) {
                    ControllerUtils.createSwingErrorMessage("Wrong current password!");
                    log.severe("Error while trying to change password.");
                } else {
                    trainerService.modifyNewPass(idTrainer, newPass, reNewPass);
                    ControllerUtils.createSwingSuccessMessage("Password change successfully!");
                    log.info("Password change successfully!");
                }
            }
            if (newCertification.equals("") && newName.equals("") &&
                    newPass.equals("") && newUserName.equals("")) {
                ControllerUtils.createSwingErrorMessage("All fields empty!");
                log.severe("Error while trying to change user profile. Fields empty.");
            }
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
        }
    }

    public void goBack(JFrame frame) {
        frame.dispose();
        new LoginView().setVisible(true);
    }
}
