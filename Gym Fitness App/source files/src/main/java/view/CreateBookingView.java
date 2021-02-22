package view;

import controller.CreateBookingController;
import utils.FrameDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CreateBookingView {
    private final JLabel infoLabel = new JLabel("These are the available bookings for you. Please click Book This Class if you would like to book a training class.");
    private final String idClient;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final CreateBookingController bookingController;
    private JFrame frame;
    private JTable table;

    public CreateBookingView(String idClient) {
        bookingController = new CreateBookingController();

        frame = new JFrame("Available Classes");

        this.idClient = idClient;

        createTable();

        initializeCreateBookingView();

        setFrameDesign();

        frame.setVisible(true);

    }

    private void initializeCreateBookingView() {
        frame.setSize(720, 400);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        infoLabel.setBounds(10, 70, 650, 20);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 600, 100);
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(infoLabel);
        frame.setResizable(false);

        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2, dimension.height / 2 - frame.getSize().height / 2);

    }

    private void createTable() {

        DefaultTableModel tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        tableModel.addColumn("Class");
        tableModel.addColumn("Starts At");
        tableModel.addColumn("Duration");
        tableModel.addColumn("Trainer");
        tableModel.addColumn("Rating Trainer");
        tableModel.addRow(new Object[]{"-", "-", "-", "-", "-"});
        bookingController.addBookings(frame, tableModel, idClient);
    }

    private void setFrameDesign() {
        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);
        table.setBackground(FrameDesign.GREY);
        table.setFont(FrameDesign.CALIBRI_SIMPLE_14);
        infoLabel.setForeground(FrameDesign.CREAM);
        infoLabel.setFont(FrameDesign.CALIBRI_BOLD_14);
    }
}
