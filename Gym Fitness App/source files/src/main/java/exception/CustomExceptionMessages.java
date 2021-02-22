package exception;

public class CustomExceptionMessages {

    public static final String INVALID_ID_MESSAGE = "Id is null or empty. Please try again";
    public static final String INVALID_ID_SCHEDULE = "This id does not exists.";
    public static final String INVALID_DATE = "This date is invalid.";
    public static final String INVALID_NAME = "This name contains invalid characters or digits.";
    public static final String BUSY_DATE = "There is already a Class at that time";
    public static final String INVALID_USER = "Invalid username or password entered. Please try again!";
    public static final String INVALID_USERNAME = "Invalid username. Careful not to insert blanks";
    public static final String INVALID_PASSWORD_LENGTH = "Password must have at least 6 characters";
    public static final String EXISTING_PASSWORD = "This password already exists!";
    public static final String EXISTING_USERNAME = "This username already exists!";
    public static final String START_DATE_AFTER_EXPIRATION_DATE = "Start date of membership is after the expiration date!";
    public static final String PERIOD_INVALID = "The period of time between the dates is invalid.";
    public static final String INVALID_MEMBERSHIP_TYPE = "This membership type does not exists. Try to write the first letter with upper case.";
    public static final String INVALID_TRAINER = "This trainer does not exists.";
    public static final String INVALID_CREDENTIALS = "Credentials are incorrect!";
    public static final String PASSWORD_NOT_MATCH_EXCEPTION = "Passwords must match!";
    public static final String ALREADY_CLASS = "There must be an hour between the new training and the existing ones!";
    public static final String EXISTING_MEMBERSHIP = "You already have a membership";
    public static final String NO_MEMBERSHIP = "You don't have a membership";
    public static final String ZERO_BOOKINGS_LEFT = "You don't have any bookings left on your membership";
    public static final String MEMBERSHIP_EXPIRED = "Your membership has expired";
    public static final String INVALID_DATE_FORMAT = "Invalid date format. YYYY-MM-DD HH:MM";
    public static final String INVALID_PHONE_NR = "Invalid phone format. 07xxxxxxxx";
    public static final String INVALID_EMAIL = "Email format invalid";
    public static final String CERTIFICATION_INVALID = "Invalid certification for this type of schedule!";

}
