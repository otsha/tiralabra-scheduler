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
    public void validateReturnsTheCorrectDateIfTheStringIsValid() throws ParseException {
        String dateAsString = "13.12.2018";
        assertEquals(dateFormat.parse(dateAsString), handler.validate(dateAsString));
    }

    @Test
    public void validateReturnsNullIfTheStringIsInvalid() {
        String invalidDate = "xx.23.2188";
        assertEquals(null, handler.validate(invalidDate));
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
