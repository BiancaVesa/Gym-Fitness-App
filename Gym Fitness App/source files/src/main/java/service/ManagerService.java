package service;

import entity.*;
import exception.ManagerExistsException;
import pdfGenerator.CreatePdf;
import repository.LoginRepo;
import repository.ManagerRepo;
import repository.ScheduleRepo;
import repository.TrainerRepo;
import start.ApplicationStart;
import validator.LoginValidator;
import validator.ManagerValidator;
import validator.ScheduleValidator;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class ManagerService {

    private final static Logger log = Logger.getLogger(ApplicationStart.class.getName());

    private ManagerRepo managerRepo;
    private TrainerRepo trainerRepo;
    private MembershipService membershipService = new MembershipService();
    private Manager manager = new Manager();
    private LoginRepo loginRepo = new LoginRepo();

    public ManagerService(ManagerRepo managerRepo, TrainerRepo trainerRepo) {
        this.managerRepo = managerRepo;
        this.trainerRepo = trainerRepo;
    }

    public void createManager() {
        try {
            ManagerValidator.validateNonExistingManager("admin");
            log.severe("A manager already exists!");
        } catch (NoResultException nre) {

            manager.setIdManager(UUID.randomUUID().toString());
            manager.setManagerCredentials("admin", "admin12");
            manager.setName("Vadim Tudor");
            manager.setEmail("tudorelungurel12@gmail.com");
            manager.setPhoneNr("0735267899");

            managerRepo.insertManager(manager);
            log.info("Manager inserted");
        }
        catch (ManagerExistsException mex) {
            log.warning("Already have a manager!!");
        }
    }

    public void createTrainer(String name, String birthday, String sex, String certification, String userName, String password) {
        try {
            LoginValidator.validateExistingUserName(userName);

        } catch (NoResultException e) {
            //validator pt trainer sa nu cumva sa punem vreun camp null - acest validator arunca o exceptie ce se trateaza in controller
            Trainer trainer = new Trainer();
            trainer.setIdTrainer(UUID.randomUUID().toString());
            trainer.setTrainerCredentials(userName, password);

            Profile trainerProfile = new Profile();
            trainerProfile.setId(UUID.randomUUID().toString());
            trainerProfile.setType("trainer");
            trainerProfile.setName(name);
            trainerProfile.setBirthday(birthday);
            trainerProfile.setSex(sex);
            trainerProfile.setCertification(certification);

            trainer.setProfile(trainerProfile);

            trainerRepo.insertNewTrainer(trainer);

            log.info("New trainer inserted: " + trainer);
        }
    }

    public void modifyScheduleInfo(String idClass, LocalDateTime newClassDate) {
        ScheduleRepo scheduleRepo = new ScheduleRepo();
        try {

            //se poate sa nu-l gaseasca si atunci vom avea un obiect null si vom avea illegalArgumentException, nu nullpointer
            Schedule schedule = scheduleRepo.findScheduleById(idClass);

            //asta arunca o exceptie cand nu am gasit antrenamentul cu id ul dat sau cand noua ora este invalida
            ScheduleValidator.validateUpdateTemp(schedule, newClassDate);

            ScheduleValidator.validateOneHourInterval(newClassDate);
            scheduleRepo.updateScheduleTemp(newClassDate, schedule);

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void generateReports(LocalDate startDate) {
        //verificam daca startDate e in trecut: nu putem face rapoarte pt viitor
        if (startDate.isAfter(LocalDate.now())) {
            log.severe("Invalid time!");
            throw new IllegalArgumentException("Invalid time!");
        } else {
            float silverPrice = 0;
            float goldPrice = 0;
            float platinumPrice = 0;
            List<Membership> memberships = membershipService.getMembershipByDate(startDate);

            for (Membership m : memberships) {
                switch (m.getMembershipType()) {
                    case "Silver":
                        silverPrice += 90;
                        break;
                    case "Gold":
                        goldPrice += 170;
                        break;
                    case "Platinum":
                        platinumPrice += 230;
                        break;
                    default:
                        break;
                }
            }

            CreatePdf billFile = new CreatePdf("MonthlyReport" + startDate.getMonth() + startDate.getYear() + ".pdf");
            try {
                billFile.createBillPDF(silverPrice, goldPrice, platinumPrice, startDate.getMonth().toString(), " " + startDate.getYear());
            } catch (Exception ex) {
                ex.printStackTrace();
                throw ex;
            }
        }

    }

    public void modifyName(String name) {
        Login log = loginRepo.findByUsername("admin");
        managerRepo.updateManagerName(name, log.getManager());
        manager.setName(name);
    }

    public void modifyPhoneNr(String phoneNr) {
        Login log = loginRepo.findByUsername("admin");
        managerRepo.updateManagerPhone(phoneNr, log.getManager());
        manager.setPhoneNr(phoneNr);
    }

    public void modifyEmail(String email) {
        Login log = loginRepo.findByUsername("admin");
        managerRepo.updateManagerEmail(email, log.getManager());
        manager.setEmail(email);
    }


}
