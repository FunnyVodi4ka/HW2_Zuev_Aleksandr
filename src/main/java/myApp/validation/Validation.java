package myApp.validation;

public class Validation {
    private static final String EMAIL_PATTERN =
            "^[\\w-\\+]+(\\.[\\w-\\+]+)?@[\\w-\\+]+(\\.[\\w-\\+]+)?$";

    public boolean checkId(String strId) {
        try {
            int id = Integer.parseInt(strId);
            return id > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean checkName(String strName) {
        return !strName.isEmpty() && strName.length() <= 50;
    }

    public static boolean checkEmail(String email) {
        return email.matches(EMAIL_PATTERN) && !email.isEmpty() && email.length() <= 255;
    }

    public boolean checkAge(String strAge) {
        try {
            int age = Integer.parseInt(strAge);
            return age >= 0 && age <= 125;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
