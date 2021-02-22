package view;

import controller.TrainerController;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;

public class EditTrainerProfileView extends JFrame {
    private JFrame frame;
    private final TrainerController trainerController;
    
    private JLabel newNameLabel;
    private JLabel newCertificationLabel;
    private JLabel newUserNameLabel;
    private JLabel newPasswordLabel;
    private JLabel reNewPasswordLabel;
    private JLabel oldPasswordLabel;

    private JTextField nameField;
    private JTextField certificationField;
    private JTextField userNameField;
    
    private JPasswordField passwordField;
    private JPasswordField rePassField;
    private JPasswordField oldPasswordField;


    private JButton okButton;
    private final String idTrainer;

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public EditTrainerProfileView(String idTrainer){
        trainerController = new TrainerController();

        this.idTrainer = idTrainer;

        frame = new JFrame("Edit profile");

        initialize();
        
        setDesignFrame();

        frame.setVisible(true);
    }

    private void initialize() {
        frame.setSize(500,500);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        newNameLabel = new JLabel("New name: ");
        newNameLabel.setBounds(120, 95, 80, 20);

        newCertificationLabel = new JLabel("New certification: ");
        newCertificationLabel.setBounds(120, 130, 120, 20);

        newUserNameLabel = new JLabel("New User name: ");
        newUserNameLabel.setBounds(120,165,120,20);

        newPasswordLabel = new JLabel("New password: ");
        newPasswordLabel.setBounds(120,235,120,20);

        reNewPasswordLabel = new JLabel("Confirm password: ");
        reNewPasswordLabel.setBounds(120,270,120,20);

        oldPasswordLabel = new JLabel("Current password: ");
        oldPasswordLabel.setBounds(120,200,120,20);


        nameField = new JTextField();
        nameField.setBounds(240, 95, 140,20);
        certificationField = new JTextField();
        certificationField.setBounds(240,130,140,20);
        userNameField = new JTextField();
        userNameField.setBounds(240,165,140,20);
        passwordField = new JPasswordField();
        passwordField.setBounds(240,235,140,20);
        rePassField = new JPasswordField();
        rePassField.setBounds(240,270,140,20);
        oldPasswordField =new JPasswordField();
        oldPasswordField.setBounds(240,200,140,20);
        okButton = new JButton("Save changes");
        okButton.setBounds(165,315,150,20);

        addItemsToFrame();

        okButton.addActionListener(e-> trainerController.executeUpdatesNameAndCertification(idTrainer,
                nameField.getText(), certificationField.getText(), userNameField.getText(), String.valueOf(passwordField.getPassword()), String.valueOf(rePassField.getPassword()),
                String.valueOf(oldPasswordField.getPassword())));

        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

    }

    private void addItemsToFrame() {
        frame.getContentPane().add(newNameLabel)
        .getParent().add(newCertificationLabel)
        .getParent().add(nameField)
        .getParent().add(certificationField)
        .getParent().add(newUserNameLabel)
        .getParent().add(newPasswordLabel)
        .getParent().add(userNameField)
        .getParent().add(passwordField)
        .getParent().add(reNewPasswordLabel)
        .getParent().add(rePassField)
        .getParent().add(oldPasswordLabel)
        .getParent().add(oldPasswordField)
        .getParent().add(okButton);
    }

    private void setDesignFrame(){
        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        newNameLabel.setForeground(FrameDesign.CREAM);
        newNameLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        newCertificationLabel.setForeground(FrameDesign.CREAM);
        newCertificationLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        newUserNameLabel.setForeground(FrameDesign.CREAM);
        newUserNameLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        newPasswordLabel.setForeground(FrameDesign.CREAM);
        newPasswordLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        reNewPasswordLabel.setForeground(FrameDesign.CREAM);
        reNewPasswordLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        oldPasswordLabel.setForeground(FrameDesign.CREAM);
        oldPasswordLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        nameField.setBackground(FrameDesign.GREY);
        certificationField.setBackground(FrameDesign.GREY);
        userNameField.setBackground(FrameDesign.GREY);
        passwordField.setBackground(FrameDesign.GREY);
        rePassField.setBackground(FrameDesign.GREY);
        reNewPasswordLabel.setBackground(FrameDesign.GREY);

        okButton.setBackground(FrameDesign.LIGHT_BLUE);
        okButton.setFont(FrameDesign.CALIBRI_BOLD_14);


    }

}
