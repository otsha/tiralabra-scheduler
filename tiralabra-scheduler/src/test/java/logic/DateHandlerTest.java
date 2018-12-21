package logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class DateHandlerTest {

    private DateHandler handler;
    private SimpleDateFormat dateFormat;

    public DateHandlerTest() {
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.handler = new DateHandler();
    }

    @Test
    public void parseReturnsTheCorrectDateIfTheStringIsValid() throws ParseException {
        String dateAsString = "13.12.2018";
        assertEquals(dateFormat.parse(dateAsString), handler.parse(dateAsString));
    }
    
    @Test
    public void parseReturnsNullIfTheDateFormatIsIncorrect() throws ParseException {
        String dateAsString = "14/2/2019";
        assertEquals(null, handler.parse(dateAsString));
    }

    @Test
    public void parseReturnsNullIfTheStringContainsInvalidCharacters() {
        String invalidDate = "xx.aa.bcde";
        assertEquals(null, handler.parse(invalidDate));
    }

    @Test
    public void parseReturnsNullIfTheYearIsBefore1970() {
        String invalidDate = "1.1.1969";
        assertEquals(null, handler.parse(invalidDate));
    }

    @Test
    public void parseReturnsNullIfTheMonthIsInvalid() {
        String invalidDate = "1.13.2019";
        assertEquals(null, handler.parse(invalidDate));
    }

    @Test
    public void parseReturnsNullIfTheDayIsInvalid() {
        String invalidDate = "31.2.2019";
        assertEquals(null, handler.parse(invalidDate));
    }
    
    @Test
    public void parseReturnsNullIfDayIsTooLong() {
        String invalidDate = "999.2.2019";
        assertEquals(null, handler.parse(invalidDate));
    }

    @Test
    public void dateAsStringReturnsAStringInTheCorrectFormat() {
        Date d = new Date();
        assertEquals(dateFormat.format(d), handler.dateAsString(d));
    }

    @Test
    public void differenceCalculatesTheNumberOfDaysBetweenTwoDates() throws ParseException {
        Date d1 = dateFormat.parse("20.12.2018");
        Date d2 = dateFormat.parse("31.12.2018");
        // Expected difference is 11 days
        assertEquals(11, handler.difference(d1, d2));
    }

    @Test
    public void daysFromTodayCalculatesTheNumberOfDaysBetweenTodayAndTheDate() throws ParseException {
        Date today = new Date();
        Date target = dateFormat.parse("11.11.2019");
        long difference = target.getTime() - today.getTime();
        long differenceInDays = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
        assertEquals(differenceInDays, handler.daysFromToday(target));
    }
}
