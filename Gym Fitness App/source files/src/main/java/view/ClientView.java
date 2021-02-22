package view;

import controller.ClientController;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class ClientView extends JFrame {
    private final ClientController clientController;
    private final String idClient;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private JFrame frame;
    private JButton btnViewMyBookings = new JButton("View My Bookings");
    private JButton btnViewProfile = new JButton("View Profile");
    private JButton btnCreateBooking = new JButton("Book a Class");
    private JButton btnCreateMembership = new JButton("Create Membership");
    private JButton btnRateTrainer = new JButton("Rate Trainer");
    private JButton btnLogOut = new JButton("Log Out");
    private JComboBox<String> comboEditCredentials = new JComboBox<>();

    public ClientView(String idClient) {
        clientController = new ClientController();

        frame = new JFrame("Client");

        this.idClient = idClient;

        initializeClientView();

        setFrameDesign();

        frame.setVisible(true);

    }

    private void initializeClientView() {

        btnLogOut.setBounds(0, 0, 80, 30);
        btnViewProfile.setBounds(75, 75, 250, 30);
        btnViewMyBookings.setBounds(75, 135, 250, 30);
        btnCreateBooking.setBounds(75, 195, 250, 30);
        btnCreateMembership.setBounds(75, 255, 250, 30);
        btnRateTrainer.setBounds(75, 315, 250, 30);
        comboEditCredentials.setBounds(75, 375, 250, 30);

        frame.setSize(400, 550);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(new Color(42, 50, 75));
        frame.setResizable(false);

        addItemsToFrame();

        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2, dimension.height / 2 - frame.getSize().height / 2);

        btnViewProfile.addActionListener(e -> clientController.viewProfile(idClient));
        btnViewMyBookings.addActionListener(e -> clientController.viewAllMyBookings(idClient));
        btnCreateBooking.addActionListener(e -> clientController.createBooking(idClient));
        btnCreateMembership.addActionListener(e -> clientController.createMembership(idClient));
        btnRateTrainer.addActionListener(e -> clientController.rateTrainer(idClient));
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginView();
                frame.dispose();
            }
        });

        comboEditCredentials.setModel(new DefaultComboBoxModel<>(new String[]{"Change Username", "Change Password"}));
        comboEditCredentials.addActionListener(e -> changeUserNameOrPassword());

    }

    private void setFrameDesign() {
        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        btnViewProfile.setBackground(FrameDesign.LIGHT_BLUE);
        btnViewProfile.setFont(FrameDesign.CALIBRI_BOLD_14);

        btnViewMyBookings.setBackground(FrameDesign.LIGHT_BLUE);
        btnViewMyBookings.setFont(FrameDesign.CALIBRI_BOLD_14);

        btnCreateBooking.setBackground(FrameDesign.LIGHT_BLUE);
        btnCreateBooking.setFont(FrameDesign.CALIBRI_BOLD_14);

        btnCreateMembership.setBackground(FrameDesign.LIGHT_BLUE);
        btnCreateMembership.setFont(FrameDesign.CALIBRI_BOLD_14);

        btnRateTrainer.setBackground(FrameDesign.LIGHT_BLUE);
        btnRateTrainer.setFont(FrameDesign.CALIBRI_BOLD_14);

        btnLogOut.setBackground(FrameDesign.LIGHT_BLUE);
        btnLogOut.setFont(FrameDesign.CALIBRI_BOLD_14);
    }


    private void changeUserNameOrPassword() {
        if (Objects.requireNonNull(comboEditCredentials.getSelectedItem()).toString().equals("Change Username")) {
            clientController.changeUserName(frame, idClient);
        }

        if (comboEditCredentials.getSelectedItem().toString().equals("Change Password")) {
            clientController.changePassword(frame, idClient);
        }
    }

    private void addItemsToFrame() {
        frame.getContentPane().add(btnViewProfile)
                .getParent().add(btnViewMyBookings)
                .getParent().add(btnCreateBooking)
                .getParent().add(btnCreateMembership)
                .getParent().add(btnRateTrainer)
                .getParent().add(comboEditCredentials)
                .getParent().add(btnLogOut);
    }

}
