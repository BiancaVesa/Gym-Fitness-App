package start;

import controller.LoginController;
import entity.*;
import repository.*;
import service.ClientService;
import service.ScheduleService;
import view.LoginView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ApplicationStart {

    public static void main(String[] args) {

        LoginController.run();
        new LoginView();

    }
}
