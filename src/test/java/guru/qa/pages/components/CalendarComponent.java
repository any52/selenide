package guru.qa.pages.components;

import guru.qa.tests.Utility;

import java.text.ParseException;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {

    public void setDate(String day, String month, String year) throws ParseException {

        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__month-select").selectOption(Utility.getFullFormatMonth(day, month, year));
        $(".react-datepicker__day--0" + day +
                ":not(.react-datepicker__day--outside-month)").click();
    }

}
