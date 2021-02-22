package controller;

import service.ClientService;
import start.ApplicationStart;
import utils.ControllerUtils;

import javax.swing.*;
import java.util.logging.Logger;

public class MembershipController {
    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());
    private ClientService clientService = new ClientService();

    public void createMembership(String idClient, String membershipType) {
        try {
            if (JOptionPane.showConfirmDialog(null, "Do you want the " + membershipType + " membership?", "Book a Class",
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                clientService.createMembership(idClient, membershipType);
                log.info("Membership create successfully!");
            }
        } catch (Exception e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Error while trying to create membership!");
        }
    }

}
