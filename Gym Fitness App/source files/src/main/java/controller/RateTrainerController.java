package controller;

import dto.TrainerDto;
import entity.Trainer;
import mappers.TrainerMapper;
import service.ClientService;
import start.ApplicationStart;
import utils.FrameDesign;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Logger;

public class RateTrainerController extends JFrame {
    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());

    private ClientService clientService = new ClientService();
    private Integer[] options = {1, 2, 3, 4, 5};
    private int i = 20;

    /*TODO: adauga buton in tabel*/
    public void viewTrainers(JFrame frame, DefaultTableModel tableModel, String idClient) {
        List<Trainer> allTrainers = clientService.viewAllTrainersToRate(idClient);
        if (!allTrainers.isEmpty()) {
            tableModel.removeRow(0);

            for (Trainer trainer : allTrainers) {
                JButton btnRate = new JButton("Rate Trainer");

                btnRate.setBounds(600, 100 + i, 150, 17);
                btnRate.setFont(FrameDesign.CALIBRI_BOLD_14);

                i += 17;
                frame.add(btnRate);

                TrainerDto trainerDto = TrainerMapper.entityToDto(trainer);
                btnRate.addActionListener((new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        int rating = JOptionPane.showOptionDialog(null, "Click the rating you want to give for " + trainerDto.getNameTrainer(), "Rate Trainer", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                        clientService.rateTrainer(idClient, rating + 1, trainer);   //fiecarui buton ii corespunde un int de la 0 la 5, deci pt rating trb +1
                        JOptionPane.showMessageDialog(null, "Your rating has been submitted.", "Thank you for your rating", JOptionPane.INFORMATION_MESSAGE);
                        btnRate.setText("Rated");
                        btnRate.setEnabled(false);
                        log.info("Trainer rated successfully!");
                    }
                }));
                tableModel.addRow(new Object[]{trainerDto.getNameTrainer(), trainerDto.getFitnessClassName(), btnRate});
            }
        }
    }
}
