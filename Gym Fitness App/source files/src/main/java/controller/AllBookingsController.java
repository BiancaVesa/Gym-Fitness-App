package controller;

import dto.BookingDto;
import entity.Booking;
import mappers.BookingMapper;
import service.ClientService;
import start.ApplicationStart;
import utils.FrameDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

public class AllBookingsController {
    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());
    private ClientService clientService = new ClientService();
    private int i = 20;


    public void viewBookings(JFrame frame, DefaultTableModel tableModel, String idClient) {
        List<Booking> allBookings = clientService.viewAllBookings(idClient);
        if (!allBookings.isEmpty()) {
            tableModel.removeRow(0);

            for (Booking booking : allBookings) {
                JButton btnCancel = new JButton("Cancel Booking");

                btnCancel.setBounds(550, 100 + i, 150, 17);
                btnCancel.setFont(FrameDesign.CALIBRI_BOLD_14);

                i += 17;
                frame.add(btnCancel);
                btnCancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel this booking?", "Cancel Booking",
                                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                            clientService.cancelBooking(booking);
                            btnCancel.setText("Canceled");
                            btnCancel.setEnabled(false);
                            log.info("Booking cancel.");
                        }
                    }
                });
                BookingDto bookingDto = BookingMapper.entityToDto(booking);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

                String formatDateTime = bookingDto.getTimestamp().format(formatter);

                tableModel.addRow(new Object[]{bookingDto.getClient(), bookingDto.getBookedTraining(), formatDateTime, bookingDto.getMembershipType()});
            }
        }
    }

}
