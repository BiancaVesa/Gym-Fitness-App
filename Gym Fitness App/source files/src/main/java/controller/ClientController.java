package controller;

import service.ClientService;
import service.LoginService;
import start.ApplicationStart;
import utils.ControllerUtils;
import view.*;

import javax.swing.*;
import java.util.logging.Logger;

public class ClientController {
    private ClientService clientService = new ClientService();
    private LoginService loginService = new LoginService();
    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());


    public void viewProfile(String idClient) {
        try {
            ClientProfileView clientProfileView = new ClientProfileView(idClient);
            log.info("Client profile initialised.");
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Something went wrong at profile view.");
        }
    }

    public void viewAllMyBookings(String idClient) {
        try {
            AllBookingsView allBookingsView = new AllBookingsView(idClient);
            log.info("Client bookings  view initialised.");
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Something went wrong at bookings view.");
        }
    }

    public void createMembership(String idClient) {
        try {
            MembershipView membershipView = new MembershipView(idClient);
            log.info("Membership view initialised.");
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Something went wrong at membership view.");
        }
    }

    public void createBooking(String idClient) {
        try {
            CreateBookingView createBookingView = new CreateBookingView(idClient);
            log.info("Create booking view initialised.");

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Something went wrong at crate booking view.");

        }
    }

    public void rateTrainer(String idClient) {
        try {
            RateTrainerView rateTrainerView = new RateTrainerView(idClient);
            log.info("Rate trainer view initialised.");

        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Something went wrong at rate trainer view.");

        }
    }

    public void changeUserName(JFrame jFrame, String idClient) {
        String idCredentials = clientService.getClient(idClient).getIdClientCredentials();
        JTextField newUserName = new JTextField();
        int action = JOptionPane.showConfirmDialog(jFrame, newUserName, "Enter new username:", JOptionPane.OK_CANCEL_OPTION);
        if (action == JOptionPane.OK_OPTION) {
            try {
                loginService.modifyUserName(idCredentials, newUserName.getText());
                log.info("User name modified successfully.");
            } catch (Exception e) {
                ControllerUtils.createSwingErrorMessage(e.getMessage());
                log.severe("Something went wrong while trying to change user name.");

            }
        }
    }


    public void changePassword(JFrame jFrame, String idClient) {
        String idCredentials = clientService.getClient(idClient).getIdClientCredentials();
        JPasswordField newPassword = new JPasswordField();
        JPasswordField currentPassword = new JPasswordField();
        int actionCurrent = JOptionPane.showConfirmDialog(jFrame, currentPassword, "Enter current password:", JOptionPane.OK_CANCEL_OPTION);
        if (actionCurrent == JOptionPane.OK_OPTION) {
            if (loginService.getLogin(clientService.getClient(idClient).getIdClientCredentials()).getPassword().equals(new String(currentPassword.getPassword()))) {
                int actionNew = JOptionPane.showConfirmDialog(jFrame, newPassword, "Enter new password:", JOptionPane.OK_CANCEL_OPTION);
                if (actionNew == JOptionPane.OK_OPTION) {
                    try {
                        loginService.modifyPassword(idCredentials, new String(newPassword.getPassword()));
                        log.info("Password modified successfully.");
                    } catch (Exception e) {
                        ControllerUtils.createSwingErrorMessage(e.getMessage());
                        log.severe("Something went wrong while trying to change password.");

                    }
                }
            } else {
                ControllerUtils.createSwingErrorMessage("Current password is invalid");
                log.warning("Current password is invalid.");
            }
        }
    }

}
