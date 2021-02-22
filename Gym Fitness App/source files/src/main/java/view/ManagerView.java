package view;

import controller.ManagerController;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;

public class ManagerView extends JFrame {

    private ManagerController managerController;

    private JFrame frame;
    private JButton addTrainer = new JButton("Add Trainer");;
    private JButton editSchedule = new JButton("Edit Schedule");;
    private JButton viewReports = new JButton("View Reports");
    private JButton editProfile = new JButton("Edit Profile");
    private JButton back = new JButton("Log Out");

    private final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    public ManagerView()
    {
        managerController = new ManagerController();

        frame = new JFrame("Manager");

        initialize();

        setDesignFrame();

        frame.setVisible(true);
    }

    private void initialize() {

        back.setBounds(0, 0, 80, 20);
        addTrainer.setBounds(175, 176, 130, 23);
        editSchedule.setBounds(175, 210, 130, 23);
        viewReports.setBounds(175, 244, 130, 23);
        editProfile.setBounds(175, 276, 130, 23);

        frame.setBounds(500, 500, 500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        frame.getContentPane().add(addTrainer);
        frame.getContentPane().add(editSchedule);
        frame.getContentPane().add(viewReports);
        frame.getContentPane().add(editProfile);
        frame.getContentPane().add(back);

        addTrainer.addActionListener( e -> managerController.addNewTrainer());
        editSchedule.addActionListener(e -> managerController.editTrainer());
        viewReports.addActionListener(e -> managerController.generateRaports());
        editProfile.addActionListener(e -> managerController.editManagerData());
        back.addActionListener(e -> managerController.goBack(frame));

        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

    private void setDesignFrame(){
        ImageIcon img = new ImageIcon( "dumbbell.png");
        frame.setIconImage(img.getImage());

        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        addTrainer.setBackground(FrameDesign.LIGHT_BLUE);
        addTrainer.setFont(FrameDesign.CALIBRI_BOLD_14);

        editProfile.setBackground(FrameDesign.LIGHT_BLUE);
        editProfile.setFont(FrameDesign.CALIBRI_BOLD_14);

        editSchedule.setBackground(FrameDesign.LIGHT_BLUE);
        editSchedule.setFont(FrameDesign.CALIBRI_BOLD_14);

        viewReports.setBackground(FrameDesign.LIGHT_BLUE);
        viewReports.setFont(FrameDesign.CALIBRI_BOLD_14);

        back.setBackground(FrameDesign.LIGHT_BLUE);
        back.setFont(FrameDesign.CALIBRI_BOLD_14);
    }
}
