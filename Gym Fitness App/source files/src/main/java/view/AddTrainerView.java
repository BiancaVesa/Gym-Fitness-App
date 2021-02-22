package view;

import controller.AddTrainerController;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class AddTrainerView {

    private final AddTrainerController addTrainerController;

    public JFrame frame;

    private JButton registerButton = new JButton("Register");

    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> sexComboBox;

    private JTextField nameLabelField;
    private JTextField certificationLabelField;
    private JTextField userNameField;

    private JPasswordField passwordField;

    private JLabel nameLabel;
    private JLabel birthdayLabel;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;
    private JLabel sexLabel;
    private JLabel certificationLabel;
    private JLabel userNameLabel;
    private JLabel passwordLabel;

    public AddTrainerView() {
        addTrainerController = new AddTrainerController();

        frame = new JFrame("Add New Trainer");

        initialize();

        setFrameDesign();

        frame.setVisible(true);
    }

    private void initialize() {
        frame.setResizable(false);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(50, 88, 98, 22);

        birthdayLabel = new JLabel("Birthday:");
        birthdayLabel.setBounds(50, 140, 74, 22);

        dayLabel = new JLabel("Day");
        dayLabel.setBounds(50, 172, 50, 22);

        monthLabel = new JLabel("Month");
        monthLabel.setBounds(210, 172, 50, 22);

        yearLabel = new JLabel("Year");
        yearLabel.setBounds(354, 172, 50, 22);

        sexLabel = new JLabel("Sex");
        sexLabel.setBounds(50, 224, 50, 22);

        certificationLabel = new JLabel("Certification");
        certificationLabel.setBounds(50, 276, 100, 22);

        userNameLabel = new JLabel("Username");
        userNameLabel.setBounds(50, 328, 98, 22);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(50, 380, 74, 22);

        nameLabelField = new JTextField();
        nameLabelField.setBounds(136, 89, 166, 20);
        nameLabelField.setColumns(10);

        dayComboBox = new JComboBox<>();
        dayComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        dayComboBox.setBounds(136, 172, 50, 20);

        monthComboBox = new JComboBox<>();
        monthComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
        monthComboBox.setBounds(260, 172, 80, 20);


        int thisYear = LocalDateTime.now().getYear();
        String[] yearLabels = new String[53];
        int i = 0;
        while (thisYear >= 1969) {
            yearLabels[i] = Integer.toString(thisYear);
            i++;
            thisYear--;
        }

        yearComboBox = new JComboBox<>();
        yearComboBox.setModel(new DefaultComboBoxModel<>(yearLabels));
        yearComboBox.setBounds(390, 172, 100, 20);

        sexComboBox = new JComboBox<>();
        sexComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"Male", "Female", "Others"}));
        sexComboBox.setBounds(136, 224, 50, 30);

        certificationLabelField = new JTextField();
        certificationLabelField.setBounds(136, 276, 166, 20);
        certificationLabelField.setColumns(10);

        userNameField = new JTextField();
        userNameField.setBounds(136, 328, 166, 20);
        userNameField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(136, 380, 166, 20);

        registerButton.setBounds(210, 450, 89, 23);
        registerButton.setFont(new Font("Calibri", Font.BOLD, 14));

        frame.setBounds(500, 500, 550, 600);
        frame.getContentPane().setLayout(null);

        registerButton.addActionListener(e -> addTrainerController.executeUpdatesNameAndCertification(this.getName(), this.getBirthdayLabel(), this.getSelectedSex(), this.getCertification(), this.getUsernameLabel(), this.getPassword()));

        addItemsToFrame();
    }

    private void setFrameDesign() {
        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        userNameLabel.setForeground(FrameDesign.CREAM);
        userNameLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        passwordLabel.setForeground(FrameDesign.CREAM);
        passwordLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        certificationLabel.setForeground(FrameDesign.CREAM);
        certificationLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        sexLabel.setForeground(FrameDesign.CREAM);
        sexLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        yearLabel.setForeground(FrameDesign.CREAM);
        yearLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        nameLabel.setForeground(FrameDesign.CREAM);
        nameLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        birthdayLabel.setForeground(FrameDesign.CREAM);
        birthdayLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        dayLabel.setForeground(FrameDesign.CREAM);
        dayLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        monthLabel.setForeground(FrameDesign.CREAM);
        monthLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        nameLabelField.setBackground(FrameDesign.GREY);
        
        dayComboBox.setBackground(FrameDesign.GREY);
        
        monthComboBox.setBackground(FrameDesign.GREY);
        
        yearComboBox.setBackground(FrameDesign.GREY);
        
        certificationLabelField.setBackground(FrameDesign.GREY);
        
        userNameField.setBackground(FrameDesign.GREY);
        
        passwordField.setBackground(FrameDesign.GREY);
        
        registerButton.setBackground(FrameDesign.LIGHT_BLUE);
    }

    private void addItemsToFrame() {
        frame.getContentPane().add(nameLabel)
                .getParent().add(nameLabelField)
                .getParent().add(birthdayLabel)
                .getParent().add(dayLabel)
                .getParent().add(dayComboBox)
                .getParent().add(monthLabel)
                .getParent().add(monthComboBox)
                .getParent().add(yearLabel)
                .getParent().add(yearComboBox)
                .getParent().add(sexLabel)
                .getParent().add(sexComboBox)
                .getParent().add(certificationLabel)
                .getParent().add(certificationLabelField)
                .getParent().add(userNameLabel)
                .getParent().add(userNameField)
                .getParent().add(passwordLabel)
                .getParent().add(passwordField)
                .getParent().add(registerButton);
    }


    public String getName() {
        return nameLabelField.getText();
    }

    private String getSelectedDay() {
        return Objects.requireNonNull(dayComboBox.getSelectedItem()).toString();
    }

    private String getSelectedMonth() {

        switch (Objects.requireNonNull(monthComboBox.getSelectedItem()).toString()) {
            case "February":
                return "02";
            case "March":
                return "03";
            case "April":
                return "04";
            case "May":
                return "05";
            case "June":
                return "06";
            case "July":
                return "07";
            case "August":
                return "08";
            case "September":
                return "09";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
            default: //January
                return "01";
        }

    }

    private String getSelectedYear() {
        return Objects.requireNonNull(yearComboBox.getSelectedItem()).toString();
    }

    private String getSelectedSex() {
        return Objects.requireNonNull(sexComboBox.getSelectedItem()).toString();
    }

    private String getBirthdayLabel() {
        return this.getSelectedDay() + "." + this.getSelectedMonth() + "." + this.getSelectedYear();
    }

    public String getCertification() {
        return certificationLabelField.getText();
    }

    private String getUsernameLabel() {
        return userNameField.getText();
    }

    public String getPassword() {
        return String.valueOf(passwordField.getPassword());
    }

}
