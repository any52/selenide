package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {

    @BeforeAll
    static void setup(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void submitPracticeForm(){
        String firstName = "Olga";
        String lastName = "Ivanova";
        String email = "olga.ivanova@gmail.com";
        String mobileNumber = "7930726541";
        String gender = "Female";
        String month  = "June";
        String year = "1965";
        String subject1 = "English";
        String subject2 = "Biology";
        String currentAddress = "Nizhny Novgorod, Sovetcky street, 5-110";
        String nameFile = "Img.jpg";
        String state =  "Haryana";
        String city = "Karnal";

        open("/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        //add personal information
        $("#firstName").sendKeys(firstName);
        $("#lastName").sendKeys(lastName);
        $("#userEmail").sendKeys(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(mobileNumber);

        // choose date of birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(month);
        $(".react-datepicker__year-select").selectOptionContainingText(year);
        $(".react-datepicker__day.react-datepicker__day--025").click();

        // choose subjects
        $("#subjectsInput").sendKeys("E");
        $(byText(subject1)).click();
        $("#subjectsInput").sendKeys("B");
        $(byText(subject2)).click();
        $("[for=hobbies-checkbox-1]").click();

        //upload file
        File file = new File("src/test/resources/" +nameFile);
        $("#uploadPicture").uploadFile(file);
        $("#uploadPicture").shouldHave(value(nameFile));

        //choose address, state, city
        $("#currentAddress").sendKeys(currentAddress);
        $("#state").click();
        $(byText(state)).click();
        $("#city").click();
        $(byText(city)).click();
        $("#submit").click();

        // check results
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " + lastName));
        $(".table").$(byText("Student Email")).sibling(0).shouldHave(text(email));
        $(".table").$(byText("Gender")).sibling(0).shouldHave(text(gender));
        $(".table").$(byText("Mobile")).sibling(0).shouldHave(text(mobileNumber));
        $(".table").$(byText("Date of Birth")).sibling(0).shouldHave(text("25 "+ month + "," + year));
        $(".table").$(byText("Subjects")).sibling(0).shouldHave(text(subject1 + ", " + subject2));
        $(".table").$(byText("Hobbies")).sibling(0).shouldHave(text("Sports"));
        $(".table").$(byText("Picture")).sibling(0).shouldHave(text(nameFile));
        $(".table").$(byText("Address")).sibling(0).shouldHave(text(currentAddress));
        $(".table").$(byText("State and City")).sibling(0).shouldHave(text(state + " " + city));

    }
}
