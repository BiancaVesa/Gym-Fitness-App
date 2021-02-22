package service;


import dto.TrainerProfileDto;
import entity.Trainer;
import repository.TrainerRepo;

public class TrainerService {

    private TrainerRepo trainerRepo;
    private ProfileService profileService;
    private LoginService loginService;
    private String idCredentials;

    public TrainerService() {
        this.trainerRepo = new TrainerRepo();
        this.profileService = new ProfileService();
        this.loginService = new LoginService();
    }

    public Trainer getTrainer(String idTrainer) {
        return trainerRepo.findTrainerById(idTrainer);
    }

    public TrainerProfileDto viewProfile(String idTrainer) {
        return profileService.getTrainerProfile(getTrainer(idTrainer).getIdProfile());
    }

    public void modifyNameTrainer(String idTrainer, String newName) {
        profileService.modifyName(getTrainer(idTrainer).getIdProfile(), newName);
    }

    public void modifyCertification(String idProfile, String certification) {
        profileService.modifyCertification(getTrainer(idProfile).getIdProfile(), certification);
    }


    public void modifyUserName(String idTrainer, String newUserName) {
        idCredentials = this.getTrainer(idTrainer).getTrainerIdCredentials();
        loginService.modifyUserNameTrainer(idTrainer, idCredentials, newUserName);
    }

    public void modifyNewPass(String idTrainer, String newPass, String reNewPass) {
        idCredentials = this.getTrainer(idTrainer).getTrainerIdCredentials();
        loginService.modifyPasswordTrainer(idCredentials, newPass, reNewPass);
    }
}
