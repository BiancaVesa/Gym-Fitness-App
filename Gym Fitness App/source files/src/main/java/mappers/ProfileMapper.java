package mappers;

import dto.ClientProfileDto;
import dto.TrainerProfileDto;
import entity.Profile;

public class ProfileMapper {

    public static ClientProfileDto entityToClientProfileDto(Profile profile) {
        ClientProfileDto clientProfileDto = new ClientProfileDto();
        clientProfileDto.setBirthday(profile.getBirthday());
        clientProfileDto.setName(profile.getName());
        clientProfileDto.setProgress(profile.getProgress());
        clientProfileDto.setSex(profile.getSex());

        return clientProfileDto;
    }

    public static TrainerProfileDto entityToTrainerProfileDto(Profile profile) {
        TrainerProfileDto trainerProfileDto = new TrainerProfileDto();
        trainerProfileDto.setBirthday(profile.getBirthday());
        trainerProfileDto.setId(profile.getId());
        trainerProfileDto.setCertification(profile.getCertification());
        trainerProfileDto.setName(profile.getName());
        trainerProfileDto.setSex(profile.getSex());
        trainerProfileDto.setRating(profile.getRating());

        return trainerProfileDto;
    }


}
