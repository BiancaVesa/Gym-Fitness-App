package controller;

import entity.Login;
import exception.CustomExceptionMessages;
import repository.LoginRepo;
import repository.ManagerRepo;
import repository.TrainerRepo;
import service.LoginService;
import service.ManagerService;
import start.ApplicationStart;
import utils.ControllerUtils;
import view.*;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Logger;

public class LoginController {

    private final LoginService loginService = new LoginService();
    private final LoginRepo loginRepo = new LoginRepo();
    public static LoginView view;
    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());

    public static void startUserRegistration() {
        new RegisterView();
    }

    public static void textFieldMouseCallback(JTextField textField) {
        textField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


    public void login(String username, String password) {
        try {
            if (loginService.existentCredentialsFound(username, password)) {
                Login loginAction = loginRepo.getLoginUserByLoginCredentials(username, password);

                switch (loginAction.getType()) {
                    case "client":
                        new ClientView(loginAction.getIdUser());
                        break;
                    case "trainer":
                        new TrainerView(loginAction.getIdUser());
                        break;
                    case "manager":
                        new ManagerView();
                        break;
                }
                view.dispose();
            }
            else throw new IllegalArgumentException(CustomExceptionMessages.INVALID_CREDENTIALS);
            log.info("Login successfully!");
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(CustomExceptionMessages.INVALID_USER);
            log.severe("Error. Invalid username or password!");
        }
    }

    public static void run()
    {
        ManagerService managerService = new ManagerService(new ManagerRepo(), new TrainerRepo());
        managerService.createManager();
    }
}