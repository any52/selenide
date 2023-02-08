package guru.qa.tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class RegistrationWithPageObjectsTests extends TestBaseExtended {

    @Test
    @Owner("almoiseeva")
    @Tag("remote")
    @Tag("parameters")
    @DisplayName("Check filling form of registration student")
    void successfulRegistrationTest()  {
        TestData testData = new TestData();

        step("Open form of registration", () -> {
        registrationPage.openPage();
        });
        step("Fill form of registration", () -> {
        registrationPage.setFirstName(testData.firstName)
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
        });
        step("Verify results", () -> {
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
        });

    }
}
