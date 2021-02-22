package view;

import controller.TrainerController;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;


public class TrainerView extends JFrame{
    private final TrainerController trainerController;

    private final String idTrainer;

    public JFrame frame;

    private JButton viewProfileButton = new JButton("View Profile");
    private JButton editScheduleButton = new JButton("Edit Schedule");
    private JButton editTrainerButton = new JButton("Edit Profile");
    private JButton addScheduleButton = new JButton("Add Schedule");
    private JButton back = new JButton("Log Out");

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public TrainerView(String idTrainer) {
        trainerController = new TrainerController();

        frame = new JFrame("Trainer");

        this.idTrainer = idTrainer;

        initializeTrainerView();

        setDesignFrame();

        frame.setVisible(true);
    }

    private void initializeTrainerView(){

        viewProfileButton.setBounds(75, 75, 250, 30);
        editScheduleButton.setBounds(75, 135, 250, 30);
        editTrainerButton.setBounds(75, 195, 250, 30);
        addScheduleButton.setBounds(75, 255, 250, 30);
        back.setBounds(0, 0, 80, 30);
        frame.setSize(400,400);
        frame.getContentPane().setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        addToFrame();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        viewProfileButton.addActionListener(e -> trainerController.viewProfile(frame, idTrainer));
        editTrainerButton.addActionListener(e -> trainerController.viewEditTrainerProfile(frame, idTrainer));
        editScheduleButton.addActionListener(e -> trainerController.viewEditSchedule(frame,idTrainer));
        addScheduleButton.addActionListener(e->trainerController.viewAddSchedule(frame, idTrainer));
        back.addActionListener(e -> trainerController.goBack(frame));
    }

    private void addToFrame(){
        frame.getContentPane().add(viewProfileButton)
        .getParent().add(editScheduleButton)
        .getParent().add(editTrainerButton)
        .getParent().add(addScheduleButton)
        .getParent().add(back);
    }

    private void setDesignFrame(){
        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        viewProfileButton.setFont(FrameDesign.CALIBRI_BOLD_14);
        editTrainerButton.setFont(FrameDesign.CALIBRI_BOLD_14);
        editScheduleButton.setFont(FrameDesign.CALIBRI_BOLD_14);
        addScheduleButton.setFont(FrameDesign.CALIBRI_BOLD_14);
        back.setFont(FrameDesign.CALIBRI_BOLD_14);

        back.setBackground(FrameDesign.LIGHT_BLUE);
        viewProfileButton.setBackground(FrameDesign.LIGHT_BLUE);
        editTrainerButton.setBackground(FrameDesign.LIGHT_BLUE);
        editScheduleButton.setBackground(FrameDesign.LIGHT_BLUE);
        addScheduleButton.setBackground(FrameDesign.LIGHT_BLUE);
    }


}
