package validator;

import entity.Profile;
import entity.Schedule;
import exception.CustomExceptionMessages;
import exception.EntityNotExistsException;
import repository.ScheduleRepo;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileValidator {
    private static ScheduleRepo scheduleRepo = new ScheduleRepo();


    public static void validateProfileById(String idProfile) {
        if (idProfile == null || idProfile.equals("")) {
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_ID_MESSAGE);
        }
    }

    public static void validateProfile(Profile profile) {
        if (profile == null) {
            throw new EntityNotExistsException("No profile found.");
        }
        validateUpdateBirthday(profile.getId(), profile.getBirthday());
    }

    public static void validateUpdateName(String idProfile, String name) {

        if (idProfile == null || idProfile.equals("")) {
            throw new IllegalArgumentException("Profile is null or it has an empty name.");
        }

        if (name == null || name.length() < 2) {
            throw new IllegalArgumentException("Name is null or its length is smaller than 2.");
        }

        String reg = "^[\\p{L} -]+$";
        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.find()) throw new IllegalArgumentException(CustomExceptionMessages.INVALID_NAME);

    }

    public static void validateUpdateCertification(String idProfile, String certification) {
        if (idProfile == null || idProfile.equals("")) {
            throw new IllegalArgumentException("Profile is null or it has an empty name.");
        }

        if (certification == null || certification.length() < 2) {
            throw new IllegalArgumentException("Certification is null or its length is smaller than 2.");
        }
    }

    public static void validateUpdateBirthday(String idProfile, String birthday) {
        int day, month, year;

        try {
            day = Integer.parseInt(birthday.substring(0, 2));
            month = Integer.parseInt(birthday.substring(3, 5));
            year = Integer.parseInt(birthday.substring(6));

            LocalDate.of(year, month, day);
        } catch (Exception e) {
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_DATE);
        }
    }

    public static void validateUpdateIdClassSchedule(Profile profile, String idClass) {

        if (profile == null || profile.getId() == null) {
            throw new IllegalArgumentException("Profile is null or it has an empty name.");
        }

        int ok = 0;
        List<Schedule> schedules = scheduleRepo.findAllSchedule();
        for (Schedule scheduleBuff : schedules) {
            if (scheduleBuff.getIdClass().equals(idClass)) {
                ok++;
                break;
            }
        }
        if (ok == 0)
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_ID_SCHEDULE);


    }
}
