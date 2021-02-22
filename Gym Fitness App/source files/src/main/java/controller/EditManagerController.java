package controller;

import repository.ManagerRepo;
import repository.TrainerRepo;
import service.ManagerService;
import utils.ControllerUtils;
import validator.ManagerValidator;

public class EditManagerController {

    private final ManagerService managerService = new ManagerService(new ManagerRepo(), new TrainerRepo());

    public void executeUpdatesNamePhoneOrEmail(String name, String phoneNr, String email) {

        System.out.println(name + "si" + "si" + email);

        try {
            if (!name.equals("")) {
                managerService.modifyName(name);
                ControllerUtils.createSwingSuccessMessage("Name changed!");
            }
            if (!phoneNr.equals("")) {
                ManagerValidator.validatePhoneNr(phoneNr);
                managerService.modifyPhoneNr(phoneNr);
                ControllerUtils.createSwingSuccessMessage("Phone number changed!");
            }
            if (!email.equals("")) {
                ManagerValidator.emailValidator(email);
                managerService.modifyEmail(email);
                ControllerUtils.createSwingSuccessMessage("Email changed!");
            }

            if (email.equals("") && name.equals("") && phoneNr.equals("")) {
                ControllerUtils.createSwingErrorMessage("All fields empty!");
            }
        } catch (IllegalArgumentException e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
        }
    }
}
