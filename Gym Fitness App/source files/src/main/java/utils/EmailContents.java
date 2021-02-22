package utils;

import entity.Profile;

public class EmailContents {

    public static StringBuilder generateRegistrationEmail(Profile userProfile) {
        StringBuilder emailContent = new StringBuilder();

        emailContent.append("  Thank you for joining our gym. We are so proud to have you among us")
                .append(" mr. / mrs. ")
                .append(userProfile.getName())
                .append(". Your username and password have been set, so you can log in to our application.\n")
                .append("Should you encounter any problems, be sure to let us know via email: fitness-app-gym@yandex.com\n")
                .append("\n\n\nThis email is for informational purposes only and please do not reply")
                .append("\nYours dearly, Fitness Gym");

        return emailContent;
    }

}

