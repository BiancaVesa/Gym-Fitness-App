package view;

import controller.GenerateMonthlyRaportsController;
import utils.FrameDesign;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GenerateMonthlyReportsView extends JFrame {

    private final GenerateMonthlyRaportsController generateMonthlyRaportsController;

    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    private JFrame frame;

    private JLabel monthLabel;
    private JLabel yearLabel;

    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;

    private JButton btnGenerateReports = new JButton("Generate Reports");

    public GenerateMonthlyReportsView() {
        frame = new JFrame("Monthly Reports");

        generateMonthlyRaportsController = new GenerateMonthlyRaportsController();

        initialize();

        setDesignFrame();

        frame.setVisible(true);
    }

    private void initialize() {

        monthLabel = new JLabel("Select Month");
        monthLabel.setBounds(150, 192, 100, 22);

        monthComboBox = new JComboBox<>();
        monthComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
        monthComboBox.setBounds(270,192, 65, 22);

        yearLabel = new JLabel("Select Year");
        yearLabel.setBounds(150, 230, 100, 22);

        yearComboBox = new JComboBox<>();
        yearComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009"}));
        yearComboBox.setBounds(270,230, 65, 22);

        btnGenerateReports.setBounds(175, 290, 150, 30);

        frame.setSize(500, 500);
        frame.setResizable(false);

        frame.getContentPane().setLayout(null);

        addItemsToFrame();

        frame.setLocation(dimension.width / 2 - frame.getSize().width / 2, dimension.height / 2 - frame.getSize().height / 2);

        btnGenerateReports.addActionListener(e -> generateMonthlyRaportsController.generateRaport(getSelectedMonth(), getSelectedYear()));
    }

    private void addItemsToFrame(){
        frame.getContentPane().add(monthLabel)
        .getParent().add(monthComboBox)
        .getParent().add(yearLabel)
        .getParent().add(yearComboBox)
        .getParent().add(btnGenerateReports);
    }

    private String getSelectedMonth() {
        return Objects.requireNonNull(monthComboBox.getSelectedItem()).toString();
    }

    private String getSelectedYear() {
        return Objects.requireNonNull(yearComboBox.getSelectedItem()).toString();
    }

    private void setDesignFrame(){
        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        monthLabel.setForeground(FrameDesign.CREAM);
        monthLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        yearLabel.setForeground(FrameDesign.CREAM);
        yearLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        monthComboBox.setBackground(FrameDesign.GREY);
        yearComboBox.setBackground(FrameDesign.GREY);
        btnGenerateReports.setBackground(FrameDesign.LIGHT_BLUE);
    }

}
