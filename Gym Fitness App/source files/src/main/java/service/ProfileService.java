package service;

import dto.ClientProfileDto;
import dto.TrainerProfileDto;
import entity.Profile;
import mappers.ProfileMapper;
import repository.ProfileRepo;
import validator.ProfileValidator;

public class ProfileService {

    private ProfileRepo profileRepo;

    public ProfileService() {
        this.profileRepo = new ProfileRepo();
    }

    private Profile getProfile(String idProfile) {
        return profileRepo.findProfileById(idProfile);
    }

    public ClientProfileDto getClientProfile(String idProfile) {
        ClientProfileDto profileDto = ProfileMapper.entityToClientProfileDto(getProfile(idProfile));
        return profileDto;
    }

    public TrainerProfileDto getTrainerProfile(String idProfile) {
        TrainerProfileDto profileDto = ProfileMapper.entityToTrainerProfileDto(getProfile(idProfile));
        return profileDto;
    }

    public void modifyName(String idProfile, String newName) {
        ProfileValidator.validateUpdateName(idProfile, newName);
        profileRepo.updateProfileName(newName, getProfile(idProfile));
    }

    public void modifyBirthday(String idProfile, String newBirthday) {
        ProfileValidator.validateUpdateBirthday(idProfile, newBirthday);
        profileRepo.updateProfileBirthday(newBirthday, getProfile(idProfile));
    }

    public void modifyCertification(String idProfile, String newCertification) {
        ProfileValidator.validateUpdateCertification(idProfile, newCertification);
        profileRepo.updateProfileCertification(newCertification, getProfile(idProfile));
    }

    public void modifyRating(String idProfile, float newRating) {
        profileRepo.updateProfileRating(newRating, getProfile(idProfile));
    }

}
