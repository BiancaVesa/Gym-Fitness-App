package validator;

import dto.LoginDto;
import entity.Login;
import entity.Profile;
import exception.CustomExceptionMessages;
import repository.LoginRepo;
import repository.ProfileRepo;
import repository.TrainerRepo;

public class LoginValidator {

    public static void validateLoginCredentials(LoginDto login) {
        if (login.getUserName().contains(" ") || login.getUserName().isEmpty())
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_USERNAME);
        if (login.getPassword().isEmpty() || login.getPassword().length() < 6)
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_PASSWORD_LENGTH);
    }

    public static void validateLoginProfile(Login login) {
        ProfileValidator.validateProfile(login.getClient().getProfile());
    }

    public static void validatePassword(Login login, String password) {
        if (new LoginRepo().getLoginPasswordByLogin(login).getPassword().equals(password))
            throw new IllegalArgumentException(CustomExceptionMessages.EXISTING_PASSWORD);
    }

    public static void validateUserName(Login login, String userName) {
        if (new LoginRepo().getLoginUsernameByLogin(login).getUserName().equals(userName))
            throw new IllegalArgumentException(CustomExceptionMessages.EXISTING_USERNAME);
    }


    public static void validateExistingUserName(String username) {
        if (new LoginRepo().findByUsername(username) != null) {
            throw new IllegalArgumentException(CustomExceptionMessages.EXISTING_USERNAME);
        }
    }

    public static void validateNewUserName(String idTrainer, String username) {
        TrainerRepo trainerRepo = new TrainerRepo();
        String idProfile = trainerRepo.findTrainerById(idTrainer).getIdProfile();
        ProfileRepo profileRepo = new ProfileRepo();
        Profile profile = profileRepo.findProfileById(idProfile);
        if (profile.getTrainer().getTrainerCredentials().getUserName().equals(username)) {
            throw new IllegalArgumentException(CustomExceptionMessages.EXISTING_USERNAME);
        }
    }

    public static void validatePasswordTrainer(String reNewPass, String newPassword) {
        if (!reNewPass.equals(newPassword)) {
            throw new IllegalArgumentException(CustomExceptionMessages.PASSWORD_NOT_MATCH_EXCEPTION);
        }
    }


    public static boolean validatePasswords(String password, String confirmationPassword) {
        if (!password.equals(confirmationPassword) || password.equals(""))
            throw new IllegalArgumentException(CustomExceptionMessages.PASSWORD_NOT_MATCH_EXCEPTION);
        else return true;
    }

}




