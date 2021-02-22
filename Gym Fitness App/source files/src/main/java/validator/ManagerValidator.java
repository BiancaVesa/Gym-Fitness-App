package validator;

import entity.Login;
import exception.CustomExceptionMessages;
import exception.ManagerExistsException;
import org.apache.commons.validator.GenericValidator;
import repository.LoginRepo;

import javax.persistence.NoResultException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ManagerValidator {

    private static LoginRepo loginRepo = new LoginRepo();

    public static void validateNonExistingManager(String username) {
        Login login = loginRepo.findByUsername(username);
        if (login != null) {
            throw new ManagerExistsException("A manager already exists!");
        } else
            throw new NoResultException();
    }

    public static void validatePhoneNr(String phoneNr) {
        String reg = "([07])([0-9]{8})";
        Pattern pattern = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phoneNr);
        if (!matcher.find()) throw new IllegalArgumentException(CustomExceptionMessages.INVALID_PHONE_NR);
    }

    public static void emailValidator(String email) {
        if (!GenericValidator.isEmail(email)) {
            throw new IllegalArgumentException(CustomExceptionMessages.INVALID_EMAIL);
        }
    }

}
