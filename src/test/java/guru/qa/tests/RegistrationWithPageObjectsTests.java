package guru.qa.tests;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class RegistrationWithPageObjectsTests extends TestBase {

    @Test
    void successfulRegistrationTest() throws ParseException {
        TestData testData = new TestData();

        registrationPage.openPage()
                .setFirstName(testData.firstName)
                .setLastName(testData.lastName)
                .setEmail(testData.email)
                .setGender(testData.gender)
                .setPhone(testData.mobileNumber)
                .setBirthDate(testData.day, testData.month, testData.year)
                .setSubject(testData.subject)
                .setHobby(testData.hobby)
                .uploadFile(testData.nameFile)
                .setAddress(testData.currentAddress)
                .setState(testData.state)
                .setCity(testData.city)
                .submitRegistrationData();

        registrationPage.verifyResultsModalAppears().verifyResult("Student Name", testData.firstName + " " + testData.lastName)
                .verifyResult("Student Email", testData.email)
                .verifyResult("Gender", testData.gender)
                .verifyResult("Mobile", testData.mobileNumber)
                .verifyResult("Date of Birth", testData.day + " " + Utility.getFullFormatMonth(testData.day,
                        testData.month, testData.year) + "," + testData.year)
                .verifyResult("Subjects", testData.subject)
                .verifyResult("Hobbies", testData.hobby)
                .verifyResult("Picture", testData.nameFile)
                .verifyResult("Address", testData.currentAddress)
                .verifyResult("State and City", testData.state + " " + testData.city);

    }
}
