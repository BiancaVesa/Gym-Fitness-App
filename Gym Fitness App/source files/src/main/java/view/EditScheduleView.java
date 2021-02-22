package view;

import controller.EditScheduleController;
import dto.ScheduleDto;
import entity.Schedule;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import mappers.ScheduleMapper;
import service.ScheduleService;
import utils.FrameDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class EditScheduleView extends JFrame {

    private final EditScheduleController editScheduleController;
    private final ScheduleService scheduleService;
    private final Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
    private JFrame frame;
    private JTable scheduleTable;
    private JLabel dateLabel;
    private JLabel dayLabel;
    private JLabel monthLabel;
    private JLabel yearLabel;
    private JLabel hourLabel;
    private JLabel minuteLabel;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> monthComboBox;
    private JComboBox<String> yearComboBox;
    private JComboBox<String> hourComboBox;
    private JComboBox<String> minuteComboBox;
    private String idTrainer;
    private JButton updateButton = new JButton("Update");

    public EditScheduleView(String idTrainer) {
        editScheduleController = new EditScheduleController();
        scheduleService = new ScheduleService();
        this.idTrainer = idTrainer;

        frame = new JFrame("Edit Schedule");

        initialize();

        setDesignFrame();

        frame.setVisible(true);
    }

    private void initialize() {
        frame.setBounds(500, 500, 540, 500);
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);

        dayLabel = new JLabel("Day");
        dayLabel.setBounds(10, 340, 30, 22);

        monthLabel = new JLabel("Month");
        monthLabel.setBounds(100, 340, 50, 22);

        yearLabel = new JLabel("Year");
        yearLabel.setBounds(210, 340, 50, 22);

        hourLabel = new JLabel("Hour");
        hourLabel.setBounds(310, 340, 50, 22);

        minuteLabel = new JLabel("Minute");
        minuteLabel.setBounds(410, 340, 50, 22);


        scheduleTable = new JTable();
        dateLabel = new JLabel("New date");
        dateLabel.setBounds(245, 310, 70, 30);

        scheduleTable.setRowSelectionAllowed(true);

        createTable();

        dayComboBox = new JComboBox<>();
        dayComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        dayComboBox.setBounds(40, 340, 50, 22);

        monthComboBox = new JComboBox<>();
        monthComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
        monthComboBox.setBounds(150, 340, 50, 22);

        yearComboBox = new JComboBox<>();
        yearComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031"}));
        yearComboBox.setBounds(250, 340, 50, 22);

        hourComboBox = new JComboBox<>();
        hourComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
        hourComboBox.setBounds(350, 340, 50, 22);

        minuteComboBox = new JComboBox<>();
        minuteComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"00", "30"}));
        minuteComboBox.setBounds(460, 340, 50, 22);

        updateButton.setBounds(215, 400, 110, 20);


        addItemsToFrame();

        scheduleTable.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(getSelectedDate());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        updateButton.addActionListener(actionEvent -> setDateInTable(editScheduleController.executeUpdateForSchedule(getNewDay(), getNewMonth(), getNewYear(),
                getNewHour(), getNewMinute(), getSelectedDate()), scheduleTable.getSelectedRow()));

        frame.setLocation(screenDimension.width / 2 - frame.getSize().width / 2, screenDimension.height / 2 - frame.getSize().height / 2);

    }

    private void addItemsToFrame() {
        frame.getContentPane().add(dateLabel)
                .getParent().add(dayLabel)
                .getParent().add(dayComboBox)
                .getParent().add(monthLabel)
                .getParent().add(monthComboBox)
                .getParent().add(yearLabel)
                .getParent().add(yearComboBox)
                .getParent().add(hourLabel)
                .getParent().add(hourComboBox)
                .getParent().add(minuteLabel)
                .getParent().add(minuteComboBox)
                .getParent().add(updateButton);
    }

    private void createTable() {

        String[] column = {"ClassName", "TimeStamp", "TrainerName"};

        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        scheduleTable = new JTable(tableModel);
        java.util.List<Schedule> allSchedule = this.scheduleService.getScheduleByDate();
        if (idTrainer.equals("")) {
            for (Schedule sch : allSchedule) {
                ScheduleDto scheduleDto = ScheduleMapper.entityToDto(sch);
                tableModel.addRow(new Object[]{scheduleDto.getClassName(), scheduleDto.getTemp(), scheduleDto.getTrainerName()});
            }
        } else {
            for (Schedule sch : allSchedule) {
                if (sch.getTrainer().getIdTrainer().equals(idTrainer)) {
                    ScheduleDto scheduleDto = ScheduleMapper.entityToDto(sch);
                    tableModel.addRow(new Object[]{scheduleDto.getClassName(), scheduleDto.getTemp(), scheduleDto.getTrainerName()});
                }
            }
        }

        scheduleTable.setModel(tableModel);

        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        scrollPane.setBounds(80, 68, 370, 200);
        scrollPane.setBackground(FrameDesign.GREY);
        frame.getContentPane().add(scrollPane);
    }

    private void setDateInTable(LocalDateTime localDateTime, int selectedRow) {
        if (localDateTime != null)
            scheduleTable.getModel().setValueAt(localDateTime, selectedRow, 1);
    }

    private int getNewDay() {
        return Integer.parseInt(Objects.requireNonNull(dayComboBox.getSelectedItem()).toString());
    }

    private int getNewMonth() {
        return Integer.parseInt(Objects.requireNonNull(monthComboBox.getSelectedItem()).toString());

    }

    private int getNewYear() {
        return Integer.parseInt(Objects.requireNonNull(yearComboBox.getSelectedItem()).toString());
    }

    private int getNewHour() {
        return Integer.parseInt(Objects.requireNonNull(hourComboBox.getSelectedItem()).toString());
    }

    private int getNewMinute() {
        return Integer.parseInt(Objects.requireNonNull(minuteComboBox.getSelectedItem()).toString());
    }

    private LocalDateTime getSelectedDate() {
        System.out.println(scheduleTable.getSelectedRow());

        if (scheduleTable.getSelectedRow() != -1)
            return LocalDateTime.parse(String.valueOf(scheduleTable.getModel().getValueAt(scheduleTable.getSelectedRow(), 1))
                            .replaceAll("T", " "),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return null;
    }

    private void setDesignFrame() {
        ImageIcon img = new ImageIcon("dumbbell.png");
        frame.setIconImage(img.getImage());

        frame.getContentPane().setBackground(FrameDesign.NAVY_BLUE);

        dayLabel.setForeground(FrameDesign.CREAM);
        dayLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        monthLabel.setForeground(FrameDesign.CREAM);
        monthLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        yearLabel.setForeground(FrameDesign.CREAM);
        yearLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        hourLabel.setForeground(FrameDesign.CREAM);
        hourLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        minuteLabel.setForeground(FrameDesign.CREAM);
        minuteLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        dateLabel.setForeground(FrameDesign.CREAM);
        dateLabel.setFont(FrameDesign.CALIBRI_BOLD_14);

        dayComboBox.setBackground(FrameDesign.GREY);
        monthComboBox.setBackground(FrameDesign.GREY);
        yearComboBox.setBackground(FrameDesign.GREY);
        minuteComboBox.setBackground(FrameDesign.GREY);
        hourComboBox.setBackground(FrameDesign.GREY);
        updateButton.setBackground(FrameDesign.LIGHT_BLUE);
    }


}
