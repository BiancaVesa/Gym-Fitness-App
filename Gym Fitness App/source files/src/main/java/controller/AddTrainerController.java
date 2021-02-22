package controller;

import repository.ManagerRepo;
import repository.TrainerRepo;
import service.ManagerService;
import utils.ControllerUtils;

public class AddTrainerController {

    private final ManagerService managerService = new ManagerService(new ManagerRepo(), new TrainerRepo());

    public void executeUpdatesNameAndCertification(String name, String birthday, String sex, String certification, String userName, String password) {
        System.out.println(name + birthday + sex + certification + userName + password);

        try {
            if (!name.equals("") && !birthday.equals("") && !sex.equals("") && !certification.equals("") && !userName.equals("") && !password.equals("")) {
                managerService.createTrainer(name, birthday, sex, certification, userName, password);
                ControllerUtils.createSwingSuccessMessage("Trainer succesfully created!");
            } else {
                ControllerUtils.createSwingErrorMessage("Date invalide");
            }
        } catch (NullPointerException e) {
            ControllerUtils.createSwingErrorMessage("Date invalide");
        } catch (IllegalArgumentException e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
        }
    }
}