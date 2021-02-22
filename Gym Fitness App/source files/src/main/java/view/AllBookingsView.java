package view;

import controller.AllBookingsController;
import utils.FrameDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AllBookingsView {

    private JFrame frame;

    private JTable table;

    private final JLabel infoLabel = new JLabel("These are your bookings. Please click Cancel Booking if you would like to cancel a booking.");
    private final String idClient;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final AllBookingsController allBookingsController;

    public AllBookingsView(String idClient) {
        allBookingsController = new AllBookingsController();

        frame = new JFrame("My Bookings");

        this.idClient = idClient;

        createTable();

        initialize();

        setFrameDesign();

        frame.setVisible(true);
    }

    private void initialize() {
        frame.setSize(720, 400);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        infoLabel.setBounds(10, 70, 650, 20);
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.setBounds(0, 100, 550, 100);
        scrollPane.setBackground(FrameDesign.GREY);
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(infoLabel);

        frame.getContentPane().setLayout(null);

        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2, dimension.height / 2 - frame.getSize().height / 2);
    }

    private void createTable(){
        DefaultTableModel tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        tableModel.addColumn("Client Name");
        tableModel.addColumn("Booked Training");
        tableModel.addColumn("Date");
        tableModel.addColumn("Membership");
        tableModel.addRow(new Object[]{"-", "-", "-", "-"});
        allBookingsController.viewBookings(frame, tableModel, idClient);
    }

    private void setFrameDesign(){
        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);
        table.setBackground(FrameDesign.GREY);
        table.setFont(FrameDesign.CALIBRI_SIMPLE_14);
        infoLabel.setForeground(FrameDesign.CREAM);
        infoLabel.setFont(FrameDesign.CALIBRI_BOLD_14);
    }
}
