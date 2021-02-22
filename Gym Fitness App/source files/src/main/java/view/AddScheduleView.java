package view;

import controller.TrainerController;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;

public class AddScheduleView {
    public JFrame frame;

    private JTextField classNameField;
    private JTextField tempField;
    private JTextField idTrainerField;

    private JLabel classNameLabel;
    private JLabel tempFieldLabel;
    private JLabel idTrainerLabel;

    private JButton okButton;

    private TrainerController trainerController;

    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public AddScheduleView() {
        trainerController = new TrainerController();

        frame = new JFrame("Add schedule");

        initialize();

        setFrameDesign();

        frame.setVisible(true);
    }

    private void initialize() {
        frame.setSize(500, 500);
        frame.getContentPane().setLayout(null);
        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2, dimension.height / 2 - frame.getSize().height / 2);

        frame.setResizable(false);

        classNameLabel = new JLabel("Class name: ");
        tempFieldLabel = new JLabel("Hour: ");
        idTrainerLabel = new JLabel("Your id: ");
        okButton = new JButton("OK");

        classNameLabel.setBounds(110, 160, 120, 20);
        tempFieldLabel.setBounds(110, 190, 120, 20);
        idTrainerLabel.setBounds(110, 220, 120, 20);
        okButton.setBounds(200, 250, 60, 30);

        classNameField = new JTextField();
        tempField = new JTextField();
        idTrainerField = new JTextField();

        classNameField.setBounds(250, 160, 120, 20);
        tempField.setBounds(250, 190, 120, 20);
        idTrainerField.setBounds(250, 220, 120, 20);

        addItemsToFrame();

        okButton.addActionListener(e -> trainerController.addNewSchedule(classNameField.getText(), tempField.getText(),
                idTrainerField.getText()));
    }

    private void setFrameDesign() {

        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        classNameLabel.setForeground(FrameDesign.CREAM);
        tempFieldLabel.setForeground(FrameDesign.CREAM);
        idTrainerLabel.setForeground(FrameDesign.CREAM);

        classNameLabel.setFont(FrameDesign.CALIBRI_BOLD_14);
        tempFieldLabel.setFont(FrameDesign.CALIBRI_BOLD_14);
        idTrainerLabel.setFont(FrameDesign.CALIBRI_BOLD_14);
        okButton.setFont(FrameDesign.CALIBRI_BOLD_14);

        classNameField.setBackground(FrameDesign.GREY);
        tempField.setBackground(FrameDesign.GREY);
        idTrainerField.setBackground(FrameDesign.GREY);
        okButton.setBackground(FrameDesign.LIGHT_BLUE);
    }

    private void addItemsToFrame() {
        frame.getContentPane().add(classNameLabel)
                .getParent().add(tempFieldLabel)
                .getParent().add(idTrainerLabel)
                .getParent().add(classNameField)
                .getParent().add(tempField)
                .getParent().add(idTrainerField)
                .getParent().add(okButton);
    }
}
