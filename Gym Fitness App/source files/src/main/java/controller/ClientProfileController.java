package controller;

import dto.ClientProfileDto;
import service.ClientService;
import start.ApplicationStart;
import utils.ControllerUtils;
import view.ClientProfileView;

import javax.swing.*;
import java.util.logging.Logger;

public class ClientProfileController {
    private final ClientService clientService = new ClientService();
    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());

    public ClientProfileDto viewProfileDetails(String idClient){
        return clientService.viewProfile(idClient);
    }

    public void editName(ClientProfileView clientProfileView, JFrame jFrame, String idClient){
        JTextField newName = new JTextField();
        int action = JOptionPane.showConfirmDialog(jFrame, newName, "Type your name:", JOptionPane.OK_CANCEL_OPTION);
        if (action == JOptionPane.OK_OPTION) {
            try {
                clientService.modifyName(idClient, newName.getText());
                clientProfileView.updateName(newName.getText());
                log.info("Named changed successfully");
            } catch (IllegalArgumentException e) {
                ControllerUtils.createSwingErrorMessage(e.getMessage());
                log.severe("Error while trying to change the name.");

            }
        }
    }

    public void editBirthday(ClientProfileView clientProfileView, JFrame jFrame, String idClient){
        JTextField newBirthday = new JTextField();
        int action = JOptionPane.showConfirmDialog(jFrame, newBirthday, "Type your birthday:", JOptionPane.OK_CANCEL_OPTION);
        if (action == JOptionPane.OK_OPTION) {

            try {
                clientService.modifyBirthday(idClient, newBirthday.getText());
                clientProfileView.updateBirthday(newBirthday.getText());
                log.info("Birthday changed successfully");
            } catch (Exception e) {
                ControllerUtils.createSwingErrorMessage(e.getMessage());
                log.severe("Error while trying to change the birthday.");

            }
        }
    }
}
