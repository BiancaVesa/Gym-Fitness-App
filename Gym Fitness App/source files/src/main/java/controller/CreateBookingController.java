package controller;

import dto.ScheduleDto;
import entity.Schedule;
import mappers.ScheduleMapper;
import service.ClientService;
import utils.ControllerUtils;
import utils.FrameDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CreateBookingController {
    private ClientService clientService = new ClientService();
    private int i = 20;

    public void addBookings(JFrame frame, DefaultTableModel tableModel, String idClient) {
        List<Schedule> allAvailableBookings = clientService.viewAvailableClasses(idClient);
        if (!allAvailableBookings.isEmpty()) {
            tableModel.removeRow(0);

            for (Schedule schedule : allAvailableBookings) {
                JButton btnBookClass = new JButton("Book This Class");

                btnBookClass.setBounds(600, 100 + i, 150, 17);
                btnBookClass.setFont(FrameDesign.CALIBRI_BOLD_14);

                i += 17;
                frame.add(btnBookClass);
                btnBookClass.addActionListener(actionEvent -> {
                    if (JOptionPane.showConfirmDialog(null, "Do you want to book this class?", "Book a Class",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        try {
                            clientService.createBooking(idClient, schedule);
                            btnBookClass.setText("Booked");
                            btnBookClass.setEnabled(false);
                        } catch (Exception ex) {
                            ControllerUtils.createSwingErrorMessage(ex.getMessage());
                        }
                    }
                });
                ScheduleDto scheduleDto = ScheduleMapper.entityToDto(schedule);


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                String formatDateTime = scheduleDto.getTemp().format(formatter);

                tableModel.addRow(new Object[]{scheduleDto.getClassName(), formatDateTime, "1 hr", scheduleDto.getTrainerName(), scheduleDto.getRatingTrainer()});
            }
        }
    }
}
