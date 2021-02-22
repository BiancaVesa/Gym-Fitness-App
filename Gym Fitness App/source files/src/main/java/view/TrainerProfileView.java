package view;

import controller.TrainerController;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;

public class TrainerProfileView extends JFrame{

    private JFrame frame;

    private JLabel labelProfile = new JLabel("Your Profile");
    private JLabel idLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel certificationLabel = new JLabel();
    private JLabel ratingLabel = new JLabel();

    private final String idTrainer;
    private final TrainerController trainerController;

    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public TrainerProfileView(String idTrainer) {
        trainerController = new TrainerController();

        this.idTrainer = idTrainer;

        frame = new JFrame("Trainer Profile");

        setFrameDesign();

        initializeTrainerProfileView();

        frame.setVisible(true);
    }

    private void initializeTrainerProfileView(){
        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);
        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2, dimension.height / 2 - frame.getSize().height / 2);

        labelProfile.setBounds(170, 10, 100, 20);
        idLabel.setBounds(75, 70, 300, 30);
        nameLabel.setBounds(75, 130, 250, 30);
        certificationLabel.setBounds(75,190,250,30);
        ratingLabel.setBounds(75,250,250,30);

        frame.setSize(420, 500);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        addItemsToFrame();
    }

    private void addItemsToFrame() {
        frame.getContentPane().add(labelProfile)
                .getParent().add(idLabel)
                .getParent().add(nameLabel)
                .getParent().add(certificationLabel)
                .getParent().add(ratingLabel);
    }

    private void setFrameDesign(){

        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        String[][] data = trainerController.getTrainerProfileData(idTrainer);

        labelProfile.setForeground(FrameDesign.DARK_PINK);
        labelProfile.setFont(FrameDesign.CALIBRI_BOLD_16);

        idLabel.setText("ID:       " + data[0][0]);
        idLabel.setForeground(FrameDesign.CREAM);
        idLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        nameLabel.setText("Name:       " + data[0][1]);
        nameLabel.setForeground(FrameDesign.CREAM);
        nameLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        certificationLabel.setText("Certification:       " + (data[0][2] == null ? "None" : data[0][2]));
        certificationLabel.setForeground(FrameDesign.CREAM);
        certificationLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        ratingLabel.setText("Rating:       " + data[0][3]);
        ratingLabel.setForeground(FrameDesign.CREAM);
        ratingLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

    }
}
