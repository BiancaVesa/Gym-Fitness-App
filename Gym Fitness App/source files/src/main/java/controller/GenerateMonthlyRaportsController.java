package controller;

import repository.ManagerRepo;
import repository.TrainerRepo;
import service.ManagerService;
import start.ApplicationStart;
import utils.ControllerUtils;

import java.time.LocalDate;
import java.util.logging.Logger;

public class GenerateMonthlyRaportsController {

    private final ManagerService managerService = new ManagerService(new ManagerRepo(), new TrainerRepo());
    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());


    public void generateRaport(String month, String year){
        System.out.println(month + year );

        try {
            if (!month.equals("") && !year.equals("") ) {
                System.out.println("nu am luna si an invalid");
                LocalDate localDate = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
                managerService.generateReports(localDate);
                ControllerUtils.createSwingSuccessMessage("Reports are generated!");
            } else {
                ControllerUtils.createSwingErrorMessage("Date invalide");
                log.severe("Error while trying to generate monthly reports.");
            }
        }catch(NullPointerException e)
        {
            ControllerUtils.createSwingErrorMessage("Nu am membership-uri in luna " + month);
            log.severe("Error while trying to generate monthly reports.");
        }catch (IllegalArgumentException e) {
            ControllerUtils.createSwingErrorMessage(e.getMessage());
            log.severe("Error while trying to generate monthly reports.");
        }
    }
}
