package mappers;

import dto.ScheduleDto;
import entity.Schedule;

public class ScheduleMapper {

    public static ScheduleDto entityToDto(Schedule schedule) {
        ScheduleDto scheduleDto = new ScheduleDto();
        scheduleDto.setClassName(schedule.getClassName());
        scheduleDto.setTemp(schedule.getTemp());
        scheduleDto.setTrainerName(schedule.getTrainer().getProfile().getName());
        scheduleDto.setRatingTrainer(schedule.getTrainer().getProfile().getRating());
        return scheduleDto;
    }

    public static Schedule dtoToEntity(ScheduleDto scheduleDto) {
        return null;
    }
}
