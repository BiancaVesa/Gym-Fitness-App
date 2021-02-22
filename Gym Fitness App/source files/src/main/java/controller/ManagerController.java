package controller;

import view.*;

import javax.swing.*;

public class ManagerController {

    public void addNewTrainer() {

        new AddTrainerView();
    }

    public void editTrainer() {
        new EditScheduleView("");
    }

    public void generateRaports() {
        new GenerateMonthlyReportsView();
    }

    public void editManagerData() {
        new EditManagerView();
    }

    public void goBack(JFrame frame) {
        frame.dispose();
        new LoginView();
    }
}
