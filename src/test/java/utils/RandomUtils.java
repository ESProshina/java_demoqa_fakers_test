package utils;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {

    private static final Faker faker = new Faker(new Locale("en-US"));

    // Генерация имени
    public static String getRandomFirstName() {
        return faker.name().firstName();
    }

    // Генерация фамилии
    public static String getRandomLastName() {
        return faker.name().lastName();
    }

    // Генерация email
    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    // Генерация email на основе имени и фамилии
    public static String getRandomEmail(String firstName, String lastName) {
        return faker.internet().emailAddress(firstName.toLowerCase() + "." + lastName.toLowerCase());
    }

    // Генерация пола
    public static String getRandomGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    // Генерация номера телефона (10 цифр)
    public static String getRandomMobileNumber() {
        return faker.number().digits(10);
    }

    // Генерация дня рождения
    public static String getRandomDay() {
        return String.valueOf(faker.number().numberBetween(1, 28));
    }

    // Генерация месяца рождения
    public static String getRandomMonth() {
        return faker.options().option(
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        );
    }

    // Генерация года рождения
    public static String getRandomYear() {
        return String.valueOf(faker.number().numberBetween(1980, 2005));
    }

    // Генерация предмета
    public static String getRandomSubject() {
        return faker.options().option("Maths", "English", "Chemistry", "Physics", "Computer Science", "Commerce");
    }

    // Генерация хобби
    public static String getRandomHobby() {
        return faker.options().option("Reading", "Music", "Sports");
    }

    // Генерация названия картинки
    public static String getRandomPictureName() {
        return faker.options().option("testbest.png", "test_picture.jpg", "sample.png", "photo.jpeg");
    }

    // Генерация адреса
    public static String getRandomAddress() {
        return faker.address().fullAddress();
    }

    // Генерация штата
    public static String getRandomState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    // Генерация города
    public static String getRandomCity(String state) {
        switch (state) {
            case "NCR":
                return faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh":
                return faker.options().option("Agra", "Lucknow", "Kanpur", "Varanasi");
            case "Haryana":
                return faker.options().option("Karnal", "Panipat", "Gurgaon", "Faridabad");
            case "Rajasthan":
                return faker.options().option("Jaipur", "Jodhpur", "Udaipur", "Kota");
            default:
                return faker.options().option("Delhi", "Gurgaon", "Noida");
        }
    }

    public static String getRandomCity() {
        return faker.options().option("Delhi", "Gurgaon", "Noida", "Agra", "Lucknow", "Kanpur", "Panipat");
    }

    // Генерация полного набора данных для студента
    public static StudentTestData getRandomStudentData() {
        String firstName = getRandomFirstName();
        String lastName = getRandomLastName();
        String email = getRandomEmail(firstName, lastName);
        String gender = getRandomGender();
        String mobileNumber = getRandomMobileNumber();
        String day = getRandomDay();
        String month = getRandomMonth();
        String year = getRandomYear();
        String subject = getRandomSubject();
        String hobby = getRandomHobby();
        String pictureName = "testbest.png"; // Используем существующий файл
        String address = getRandomAddress();
        String state = getRandomState();
        String city = getRandomCity(state);

        return new StudentTestData(firstName, lastName, email, gender, mobileNumber,
                day, month, year, subject, hobby, pictureName, address, state, city);
    }

    // Вспомогательный класс для хранения данных студента
    public static class StudentTestData {
        private final String firstName;
        private final String lastName;
        private final String email;
        private final String gender;
        private final String mobileNumber;
        private final String day;
        private final String month;
        private final String year;
        private final String subject;
        private final String hobby;
        private final String pictureName;
        private final String address;
        private final String state;
        private final String city;

        public StudentTestData(String firstName, String lastName, String email, String gender,
                               String mobileNumber, String day, String month, String year,
                               String subject, String hobby, String pictureName,
                               String address, String state, String city) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
            this.gender = gender;
            this.mobileNumber = mobileNumber;
            this.day = day;
            this.month = month;
            this.year = year;
            this.subject = subject;
            this.hobby = hobby;
            this.pictureName = pictureName;
            this.address = address;
            this.state = state;
            this.city = city;
        }

        // Геттеры
        public String getFirstName() { return firstName; }
        public String getLastName() { return lastName; }
        public String getEmail() { return email; }
        public String getGender() { return gender; }
        public String getMobileNumber() { return mobileNumber; }
        public String getDay() { return day; }
        public String getMonth() { return month; }
        public String getYear() { return year; }
        public String getSubject() { return subject; }
        public String getHobby() { return hobby; }
        public String getPictureName() { return pictureName; }
        public String getAddress() { return address; }
        public String getState() { return state; }
        public String getCity() { return city; }

        // Метод для получения всех данных в виде массива
        public Object[] toArray() {
            return new Object[]{firstName, lastName, email, gender, mobileNumber,
                    day, month, year, subject, hobby, pictureName, address, state, city};
        }
    }
}