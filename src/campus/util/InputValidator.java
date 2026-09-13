package campus.util;

public class InputValidator {

    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public static boolean isValidPriority(int priority) {
        return priority >= 1 && priority <= 5;
    }

    public static boolean isValidCapacity(int capacity) {
        return capacity > 0;
    }

    public static boolean isValidTime(
            java.time.LocalTime startTime,
            java.time.LocalTime endTime) {

        return startTime != null
                && endTime != null
                && startTime.isBefore(endTime);
    }
}

