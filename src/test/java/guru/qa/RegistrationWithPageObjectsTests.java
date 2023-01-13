package guru.qa;

import org.junit.jupiter.api.Test;

public class RegistrationWithPageObjectsTests extends TestBase{

    @Test
    void successfulRegistrationTest() {
        String firstName = "Olga";
        String lastName = "Ivanova";
        String email = "olga.ivanova@gmail.com";
        String mobileNumber = "7930726541";
        String gender = "Female";
        String day = "25";
        String month  = "June";
        String year = "1965";
        String subject = "English";
        String hobby = "Sports";
        String currentAddress = "Nizhny Novgorod, Sovetcky street, 5-110";
        String nameFile = "Img.jpg";
        String state =  "Haryana";
        String city = "Karnal";

        registrationPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhone(mobileNumber)
                .setBirthDate(day, month, year)
                .setSubject(subject)
                .setHobby(hobby)
                .uploadFile(nameFile)
                .setAddress(currentAddress)
                .setState(state)
                .setCity(city)
                .submitRegistrationData();

        registrationPage.verifyResultsModalAppears() .verifyResult("Student Name", firstName + " " + lastName)
                .verifyResult("Student Email", email)
                .verifyResult("Gender", gender)
                .verifyResult("Mobile", mobileNumber)
                .verifyResult("Date of Birth", day + " " + month + "," + year)
                .verifyResult("Subjects", subject)
                .verifyResult("Hobbies", hobby)
                .verifyResult("Picture", nameFile)
                .verifyResult("Address", currentAddress)
                .verifyResult("State and City", state + " " + city);

    }
}
