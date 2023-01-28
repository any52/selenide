package guru.qa.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Utility {

    public static String getFullFormatMonth(String day, String month, String year) throws ParseException {
        SimpleDateFormat oldDateFormat = new SimpleDateFormat("d MMM yyyy", Locale.US);
        SimpleDateFormat newDateFormat = new SimpleDateFormat("dd MMMMM yyyy", Locale.US);
        StringBuilder date = new StringBuilder();
        date.append(day).append(" ").append(month).append(" ").append(year);
        List<String> newFormatDate = List.of(newDateFormat.format(oldDateFormat.parse(date.toString())).split(" "));
        return newFormatDate.get(1);
    }
}
