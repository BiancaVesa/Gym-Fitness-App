package view;

import controller.EditManagerController;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;

public class EditManagerView extends JFrame {

    private JFrame frame;
    
    private final EditManagerController editManagerController;
    
    private JLabel newNameLabel;
    private JLabel newEmailLabel;
    private JLabel newPhoneNrLabel;

    private JTextField nameField;
    private JTextField emailField;
    private JTextField phoneNrField;

    private JButton editButton = new JButton("Edit Data");

    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public EditManagerView() {
        editManagerController = new EditManagerController();

        frame = new JFrame("Edit Manager Data");

        initialize();
        
        setDesignFrame();

        frame.setVisible(true);

    }

    private void initialize() {
        newNameLabel = new JLabel("New Name");
        newNameLabel.setBounds(120, 152, 100, 22);

        newEmailLabel = new JLabel("New Email");
        newEmailLabel.setBounds(120, 190, 100, 22);

        newPhoneNrLabel = new JLabel("New Phone Nr.");
        newPhoneNrLabel.setBounds(120, 228, 100, 22);

        nameField = new JTextField();
        nameField.setBounds(250, 152, 100, 22);
        emailField = new JTextField();
        emailField.setBounds(250, 190, 100, 22);
        phoneNrField = new JTextField();
        phoneNrField.setBounds(250, 228, 100, 22);

        editButton.setBounds(180, 300, 100, 30);


        frame.setSize(500, 500);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2, dimension.height / 2 - frame.getSize().height / 2);

        editButton.addActionListener(e -> editManagerController.executeUpdatesNamePhoneOrEmail(nameField.getText(), phoneNrField.getText(), emailField.getText()));


        addItemsToFrame();
        
    }

    private void addItemsToFrame() {
        frame.getContentPane().add(newNameLabel)
        .getParent().add(nameField)
        .getParent().add(newEmailLabel)
        .getParent().add(emailField)
        .getParent().add(newPhoneNrLabel)
        .getParent().add(phoneNrField)
        .getParent().add(editButton);
    }

    private void setDesignFrame() {

        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        newNameLabel.setFont(FrameDesign.CALIBRI_BOLD_14);
        newPhoneNrLabel.setFont(FrameDesign.CALIBRI_BOLD_14);
        newEmailLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        newNameLabel.setForeground(FrameDesign.CREAM);
        newPhoneNrLabel.setForeground(FrameDesign.CREAM);
        newEmailLabel.setForeground(FrameDesign.CREAM);

        nameField.setBackground(FrameDesign.GREY);
        phoneNrField.setBackground(FrameDesign.GREY);
        emailField.setBackground(FrameDesign.GREY);
        editButton.setBackground(FrameDesign.GREY);
        editButton.setBackground(FrameDesign.LIGHT_BLUE);

    }
}
