package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateHandler {

    private SimpleDateFormat dateFormat;
    private Date currentDate;

    public DateHandler() {
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.currentDate = new Date();
    }

    /**
     * Attempts to parse a valid date from the inputted String.
     *
     * @param dateAsString
     *
     * @return The inputted string as Date.
     */
    public Date parse(String dateAsString) {
        if (validate(dateAsString)) {
            try {
                Date d = dateFormat.parse(dateAsString);
                return d;
            } catch (ParseException e) {
                return null;
            }
        }

        return null;
    }

    private boolean validate(String dateAsString) {
        String[] split = dateAsString.split("\\.");

        // Check that the format is correct
        if (split.length != 3) {
            return false;
        }

        // Validate the month and the year
        if (!validateMonth(split[1]) || !validateYear(split[2])) {
            return false;
        }

        // validate the day
        if (!validateDay(split[0], split[1])) {
            return false;
        }

        return true;
    }

    private boolean validateDay(String day, String month) {
        if (day.length() > 2) {
            return false;
        }

        try {
            int dayAsNumber = Integer.parseInt(day);
            int monthAsNumber = Integer.parseInt(month);

            if (dayAsNumber < 1) {
                return false;
            } else if (monthAsNumber == 2) {
                return !(dayAsNumber > 28);
            } else if (monthAsNumber == 1 || monthAsNumber == 3 || monthAsNumber == 5 || monthAsNumber == 7
                    || monthAsNumber == 8 || monthAsNumber == 10 || monthAsNumber == 12) {
                return !(dayAsNumber > 31);
            } else {
                return !(dayAsNumber > 30);
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validateMonth(String month) {
        if (month.length() > 2) {
            return false;
        }

        try {
            int asNumber = Integer.parseInt(month);

            if (asNumber < 1 || asNumber > 12) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    private boolean validateYear(String year) {
        try {
            int asNumber = Integer.parseInt(year);

            if (asNumber < 1970) {
                return false;
            }

        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    /**
     * Parses the inputted date to a String in dd.MM.yyyy format
     *
     * @param d
     * @return Date in dd.MM.yyyy format
     */
    public String dateAsString(Date d) {
        return dateFormat.format(d);
    }

    /**
     * Calculates the number of days between two dates
     *
     * @param d1 - The former date
     * @param d2 - The latter date
     * @return Number of days between the two dates
     */
    public long difference(Date d1, Date d2) {
        long difference = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }

    /**
     * Calculates the number of days between the inputted date and the current
     * date
     *
     * @param d - The date to be compared
     * @return Number of days until the inputted date
     */
    public long daysFromToday(Date d) {
        long difference = d.getTime() - currentDate.getTime();
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }

}
