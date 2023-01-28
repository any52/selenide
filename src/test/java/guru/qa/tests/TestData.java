package guru.qa.tests;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.*;

public class TestData {

    Faker faker = new Faker(new Locale("us"));
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String currentAddress = faker.address().streetAddress();
    String mobileNumber = faker.number().digits(10);
    String gender = faker.options().option("Female", "Male", "Other");
    List<String> date = getDate();
    String day = date.get(0);
    String month = date.get(1);
    String year = date.get(2);
    String subject = faker.options().option("Arts", "Accounting", "Biology", "Civics", "Chemistry", "Commerce",
            "Computer Science", "Hindi", "History", "English", "Economics", "Physics", "Maths", "Social Studies");
    String hobby = faker.options().option("Sports", "Reading", "Music");
    String nameFile = "Img.jpg";
    List<String> cityAndState = getCityAndState();
    String state = cityAndState.get(0);
    String city = cityAndState.get(1);

    public List<String> getDate() {
        String date = new SimpleDateFormat("d MMM yyyy", Locale.US).format(faker.date().birthday());
        System.out.println(date);
        return Arrays.asList(date.split(" "));
    }

    public List<String> getCityAndState() {
        String state = faker.options().option("NCR", "Haryana", "Uttar Pradesh", "Rajasthan");
        String city = "";
        switch (state) {
            case ("NCR"):
                city = faker.options().option("Delhi", "Gurgaon", "Noida");
                break;
            case ("Haryana"):
                city = faker.options().option("Karnal", "Panipat");
                break;
            case ("Uttar Pradesh"):
                city = faker.options().option("Agra", "Lucknow", "Merrut");
                break;
            case ("Rajasthan"):
                city = faker.options().option("Jaipur", "Jaiselmer");
                break;
        }
        return Arrays.asList(new String[]{state, city});
    }

}
