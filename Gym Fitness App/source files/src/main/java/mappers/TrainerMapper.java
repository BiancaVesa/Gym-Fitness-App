package mappers;

import dto.TrainerDto;
import entity.Trainer;

public class TrainerMapper {
    public static TrainerDto entityToDto(Trainer trainer) {
        TrainerDto trainerDto = new TrainerDto();
        trainerDto.setNameTrainer(trainer.getProfile().getName());
        trainerDto.setFitnessClassName(trainer.getProfile().getCertification());
        return trainerDto;
    }
}
