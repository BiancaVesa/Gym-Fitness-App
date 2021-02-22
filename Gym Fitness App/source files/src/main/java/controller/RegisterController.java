package controller;

import entity.Client;
import entity.Profile;
import exception.CustomExceptionMessages;
import service.EmailService;
import service.LoginService;
import utils.ControllerUtils;
import utils.EmailContents;
import utils.EmailSubjects;
import validator.LoginValidator;
import validator.ProfileValidator;
import view.LoginView;
import view.RegisterView;

import java.util.UUID;

public class RegisterController {
    private static final LoginService loginService = new LoginService();
    private static final EmailService emailService = new EmailService();
    public static RegisterView view;

    private Client toBeRegistered = new Client();
    private Profile clientProfile = new Profile();

    public void registerUser(String firstName, String lastName, String birthDay, String sex,
                             String userName, char[] password, char[] confirmationPassword) {
        try {
            if (!LoginValidator.validatePasswords(String.valueOf(password), String.valueOf(confirmationPassword))) {
                ControllerUtils.createSwingErrorMessage(
                        CustomExceptionMessages.INVALID_CREDENTIALS);
            }

            toBeRegistered.setIdClient(UUID.randomUUID().toString());
            toBeRegistered.setClientCredentials(userName, String.valueOf(password));

            clientProfile.setName(firstName + " " + lastName);
            clientProfile.setBirthday(birthDay);
            clientProfile.setSex(sex);
            clientProfile.setType("client");
            clientProfile.setId(UUID.randomUUID().toString());

            ProfileValidator.validateProfile(clientProfile);
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(CustomExceptionMessages.INVALID_CREDENTIALS);
        }
        if (loginService.registerUser(toBeRegistered, clientProfile) == null) {
            ControllerUtils.createSwingErrorMessage(CustomExceptionMessages.INVALID_CREDENTIALS);
        }
        done();
    }

    private void done() {
        ControllerUtils.createSwingSuccessMessage("Registration done!");

        emailService.sendMail("fitness-app-gym@yandex.com", EmailSubjects.REGISTER_EMAIL_SUBJEECT,
                EmailContents.generateRegistrationEmail(this.clientProfile));

        view.dispose();
        new LoginView().setVisible(true);
    }
}
