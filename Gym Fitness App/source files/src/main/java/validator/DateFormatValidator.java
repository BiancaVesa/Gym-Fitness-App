package validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatValidator implements DateValidator {

    private String dateFormat;

    public DateFormatValidator(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(String dateStr) {
        DateFormat simpleDateFormat = new SimpleDateFormat(this.dateFormat);
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}

