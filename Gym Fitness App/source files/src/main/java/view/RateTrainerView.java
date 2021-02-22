package view;

import controller.RateTrainerController;
import utils.FrameDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RateTrainerView {
    private JFrame frame;

    private JTable table;

    private final JLabel infoLabel = new JLabel("Please choose the trainer you want to rate.");
    private final String idClient;

    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    private final RateTrainerController rateTrainerController;

    public RateTrainerView(String idClient) {
        rateTrainerController = new RateTrainerController();

        frame = new JFrame("Rate Trainer");

        this.idClient = idClient;

        createTable();

        initializeBookingsView();

        setFrameDesign();

        frame.setVisible(true);

    }

    private void initializeBookingsView() {
        frame.setSize(720, 400);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        infoLabel.setBounds(10, 70, 650, 20);
        frame.setResizable(false);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 100, 600, 100);
        frame.getContentPane().add(scrollPane);
        frame.getContentPane().add(infoLabel);

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
        tableModel.addColumn("Trainer");
        tableModel.addColumn("Class You've Participated");
        tableModel.addRow(new Object[]{"-", "-"});
        rateTrainerController.viewTrainers(frame, tableModel, idClient);
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
