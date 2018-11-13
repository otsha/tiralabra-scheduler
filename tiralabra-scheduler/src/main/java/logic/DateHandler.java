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
    public Date validate(String dateAsString) {
        try {
            Date d = dateFormat.parse(dateAsString);
            return d;
        } catch (ParseException e) {
            return null;
        }
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
     * Calculates the number of days between the inputted date and the current date
     *
     * @param d - The date to be compared
     * @return Number of days until the inputted date
     */
    public long daysFromToday(Date d) {
        long difference = d.getTime() - currentDate.getTime();
        return TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }

}
